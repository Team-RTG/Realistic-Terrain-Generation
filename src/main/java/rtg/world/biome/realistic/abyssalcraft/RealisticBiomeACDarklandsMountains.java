package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsMountains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsMountains;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsMountains(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsMountains(),
            new SurfaceACDarklandsMountains(acBiome.topBlock, acBiome.fillerBlock));
        
        this.config = config;
    }
}
