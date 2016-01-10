package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biomes.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLWoodlands;
import rtg.world.gen.terrain.highlands.TerrainHLWoodlands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLWoodlands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.woodlands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLWoodlands(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLWoodlands(230f, 40f, 0f),
            new SurfaceHLWoodlands(topBlock, fillerBlock, false, null, 0.95f));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigHL.weightHLWoodlands;
        this.generateVillages = ConfigHL.villageHLWoodlands;
    }
}
