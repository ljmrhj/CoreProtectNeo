package net.coreprotect.utility;

public final class Color {

    /** Chrome color character used by legacy Minecraft formatting codes. */
    public static final char COLOR_CHAR = '\u00A7';

    // we define our own constants here to eliminate string concatenation
    // javadoc taken from org.bukkit.ChatColor

    /**
     * Represents black.
     */
    public static final String BLACK = "\u00A70";

    /**
     * Represents dark blue.
     */
    public static final String DARK_BLUE = "\u00A71";

    /**
     * Represents dark green.
     */
    public static final String DARK_GREEN = "\u00A72";

    /**
     * Represents dark blue (aqua).
     */
    public static String DARK_AQUA = "\u00A73";

    /**
     * Represents dark red.
     */
    public static final String DARK_RED = "\u00A74";

    /**
     * Represents dark purple.
     */
    public static final String DARK_PURPLE = "\u00A75";

    /**
     * Represents gold.
     */
    public static final String GOLD = "\u00A76";

    /**
     * Represents grey.
     */
    public static final String GREY = "\u00A77";

    /**
     * Represents dark grey.
     */
    public static final String DARK_GREY = "\u00A78";

    /**
     * Represents blue.
     */
    public static final String BLUE = "\u00A79";

    /**
     * Represents green.
     */
    public static final String GREEN = "\u00A7a";

    /**
     * Represents aqua.
     */
    public static final String AQUA = "\u00A7b";

    /**
     * Represents red.
     */
    public static final String RED = "\u00A7c";

    /**
     * Represents light purple.
     */
    public static final String LIGHT_PURPLE = "\u00A7d";

    /**
     * Represents yellow.
     */
    public static final String YELLOW = "\u00A7e";

    /**
     * Represents white.
     */
    public static final String WHITE = "\u00A7f";

    /**
     * Represents magical characters that change around randomly.
     */
    public static final String MAGIC = COLOR_CHAR + "k";

    /**
     * Makes the text bold.
     */
    public static final String BOLD = COLOR_CHAR + "l";

    /**
     * Makes a line appear through the text.
     */
    public static final String STRIKETHROUGH = COLOR_CHAR + "m";

    /**
     * Makes the text appear underlined.
     */
    public static final String UNDERLINE = COLOR_CHAR + "n";

    /**
     * Makes the text italic.
     */
    public static final String ITALIC = COLOR_CHAR + "o";

    /**
     * Resets all previous chat colors or formats.
     */
    public static final String RESET = COLOR_CHAR + "r";

    private Color() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Translates alternate color codes (e.g. '&a') into legacy section-sign codes.
     */
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        if (textToTranslate == null) {
            return null;
        }
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    /**
     * Removes legacy color/formatting codes from a string.
     */
    public static String stripColor(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("(?i)" + COLOR_CHAR + "[0-9A-FK-ORX]", "");
    }
}

