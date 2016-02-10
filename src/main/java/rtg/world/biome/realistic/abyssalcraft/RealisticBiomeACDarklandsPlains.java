package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsPlains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsPlains;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACDarklandsPlains extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsPlains(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsPlains(),
            new SurfaceACDarklandsPlains(acBiome.topBlock, acBiome.fillerBlock));
        
        this.config = config;
    }
}
