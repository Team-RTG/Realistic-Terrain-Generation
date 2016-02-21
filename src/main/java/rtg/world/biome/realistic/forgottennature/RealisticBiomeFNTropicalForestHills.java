package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNTropicalForestHills;
import rtg.world.gen.terrain.forgottennature.TerrainFNTropicalForestHills;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNTropicalForestHills extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNTropicalForestHills(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNTropicalForestHills(),
            new SurfaceFNTropicalForestHills(config, fnBiome.topBlock, fnBiome.fillerBlock));
    }
}
