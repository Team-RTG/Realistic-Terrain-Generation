package rtg.world.gen.surface.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.SurfaceBase;
import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;

public class SurfaceEBBase extends SurfaceBase
{
    
    public SurfaceEBBase(BiomeConfig config, Block top, Block fill)
    {
    
        super(config, top, fill);
    }
    
    public boolean shouldReplaceStone() {
    
        return (EnhancedBiomesMod.useNewStone == 1) ? true : false;
    }
}
