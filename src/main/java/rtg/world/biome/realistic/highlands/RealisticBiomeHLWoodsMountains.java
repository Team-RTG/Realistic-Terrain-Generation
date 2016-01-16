package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLWoodsMountains;
import rtg.world.gen.terrain.highlands.TerrainHLWoodsMountains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLWoodsMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.woodsMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLWoodsMountains(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainHLWoodsMountains(230f, 120f, 0f),
            new SurfaceHLWoodsMountains(topBlock, fillerBlock, false, null, 0.95f));
        
        this.config = config;
        this.generateVillages = ConfigHL.villageHLWoodsMountains;
    }
}
