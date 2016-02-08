package rtg.world.biome.realistic.arsmagica;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.arsmagica.config.BiomeConfigAMWitchwoodForest;
import rtg.config.arsmagica.ConfigAM;
import rtg.world.gen.surface.arsmagica.SurfaceAMWitchwoodForest;
import rtg.world.gen.terrain.arsmagica.TerrainAMWitchwoodForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeAMWitchwoodForest extends RealisticBiomeAMBase
{
    
    public RealisticBiomeAMWitchwoodForest(BiomeGenBase amBiome, BiomeConfig config)
    {
    
        super(
            amBiome, BiomeGenBase.river,
            new TerrainAMWitchwoodForest(),
            new SurfaceAMWitchwoodForest(amBiome.topBlock, amBiome.fillerBlock));
        
        this.config = config;
    }
}
