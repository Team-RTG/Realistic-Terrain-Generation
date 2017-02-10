package rtg.world.biome.realistic.afraidofthedark;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.afraidofthedark.SurfaceAOTDErieForest;
import rtg.world.gen.terrain.afraidofthedark.TerrainAOTDErieForest;

public class RealisticBiomeAOTDErieForest extends RealisticBiomeAOTDBase {

    public RealisticBiomeAOTDErieForest(BiomeGenBase aotdBiome, BiomeConfig config) {

        super(config,
            aotdBiome, BiomeGenBase.river,
            new TerrainAOTDErieForest(68f, 80f, 30f),
            new SurfaceAOTDErieForest(config, aotdBiome.topBlock, aotdBiome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
