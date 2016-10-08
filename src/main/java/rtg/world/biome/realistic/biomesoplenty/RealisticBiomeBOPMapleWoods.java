package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMapleWoods;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMapleWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMapleWoods;

public class RealisticBiomeBOPMapleWoods extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.maple_woods.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMapleWoods(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPMapleWoods(68f, 80f, 30f),
            new SurfaceBOPMapleWoods(config, biome.topBlock, biome.fillerBlock)
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree.logConditionNoise = 8f;
        decoFallenTree.logConditionChance = 1;
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.LOG.getDefaultState(), BlockUtil.getStateLog(1)};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPMapleWoods.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
