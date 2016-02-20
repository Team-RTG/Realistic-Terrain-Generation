package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLBadlands;
import rtg.world.gen.terrain.highlands.TerrainHLBadlands;

public class RealisticBiomeHLBadlands extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.badlands;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLBadlands(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLBadlands(),
            new SurfaceHLBadlands(config, topBlock, fillerBlock));
    }
}
