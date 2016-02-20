package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLForestIsland;
import rtg.world.gen.terrain.highlands.TerrainHLForestIsland;

public class RealisticBiomeHLForestIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.forestIsland;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLForestIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLForestIsland(90f, 180f, 13f, 100f, 1f, 260f, 59f),
            new SurfaceHLForestIsland(config, topBlock, fillerBlock));
    }
}
