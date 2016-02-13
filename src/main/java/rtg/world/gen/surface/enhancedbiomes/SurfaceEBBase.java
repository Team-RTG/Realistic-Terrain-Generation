package rtg.world.gen.surface.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.SurfaceBase;
import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;

public class SurfaceEBBase extends SurfaceBase
{
    
    public SurfaceEBBase(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte)
    {
        super(config, top, topByte, fill, fillByte);
    }
    
    public boolean shouldReplaceStone() {
    
        return (EnhancedBiomesMod.useNewStone == 1) ? true : false;
    }
}
