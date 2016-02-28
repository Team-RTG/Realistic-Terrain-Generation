package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNCherryBlossomWoodland;
import rtg.world.gen.terrain.forgottennature.TerrainFNCherryBlossomWoodland;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNCherryBlossomWoodland extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNCherryBlossomWoodland(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNCherryBlossomWoodland(),
            new SurfaceFNCherryBlossomWoodland(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f));
    }
}
