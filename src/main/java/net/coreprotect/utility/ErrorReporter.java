package net.coreprotect.utility;

import net.coreprotect.neoforge.NeoPlatform;

/**
 * NeoForge replacement for the plugin's error reporter.
 *
 * <p>The original reported errors to an external service; the port only logs them locally.
 */
public final class ErrorReporter {

    private ErrorReporter() {
        throw new IllegalStateException("Utility class");
    }

    public static void report(Throwable throwable) {
        if (throwable == null) {
            return;
        }
        NeoPlatform.LOGGER.error("[CoreProtect] An error occurred", throwable);
    }
}
