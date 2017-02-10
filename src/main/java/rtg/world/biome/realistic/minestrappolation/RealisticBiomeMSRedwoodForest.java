package rtg.world.biome.realistic.minestrappolation;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.minestrappolation.SurfaceMSRedwoodForest;
import rtg.world.gen.terrain.minestrappolation.TerrainMSRedwoodForest;

public class RealisticBiomeMSRedwoodForest extends RealisticBiomeMSBase {

    public RealisticBiomeMSRedwoodForest(BiomeGenBase msBiome, BiomeConfig config) {

        super(config,
            msBiome,
            BiomeGenBase.river,
            new TerrainMSRedwoodForest(),
            new SurfaceMSRedwoodForest(config, msBiome.topBlock, msBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, msBiome.topBlock, 0.10f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
