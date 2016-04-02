package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestM;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestM;

public class RealisticBiomeVanillaBirchForestM extends RealisticBiomeVanillaBase {

    public static BiomeGenBase standardBiome = Biomes.birchForest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaBirchForestM(BiomeConfig config) {

        super(config,
                mutationBiome,
                Biomes.river,
                new TerrainVanillaBirchForestM(),
                new SurfaceVanillaBirchForestM(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f)
        );
        this.noLakes = true;

        /**
         * ##################################################
         * # DECORATIONS (ORDER MATTERS)
         * ##################################################
         */

        DecoTree superTallBirch = new DecoTree();
        superTallBirch.strengthFactorForLoops = 16f;
        superTallBirch.strengthNoiseFactorForLoops = true;
        superTallBirch.treeType = TreeType.SUPER_TALL_BIRCH;
        superTallBirch.distribution.noiseDivisor = 80f;
        superTallBirch.distribution.noiseFactor = 60f;
        superTallBirch.distribution.noiseAddend = -15f;
        superTallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
        superTallBirch.maxY = 100;
        this.addDeco(superTallBirch);

        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.maxY = 128;
        decoDoublePlants.strengthFactor = 8f;
        this.addDeco(decoDoublePlants);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 20;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte) 2;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaBirchForestM.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);
    }
}
