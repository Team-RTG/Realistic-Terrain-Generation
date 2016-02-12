package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLBog;
import rtg.world.gen.terrain.highlands.TerrainHLBog;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBog extends RealisticBiomeHLBase
{
    
    public RealisticBiomeHLBog(BiomeConfig config)
    {
        super(config, 
            HighlandsBiomes.bog, BiomeGenBase.river,
            new TerrainHLBog(),
            new SurfaceHLBog(config, HighlandsBiomes.bog.topBlock, HighlandsBiomes.bog.fillerBlock));
    }
}
