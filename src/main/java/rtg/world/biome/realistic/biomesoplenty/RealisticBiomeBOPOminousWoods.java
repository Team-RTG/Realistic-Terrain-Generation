package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOminousWoods;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOminousWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOminousWoods;

public class RealisticBiomeBOPOminousWoods extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.ominous_woods.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPOminousWoods(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPOminousWoods(65f, 80f, 48f),
            new SurfaceBOPOminousWoods(config, topBlock, fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.randomLogBlocks = new IBlockState[]{BOPBlocks.log_1.getStateFromMeta(2), BOPBlocks.log_3.getStateFromMeta(2)};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPOminousWoods.decorationLogsId));
    }
}
