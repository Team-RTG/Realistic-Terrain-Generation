package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPKelpForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPKelpForest;

public class RealisticBiomeBOPKelpForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.kelp_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPKelpForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPKelpForest(false, -10f, 0f, 0f, 0f, 30f),
            new SurfaceBOPKelpForest(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
