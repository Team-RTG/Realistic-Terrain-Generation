package rtg.world.biome.realistic.arsmagica;

import rtg.api.biomes.BiomeConfig;
import rtg.api.biomes.arsmagica.config.BiomeConfigAMWitchwoodForest;
import rtg.config.arsmagica.ConfigAM;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.arsmagica.SurfaceAMWitchwoodForest;
import rtg.world.gen.terrain.arsmagica.TerrainAMWitchwoodForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeAMWitchwoodForest extends RealisticBiomeAMBase
{
    
    public RealisticBiomeAMWitchwoodForest(BiomeGenBase amBiome, BiomeConfig config)
    {
    
        super(
            amBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainAMWitchwoodForest(),
            new SurfaceAMWitchwoodForest(amBiome.topBlock, amBiome.fillerBlock));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigAM.weightAMWitchwoodForest;
        this.generateVillages = ConfigAM.villageAMWitchwoodForest;
    }
}
