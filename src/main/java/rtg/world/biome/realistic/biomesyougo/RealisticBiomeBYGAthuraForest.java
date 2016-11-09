package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGAthuraForest;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGAthuraForest extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState athuraLogBlock = Block.getBlockFromName("BiomesYouGo:AthuraLog").getDefaultState();
    private static IBlockState athuraLeavesBlock = Block.getBlockFromName("BiomesYouGo:AthuraLeaves").getDefaultState();

    public RealisticBiomeBYGAthuraForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesyougo.TerrainBYGAthuraForest(),
            new SurfaceBYGAthuraForest(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                BlockUtil.getStateDirt(2), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                0.35f, //float mixHeight,
                10f, //float smallWidth,
                0.65f //float smallStrength
            )
        );

        DecoShrub decoShrubAthura = new DecoShrub();
        decoShrubAthura.logBlock = athuraLogBlock;
        decoShrubAthura.leavesBlock = athuraLeavesBlock;
        decoShrubAthura.maxY = 90;
        decoShrubAthura.strengthFactor = 4f;
        decoShrubAthura.chance = 8;
        this.addDeco(decoShrubAthura);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 24;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 63;
        decoGrass.maxY = 105;
        decoGrass.loops = 1;
        this.addDeco(decoGrass);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGAthuraForest();
    }

    public class TerrainBYGAthuraForest extends TerrainBase {

        private float baseHeight = 72f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 10f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 20f;

        public TerrainBYGAthuraForest() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

            float h = terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return riverized(groundNoise + h, river);
        }
    }
}
