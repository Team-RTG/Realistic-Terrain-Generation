package rtg.world.biome.realistic.vampirism;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vampirism.SurfaceVAMPVampireForest;
import rtg.world.gen.terrain.vampirism.TerrainVAMPVampireForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVAMPVampireForest extends RealisticBiomeVAMPBase
{
    
    public RealisticBiomeVAMPVampireForest(BiomeGenBase vampBiome, BiomeConfig config)
    {
    
        super(config,
            vampBiome, BiomeGenBase.river,
            new TerrainVAMPVampireForest(),
            new SurfaceVAMPVampireForest(config, vampBiome.topBlock, vampBiome.fillerBlock));
    }
}
