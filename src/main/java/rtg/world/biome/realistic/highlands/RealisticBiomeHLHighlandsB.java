package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLHighlandsB;
import rtg.world.gen.terrain.highlands.TerrainHLHighlandsB;

public class RealisticBiomeHLHighlandsB extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.highlandsb;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLHighlandsB(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLHighlandsB(15f, 60f, 68f, 150f),
            new SurfaceHLHighlandsB(config, topBlock, fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f));
    }
}
