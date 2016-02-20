package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLWoodsMountains;
import rtg.world.gen.terrain.highlands.TerrainHLWoodsMountains;

public class RealisticBiomeHLWoodsMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.woodsMountains;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLWoodsMountains(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLWoodsMountains(230f, 120f, 0f),
            new SurfaceHLWoodsMountains(config, topBlock, fillerBlock, false, null, 2.0f));
    }
}
