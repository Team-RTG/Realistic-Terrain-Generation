package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLSavannah;
import rtg.world.gen.terrain.highlands.TerrainHLSavannah;

public class RealisticBiomeHLSavannah extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.savannah;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLSavannah(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLSavannah(),
            new SurfaceHLSavannah(config, topBlock, fillerBlock));
    }
}
