package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNRedwoodForestHills;
import rtg.world.gen.terrain.forgottennature.TerrainFNRedwoodForestHills;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNRedwoodForestHills extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNRedwoodForestHills(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNRedwoodForestHills(),
            new SurfaceFNRedwoodForestHills(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f));
    }
}
