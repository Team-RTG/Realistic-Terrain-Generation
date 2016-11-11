package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBorealForest;
import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPBorealForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.boreal_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBorealForest(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPBorealForest(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBorealForest();
    }

    public class TerrainBOPBorealForest extends TerrainBase {

        private float baseHeight = 64f;
        private float hillStrength = 50f;
        private BumpyHillsEffect hillEffect;
        private float hillWidth = 60f;
        private float hillBumpyness = 10f;
        private float hillBumpynessWidth = 20f;

        public TerrainBOPBorealForest() {

            hillEffect = new BumpyHillsEffect();
            hillEffect.hillHeight = hillStrength;
            hillEffect.hillWavelength = hillWidth;
            hillEffect.spikeHeight = hillBumpyness;
            hillEffect.spikeWavelength = this.hillBumpynessWidth;
        }

        public TerrainBOPBorealForest(float bh, float hs) {

            this();
            baseHeight = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);


            float m = hillEffect.added(simplex, cell, x, y);

            return riverized(baseHeight, river) + (groundNoise + m) * river;
        }
    }
}
