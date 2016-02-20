package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLMeadow;
import rtg.world.gen.terrain.highlands.TerrainHLMeadow;

public class RealisticBiomeHLMeadow extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.meadow;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLMeadow(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLMeadow(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLMeadow(config, topBlock, fillerBlock));
    }
}
