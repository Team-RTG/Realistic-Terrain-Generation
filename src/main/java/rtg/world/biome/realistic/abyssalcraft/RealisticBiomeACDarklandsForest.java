package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsForest;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACDarklandsForest extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsForest(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsForest(),
            new SurfaceACDarklandsForest(acBiome.topBlock, acBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, acBiome.fillerBlock, (byte)0, 0.10f));
        
        this.config = config;
    }
}
