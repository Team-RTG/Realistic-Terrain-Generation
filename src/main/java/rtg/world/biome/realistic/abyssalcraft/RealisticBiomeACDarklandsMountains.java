package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsMountains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsMountains;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsMountains(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            Biomes.river,
            new TerrainACDarklandsMountains(),
            new SurfaceACDarklandsMountains(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0.2f));
    }
}
