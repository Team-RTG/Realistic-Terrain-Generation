package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.block.BlockBOPLog;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOasis;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOasis;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOasis;

public class RealisticBiomeBOPOasis extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.oasis.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOasis(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPOasis(),
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
        decoFallenTree.logBlock = BlockBOPLog.paging.getVariantState(BOPWoods.PALM);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPOasis.decorationLogsId));
    }
}
