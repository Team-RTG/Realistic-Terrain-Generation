package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOasis;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOasis;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPOasis extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.oasis.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOasis(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPOasis(),
            new SurfaceBOPOasis(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.SAND.getDefaultState(), //IBlockState mixTop,
                Blocks.SANDSTONE.getDefaultState(), //IBlockState mixFill,
                40f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = BOPBlocks.log_2.getStateFromMeta(3);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPOasis.decorationLogsId));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPOasis();
    }

    public class TerrainBOPOasis extends TerrainBase {

        public TerrainBOPOasis() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 100f, 65f);
        }
    }
}
