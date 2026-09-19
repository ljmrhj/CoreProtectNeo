package net.coreprotect.listener.player;

/**
 * Placeholder for the container transaction listener.
 *
 * <p>Container logging is ported with the NeoForge event listeners in a later stage.
 */
public final class InventoryChangeListener {

    private InventoryChangeListener() {
        throw new IllegalStateException("Utility class");
    }

    public static void flushPendingContainer(Object location, Object contents) {
        // Implemented with the container listeners.
    }

    public static void queueContainerBreak(String user, Object location, Object type, Object contents) {
        // Implemented with the container listeners.
    }
}
