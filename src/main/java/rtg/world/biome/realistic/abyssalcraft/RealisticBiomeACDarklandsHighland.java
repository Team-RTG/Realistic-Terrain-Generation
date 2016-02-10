package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsHighland;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsHighland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACDarklandsHighland extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsHighland(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsHighland(10f, 120f, 68f, 200f),
            new SurfaceACDarklandsHighland(acBiome.topBlock, acBiome.fillerBlock, acBiome.topBlock, acBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
        );
        
        this.config = config;
    }
}
