package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSnowyConiferousForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSnowyConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSnowyConiferousForest;

public class RealisticBiomeBOPSnowyConiferousForest extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.snowy_coniferous_forest.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPSnowyConiferousForest(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.frozenRiver,
            new TerrainBOPSnowyConiferousForest(65f, 70f, 40f),
            new SurfaceBOPSnowyConiferousForest(config, topBlock, fillerBlock, false, null, 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone.getDefaultState();
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
        decoFallenTree.logBlock = BOPBlocks.log_1.getStateFromMeta(3);
        decoFallenTree.leavesBlock = Blocks.leaves.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPSnowyConiferousForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 12;
        this.addDeco(decoBaseBiomeDecorations);
    }
}
