package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLSnowIsland;
import rtg.world.gen.terrain.highlands.TerrainHLSnowIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLSnowIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.snowIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLSnowIsland(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLSnowIsland(90f, 180f, 13f, 100f, 1f, 260f, 70f),
            new SurfaceHLSnowIsland(config, topBlock, fillerBlock));
        
        this.config = config;
    }
}
