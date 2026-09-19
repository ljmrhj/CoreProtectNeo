package net.coreprotect.utility;

import net.coreprotect.config.ConfigHandler;

/**
 * Version information for the NeoForge port.
 *
 * <p>The comparison helpers keep the signatures of the original plugin so the patch scripts and
 * command code stay unchanged. Bukkit/Spigot/Paper/Folia and WorldEdit detection always report
 * {@code false}: those platforms do not exist on NeoForge.
 */
public final class VersionUtils {

    public static final String PLUGIN_NAME = "CoreProtect";
    public static final String PLUGIN_VERSION = "24.1";
    private static final String BRANCH = ConfigHandler.EDITION_BRANCH;

    private VersionUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Version the database schema is patched to, e.g. {@code [24, 0, 0]}.
     */
    public static Integer[] getInternalPluginVersion() {
        return parseVersion(ConfigHandler.PATCH_VERSION);
    }

    /**
     * Compares two version arrays.
     *
     * @return true when {@code latestVersion} is newer than {@code currentVersion}
     */
    public static boolean newVersion(Integer[] currentVersion, Integer[] latestVersion) {
        if (currentVersion == null || latestVersion == null) {
            return false;
        }
        for (int i = 0; i < Math.min(currentVersion.length, latestVersion.length); i++) {
            int current = currentVersion[i] == null ? 0 : currentVersion[i];
            int latest = latestVersion[i] == null ? 0 : latestVersion[i];
            if (latest > current) {
                return true;
            }
            if (latest < current) {
                return false;
            }
        }
        return false;
    }

    /**
     * Compares two version strings such as "2.18.1".
     *
     * @return true when {@code latestVersion} is newer than {@code currentVersion}
     */
    public static boolean newVersion(String currentVersion, String latestVersion) {
        return newVersion(parseVersion(currentVersion), parseVersion(latestVersion));
    }

    /** Parses "24.1" / "2.18.1" into a three element version array. */
    public static Integer[] parseVersion(String version) {
        Integer[] result = new Integer[] { 0, 0, 0 };
        if (version == null || version.isEmpty()) {
            return result;
        }
        String[] split = version.split("\\.");
        for (int i = 0; i < Math.min(split.length, 3); i++) {
            try {
                result[i] = Integer.parseInt(split[i].replaceAll("[^0-9]", ""));
            }
            catch (NumberFormatException e) {
                result[i] = 0;
            }
        }
        return result;
    }

    public static String getPluginName() {
        return PLUGIN_NAME;
    }

    public static String getPluginVersion() {
        return PLUGIN_VERSION;
    }

    /**
     * Compares a version array with a version string.
     *
     * @return true when the string version is newer
     */
    public static boolean newVersion(Integer[] currentVersion, String latestVersion) {
        return newVersion(currentVersion, parseVersion(latestVersion));
    }

    public static String getBranch() {
        return "NeoForge";
    }

    public static boolean isCommunityEdition() {
        return true; // The NeoForge port corresponds to the Community Edition.
    }

    public static boolean isSpigot() {
        return false;
    }

    public static boolean isPaper() {
        return false;
    }

    public static boolean isFolia() {
        return false;
    }

    public static boolean checkWorldEdit() {
        return false;
    }

    public static void loadWorldEdit() {
        // WorldEdit integration is not part of the NeoForge port.
    }

    public static void unloadWorldEdit() {
        // WorldEdit integration is not part of the NeoForge port.
    }
}
