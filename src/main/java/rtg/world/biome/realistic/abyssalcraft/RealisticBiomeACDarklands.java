package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklands;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklands;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACDarklands extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklands(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklands(),
            new SurfaceACDarklands(acBiome.topBlock, acBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, acBiome.fillerBlock, (byte)0, 0.15f));
        
        this.config = config;
    }
}
