package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.config.BiomeConfigProperty;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaM;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaColdTaigaM extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.coldTaiga;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaColdTaigaM() {

        super(
                mutationBiome,
                Biomes.frozenRiver
        );
        this.noLakes = true;

        /**
         * ##################################################
         * # DECORATIONS (ORDER MATTERS)
         * ##################################################
         */

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);

        DecoTree decoTrees = new DecoTree();
        decoTrees.strengthFactorForLoops = 4f;
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.distribution.noiseDivisor = 100f;
        decoTrees.distribution.noiseFactor = 6f;
        decoTrees.distribution.noiseAddend = 0.8f;
        decoTrees.treeType = TreeType.VANILLA_COLD_TAIGA;
        decoTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
        decoTrees.maxY = 110;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte) 1;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigProperty.DECORATION_LOG));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.equalsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.maxY = 90;
        decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
        decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaColdTaigaM(config, topBlock, fillerBlock);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
            }
        };
    }
}
