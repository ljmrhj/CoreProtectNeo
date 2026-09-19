package net.coreprotect.consumer;

/**
 * A single queued database write.
 *
 * <p>The original plugin queued {@code Object[]} arrays; the port uses a typed record with the same
 * information so that the write ordering and batching behave like the plugin.
 *
 * @param type      which table the entry is written to
 * @param time      unix timestamp of the action
 * @param user      player name (may be null for natural causes)
 * @param uuid      player uuid (may be null)
 * @param world     dimension id, e.g. "minecraft:overworld"
 * @param x         block x
 * @param y         block y
 * @param z         block z
 * @param materialId material_map id (blocks/items)
 * @param dataId    blockdata_map id (blocks/containers)
 * @param amount    item amount (items/containers)
 * @param message   chat message / command string
 * @param action    CoreProtect action id (1 = removed, 0 = added, or session login/logout)
 * @param blockData raw block data string, e.g. "minecraft:oak_log[axis=y]"
 */
public record ConsumerEntry(ProcessType type, int time, String user, String uuid, String world,
                            int x, int y, int z, int materialId, int dataId, int amount,
                            String message, int action, String blockData, byte[] blob) {

    public static Builder builder(ProcessType type, int time) {
        return new Builder(type, time);
    }

    /** Builder with the original field defaults. */
    public static final class Builder {
        private final ProcessType type;
        private final int time;
        private String user;
        private String uuid;
        private String world;
        private int x;
        private int y;
        private int z;
        private int materialId = -1;
        private int dataId = -1;
        private int amount = 0;
        private String message;
        private int action = 0;
        private String blockData;
        private byte[] blob;

        private Builder(ProcessType type, int time) {
            this.type = type;
            this.time = time;
        }

        public Builder user(String user, String uuid) {
            this.user = user;
            this.uuid = uuid;
            return this;
        }

        public Builder position(String world, int x, int y, int z) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public Builder material(int materialId) {
            this.materialId = materialId;
            return this;
        }

        public Builder data(int dataId) {
            this.dataId = dataId;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder action(int action) {
            this.action = action;
            return this;
        }

        public Builder blockData(String blockData) {
            this.blockData = blockData;
            return this;
        }

        /** Pre-serialized metadata (item stacks, container contents, entity data). */
        public Builder blob(byte[] blob) {
            this.blob = blob;
            return this;
        }

        public ConsumerEntry build() {
            return new ConsumerEntry(type, time, user, uuid, world, x, y, z, materialId, dataId,
                    amount, message, action, blockData, blob);
        }
    }
}
