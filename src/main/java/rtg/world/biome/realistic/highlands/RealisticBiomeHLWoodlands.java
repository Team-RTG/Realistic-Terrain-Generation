package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLWoodlands;
import rtg.world.gen.terrain.highlands.TerrainHLWoodlands;

public class RealisticBiomeHLWoodlands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.woodlands;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLWoodlands(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLWoodlands(230f, 40f, 0f),
            new SurfaceHLWoodlands(config, topBlock, fillerBlock, false, null, 0.95f));
    }
}
