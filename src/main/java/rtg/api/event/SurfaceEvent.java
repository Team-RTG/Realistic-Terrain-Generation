package rtg.api.event;


import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.common.eventhandler.Event;

import rtg.api.world.RTGWorld;

public class SurfaceEvent extends Event {

    public SurfaceEvent() {
        super();
    }

    public static class HardcodedBlock extends SurfaceEvent
    {
        private RTGWorld rtgWorld;
        private int chunkX;
        private int chunkZ;
        private int worldX;
        private int worldY;
        private int worldZ;
        private IBlockState block;

        public HardcodedBlock(RTGWorld rtgWorld, int worldX, int worldZ, int chunkX, int chunkZ, int worldY, IBlockState defaultBlock) {

            super();

            this.rtgWorld = rtgWorld;
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.worldX = worldX;
            this.worldY = worldY;
            this.worldZ = worldZ;
            this.block = defaultBlock;
        }

        public RTGWorld getRTGWorld() {

            return rtgWorld;
        }

        public int getChunkX() {

            return chunkX;
        }

        public int getChunkZ() {

            return chunkZ;
        }

        public int getWorldX() {

            return worldX;
        }

        public int getWorldY() {

            return worldY;
        }

        public int getWorldZ() {

            return worldZ;
        }

        public IBlockState getBlock() {

            return block;
        }

        public void setBlock(IBlockState block) {

            this.block = block;
        }
    }
}