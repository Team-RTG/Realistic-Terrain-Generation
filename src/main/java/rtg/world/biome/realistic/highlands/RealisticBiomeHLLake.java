package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLLake;
import rtg.world.gen.terrain.highlands.TerrainHLLake;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLLake extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.lake;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLLake(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLLake(),
            new SurfaceHLLake(config, topBlock, fillerBlock));
    }
}
