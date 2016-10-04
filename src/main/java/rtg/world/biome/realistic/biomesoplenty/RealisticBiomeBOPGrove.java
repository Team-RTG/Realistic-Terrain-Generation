package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrove;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGrove;

public class RealisticBiomeBOPGrove extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.grove.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPGrove(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPGrove(),
            new SurfaceBOPGrove(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 0.15f)
        );

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.distribution.noiseDivisor = 80f;
        decoFallenTree1.distribution.noiseFactor = 60f;
        decoFallenTree1.distribution.noiseAddend = -15f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree1.logConditionNoise = 8f;
        decoFallenTree1.logConditionChance = 1;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoFallenTree1.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 6;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.distribution.noiseDivisor = 80f;
        decoFallenTree2.distribution.noiseFactor = 60f;
        decoFallenTree2.distribution.noiseAddend = -15f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree2.logConditionNoise = 8f;
        decoFallenTree2.logConditionChance = 1;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoFallenTree2.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 6;

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050, this.config._boolean(BiomeConfigBOPGrove.decorationLogsId));

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoShrubCustom.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoShrubCustom.maxY = 110;
        decoShrubCustom.strengthFactor = 2f;
        DecoShrub decoShrubCustom2 = new DecoShrub();
        decoShrubCustom2.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoShrubCustom2.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoShrubCustom2.maxY = 110;
        decoShrubCustom2.strengthFactor = 2f;
        DecoHelper5050 decoHelperHelper50502 = new DecoHelper5050(decoShrubCustom, decoShrubCustom2);
        this.addDeco(decoHelperHelper50502);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
