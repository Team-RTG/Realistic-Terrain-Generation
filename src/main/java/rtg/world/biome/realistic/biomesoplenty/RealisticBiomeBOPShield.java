package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShield;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShield;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShield;

public class RealisticBiomeBOPShield extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.shield.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPShield(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPShield(0f, 100f, 68f, 170f),
            new SurfaceBOPShield(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.distribution.noiseDivisor = 80f;
        decoFallenTree1.distribution.noiseFactor = 60f;
        decoFallenTree1.distribution.noiseAddend = -15f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree1.logConditionNoise = 0f;
        decoFallenTree1.logConditionChance = 6;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = BOPBlocks.log_4.getDefaultState();
        decoFallenTree1.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 4;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.distribution.noiseDivisor = 80f;
        decoFallenTree2.distribution.noiseFactor = 60f;
        decoFallenTree2.distribution.noiseAddend = -15f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree2.logConditionNoise = 0f;
        decoFallenTree2.logConditionChance = 6;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoFallenTree2.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 4;

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050, this.config._boolean(BiomeConfigBOPShield.decorationLogsId));
    }
}
