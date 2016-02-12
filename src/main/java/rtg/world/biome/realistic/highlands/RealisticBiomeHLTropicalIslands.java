package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLTropicalIslands;
import rtg.world.gen.terrain.highlands.TerrainHLTropicalIslands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLTropicalIslands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tropicalIslands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTropicalIslands(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLTropicalIslands(90f, 180f, 13f, 100f, 1f, 260f, 59f),
            new SurfaceHLTropicalIslands(config, topBlock, fillerBlock));
        
        this.config = config;
    }
}
