package net.coreprotect.thread;

import net.coreprotect.neoforge.NeoPlatform;

/**
 * NeoForge replacement for the Bukkit scheduler used by the original plugin.
 */
public final class Scheduler {

    private Scheduler() {
        throw new IllegalStateException("Utility class");
    }

    public static void runTask(Object plugin, Runnable runnable) {
        NeoPlatform.runSync(runnable);
    }

    public static void runTaskAsynchronously(Object plugin, Runnable runnable) {
        NeoPlatform.runAsync(runnable);
    }

    public static void scheduleSyncDelayedTask(Object plugin, Runnable runnable, long delay) {
        if (delay <= 0) {
            NeoPlatform.runSync(runnable);
            return;
        }
        NeoPlatform.runAsync(() -> {
            try {
                Thread.sleep(delay * 50L);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            NeoPlatform.runSync(runnable);
        });
    }
}
