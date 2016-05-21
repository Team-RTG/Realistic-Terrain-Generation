package rtg.world.gen.surface.highlands;

import net.minecraft.block.Block;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPVolcano;

public class SurfaceHLVolcanoIsland extends SurfaceBOPVolcano
{
    public SurfaceHLVolcanoIsland(BiomeConfig config, Block top, byte topByte, Block filler, byte fillerByte, Block mixTop, byte mixTopByte, Block mixFiller,
            byte mixFillerByte, float mixWidth, float mixHeight, float smallWidth, float smallStrength)
    {
    
        super(config, top, topByte, filler, fillerByte, mixTop, mixTopByte, mixFiller, mixFillerByte, mixWidth, mixHeight, smallWidth, smallStrength);
    }
}
