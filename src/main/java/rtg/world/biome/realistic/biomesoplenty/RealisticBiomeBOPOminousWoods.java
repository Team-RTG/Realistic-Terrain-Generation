package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.block.BlockBOPLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOminousWoods;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOminousWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOminousWoods;

public class RealisticBiomeBOPOminousWoods extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.ominous_woods.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOminousWoods(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPOminousWoods(65f, 80f, 48f),
            new SurfaceBOPOminousWoods(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState())
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.randomLogBlocks = new IBlockState[]{BlockBOPLog.paging.getVariantState(BOPWoods.UMBRAN), BlockBOPLog.paging.getVariantState(BOPWoods.DEAD)};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPOminousWoods.decorationLogsId));
    }
}
