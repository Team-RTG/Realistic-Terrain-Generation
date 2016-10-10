package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.block.BlockBOPLog;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSnowyConiferousForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSnowyConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSnowyConiferousForest;

public class RealisticBiomeBOPSnowyConiferousForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.snowy_coniferous_forest.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPSnowyConiferousForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPSnowyConiferousForest(65f, 70f, 40f),
            new SurfaceBOPSnowyConiferousForest(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState(), 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = BlockBOPLog.paging.getVariantState(BOPWoods.FIR);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPSnowyConiferousForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 12;
        this.addDeco(decoBaseBiomeDecorations);
    }
}
