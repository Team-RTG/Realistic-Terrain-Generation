package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.gen.surface.highlands.SurfaceHLRainforest;
import rtg.world.gen.terrain.highlands.TerrainHLRainforest;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLRainforest extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.rainforest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLRainforest(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeGenBase.river,
            new TerrainHLRainforest(120f, 300f,8f),
            new SurfaceHLRainforest(topBlock, fillerBlock, false, null, 1.3f));
        
        this.config = config;
    }
}
