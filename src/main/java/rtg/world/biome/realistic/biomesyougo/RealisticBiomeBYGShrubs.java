package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYGShrubs;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGShrubs;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGShrubs;

public class RealisticBiomeBYGShrubs extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGShrubs(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGShrubs(),
            new SurfaceBYGShrubs(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                BlockUtil.getStateDirt(2), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                0.35f, //float mixHeight,
                10f, //float smallWidth,
                0.65f //float smallStrength
            )
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 2;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBYGShrubs.decorationLogsId));

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 24;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 63;
        decoGrass.maxY = 105;
        decoGrass.loops = 1;
        this.addDeco(decoGrass);
    }
}
