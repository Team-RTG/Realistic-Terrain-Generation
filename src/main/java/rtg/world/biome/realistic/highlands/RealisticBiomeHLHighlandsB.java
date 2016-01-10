package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biomes.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLHighlandsB;
import rtg.world.gen.terrain.highlands.TerrainHLHighlandsB;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLHighlandsB extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.highlandsb;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLHighlandsB(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainHLHighlandsB(15f, 60f, 68f, 150f),
            new SurfaceHLHighlandsB(topBlock, fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigHL.weightHLHighlandsB;
        this.generateVillages = ConfigHL.villageHLHighlandsB;
    }
}
