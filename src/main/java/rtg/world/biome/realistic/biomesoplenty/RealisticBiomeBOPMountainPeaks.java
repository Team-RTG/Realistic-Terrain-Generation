package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMountainPeaks;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMountain;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMountainPeaks;

public class RealisticBiomeBOPMountainPeaks extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.mountain.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPMountainPeaks(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPMountainPeaks(120f, 100f),
            new SurfaceBOPMountain(config,
                topBlock, //Block top
                fillerBlock, //Block filler,
                topBlock, //IBlockState mixTop,
                fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );

        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone.getDefaultState();
        decoBoulder.maxY = 90;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.logBlock = BOPBlocks.log_4.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.leaves.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPMountainPeaks.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 10;
        this.addDeco(decoShrub);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 3f;
        this.addDeco(decoGrass);
    }
}
