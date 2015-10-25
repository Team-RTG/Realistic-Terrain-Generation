package rtg.world.gen.surface.enhancedbiomes;

import rtg.world.gen.surface.SurfaceBase;
import enhancedbiomes.EnhancedBiomesMod;

import net.minecraft.block.Block;

public class SurfaceEBBase extends SurfaceBase
{
    
    public SurfaceEBBase(Block top, Block fill)
    {
    
        super(top, fill);
    }
    
    public boolean shouldReplaceStone() {
    
        return (EnhancedBiomesMod.useNewStone == 1) ? true : false;
    }
}
