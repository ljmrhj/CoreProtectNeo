package net.coreprotect.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import net.coreprotect.config.ConfigHandler;
import net.coreprotect.utility.ErrorReporter;

/**
 * Deletes old data (the {@code /co purge} command).
 *
 * <p>The same tables are purged as in the original plugin, with the same "older than" semantics.
 * Rows are removed in batches so a large purge does not lock the database for long.
 */
public final class Purge {

    /** Tables that carry a {@code time} column and are purged. */
    private static final String[] TABLES = {
            "block", "container", "item", "chat", "command", "session", "entity", "sign", "skull", "username_log"
    };

    private static final int BATCH_SIZE = 1000;

    private Purge() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Deletes every row older than {@code timeCutoff}.
     *
     * @param timeCutoff unix seconds; rows with {@code time < timeCutoff} are removed
     * @param worldName  optional dimension filter (only affects tables that store a world)
     * @return the number of removed rows
     */
    public static long purge(long timeCutoff, String worldName) {
        long removed = 0;
        try (Connection connection = Database.getConnection(false)) {
            if (connection == null) {
                return 0;
            }
            connection.setAutoCommit(false);

            final Integer worldId = worldName == null ? null : ConfigHandler.worlds.get(worldName);
            for (String table : TABLES) {
                removed += purgeTable(connection, table, timeCutoff, worldId, hasWorldColumn(table));
            }

            connection.commit();
        }
        catch (Exception e) {
            ErrorReporter.report(e);
        }
        return removed;
    }

    private static boolean hasWorldColumn(String table) {
        return switch (table) {
            case "block", "container", "item", "chat", "command", "session", "sign" -> true;
            default -> false;
        };
    }

    private static long purgeTable(Connection connection, String table, long timeCutoff, Integer worldId,
                                   boolean useWorld) {
        long removed = 0;
        String sql = "DELETE FROM " + ConfigHandler.prefix + table + " WHERE time < ?";
        if (useWorld && worldId != null) {
            sql += " AND wid = ?";
        }

        try {
            boolean finished = false;
            while (!finished) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setLong(1, timeCutoff);
                    if (useWorld && worldId != null) {
                        statement.setInt(2, worldId);
                    }
                    final int affected = statement.executeUpdate();
                    removed += affected;
                    finished = affected < BATCH_SIZE;
                }
            }
        }
        catch (Exception e) {
            // Tables that do not exist in this database are skipped.
            final String message = e.getMessage() == null ? "" : e.getMessage().toLowerCase(java.util.Locale.ROOT);
            if (!message.contains("no such table") && !message.contains("doesn't exist")) {
                ErrorReporter.report(e);
            }
        }
        return removed;
    }
}
