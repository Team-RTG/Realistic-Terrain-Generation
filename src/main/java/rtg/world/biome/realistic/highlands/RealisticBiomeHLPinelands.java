package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.gen.surface.highlands.SurfaceHLPinelands;
import rtg.world.gen.terrain.highlands.TerrainHLPinelands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLPinelands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.pinelands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLPinelands(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeGenBase.river,
            new TerrainHLPinelands(),
            new SurfaceHLPinelands(topBlock, fillerBlock, false, null, 1.2f));
        
        this.config = config;
    }
}
