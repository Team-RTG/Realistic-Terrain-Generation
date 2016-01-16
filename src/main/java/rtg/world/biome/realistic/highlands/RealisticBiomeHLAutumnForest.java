package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLAutumnForest;
import rtg.world.gen.terrain.highlands.TerrainHLAutumnForest;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLAutumnForest extends RealisticBiomeHLBase {
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.autumnForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLAutumnForest(BiomeConfig config) {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainHLAutumnForest(0f, 50f, 68f, 200f),
            new SurfaceHLAutumnForest(topBlock, fillerBlock));
        
        this.config = config;
        this.biomeWeight = ConfigHL.weightHLAutumnForest;
        this.generateVillages = ConfigHL.villageHLAutumnForest;
    }
}
