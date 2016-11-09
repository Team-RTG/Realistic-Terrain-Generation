package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTropicalIsland;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalIsland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPTropicalIsland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.tropical_island.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPTropicalIsland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPTropicalIsland(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.SAND.getDefaultState(), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                10f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                5f, //float smallWidth, 
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
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.logBlock = BOPBlocks.log_2.getStateFromMeta(3);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPTropicalIsland.decorationLogsId));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPTropicalIsland();
    }

    public class TerrainBOPTropicalIsland extends TerrainBase {

        public TerrainBOPTropicalIsland() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainBeach(x, y, simplex, river, 160f, 30f, 65f);
        }
    }
}
