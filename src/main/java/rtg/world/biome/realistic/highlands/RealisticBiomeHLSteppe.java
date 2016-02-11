package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLSteppe;
import rtg.world.gen.terrain.highlands.TerrainHLSteppe;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLSteppe extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.steppe;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLSteppe(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeGenBase.river,
            new TerrainHLSteppe(70f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLSteppe(config, topBlock, fillerBlock));
        
        this.config = config;
    }
}
