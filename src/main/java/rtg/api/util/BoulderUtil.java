package rtg.api.util;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.common.MinecraftForge;

import rtg.api.event.SurfaceEvent;

public class BoulderUtil {

    public IBlockState getBoulderBlock(IBlockState defaultBlock, int worldX, int worldY, int worldZ) {

        SurfaceEvent.BoulderBlock event = new SurfaceEvent.BoulderBlock(
            worldX, worldY, worldZ, defaultBlock
        );
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        return event.getBlock();
    }
}