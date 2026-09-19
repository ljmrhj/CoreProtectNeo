package net.coreprotect.consumer;

/**
 * Database write types of the consumer queue (mirrors the process list of the original plugin).
 */
public enum ProcessType {
    BLOCK_BREAK,
    BLOCK_PLACE,
    SESSION,
    CHAT,
    COMMAND,
    CONTAINER,
    ITEM,
    ENTITY_KILL,
    WORLD_INSERT,
    MATERIAL_INSERT,
    BLOCKDATA_INSERT,
    ENTITY_INSERT
}
