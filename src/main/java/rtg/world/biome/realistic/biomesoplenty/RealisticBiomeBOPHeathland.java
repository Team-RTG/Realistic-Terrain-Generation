package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPHeathland;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHeathland;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPHeathland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.heathland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPHeathland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPHeathland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree.logConditionNoise = 8f;
        decoFallenTree.logConditionChance = 1;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPHeathland.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPHeathland();
    }

    public class TerrainBOPHeathland extends TerrainBase {

        private float baseHeight = 66f;
        private HillockEffect hills;

        public TerrainBOPHeathland() {

            hills = new HillockEffect();
            hills.height = 25;
            hills.minimumSimplex = 0.3f;
            hills.octave = 0;
            hills.wavelength = 50f;

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float added = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);
            added += hills.added(simplex, cell, x, y);
            return riverized(baseHeight + added, river);
        }
    }
}
