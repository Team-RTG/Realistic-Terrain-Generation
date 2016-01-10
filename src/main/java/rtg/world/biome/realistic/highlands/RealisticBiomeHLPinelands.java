package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biomes.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLPinelands;
import rtg.world.gen.terrain.highlands.TerrainHLPinelands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLPinelands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.pinelands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLPinelands(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainHLPinelands(),
            new SurfaceHLPinelands(topBlock, fillerBlock, false, null, 1.2f));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigHL.weightHLPinelands;
        this.generateVillages = ConfigHL.villageHLPinelands;
    }
}
