package net.coreprotect.model;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.level.block.Block;

/**
 * Groups of blocks used for block change semantics (movement, growth, tracking, containers...).
 *
 * <p>The original plugin filled these sets from Bukkit's {@code Material} enum. The NeoForge port
 * keeps the same field names but stores blocks; the sets are populated during the block logging
 * stage of the port.
 */
public final class BlockGroup {

    public static Set<Block> TRACK_ANY = new HashSet<>();
    public static Set<Block> TRACK_TOP_BOTTOM = new HashSet<>();
    public static Set<Block> TRACK_TOP = new HashSet<>();
    public static Set<Block> TRACK_BOTTOM = new HashSet<>();
    public static Set<Block> TRACK_SIDE = new HashSet<>();
    public static Set<Block> SHULKER_BOXES = new HashSet<>();
    public static Set<Block> BUNDLES = new HashSet<>();
    public static Set<Block> CONTAINERS = new HashSet<>();
    public static Set<Block> DOORS = new HashSet<>();
    public static Set<Block> BUTTONS = new HashSet<>();
    public static Set<Block> PRESSURE_PLATES = new HashSet<>();
    public static Set<Block> VINES = new HashSet<>();
    public static Set<Block> AMETHYST = new HashSet<>();
    public static Set<Block> LIGHTABLES = new HashSet<>();
    public static Set<Block> CANDLES = new HashSet<>();
    public static Set<Block> FIRE = new HashSet<>();
    public static Set<Block> LANTERNS = new HashSet<>();
    public static Set<Block> SOUL_BLOCKS = new HashSet<>();
    public static Set<Block> DIRECTIONAL_BLOCKS = new HashSet<>();
    public static Set<Block> INTERACT_BLOCKS = new HashSet<>();
    public static Set<Block> SAFE_INTERACT_BLOCKS = new HashSet<>();
    public static Set<Block> UPDATE_STATE = new HashSet<>();
    public static Set<Block> NATURAL_BLOCKS = new HashSet<>();
    public static Set<Block> SCULK = new HashSet<>();
    public static Set<Block> VERTICAL_TOP_BOTTOM = new HashSet<>();
    public static Set<Block> VERTICAL_TOP = new HashSet<>();
    public static Set<Block> VERTICAL_BOTTOM = new HashSet<>();
    public static Set<Block> VERTICAL = new HashSet<>();
    public static Set<Block> NON_ATTACHABLE = new HashSet<>();

    private BlockGroup() {
        throw new IllegalStateException("Utility class");
    }

    public static void initialize() {
        // Block groups are populated from the block registry during the block logging stage.
    }
}
