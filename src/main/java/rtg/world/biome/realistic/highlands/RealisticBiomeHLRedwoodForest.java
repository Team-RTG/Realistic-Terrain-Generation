package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLRedwoodForest;
import rtg.world.gen.terrain.highlands.TerrainHLRedwoodForest;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLRedwoodForest extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.redwoodForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLRedwoodForest(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainHLRedwoodForest(40f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLRedwoodForest(topBlock, fillerBlock, false, null, 0.4f));
        
        this.config = config;
        this.generateVillages = ConfigHL.villageHLRedwoodForest;
    }
}
