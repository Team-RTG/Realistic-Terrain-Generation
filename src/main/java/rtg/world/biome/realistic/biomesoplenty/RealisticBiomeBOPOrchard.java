package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOrchard;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOrchard;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOrchard;

public class
RealisticBiomeBOPOrchard extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.orchard.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPOrchard(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPOrchard(58f, 67f, 25f),
            new SurfaceBOPOrchard(config, topBlock, fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 10;
        decoFallenTree.logBlock = Blocks.log.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.leaves.getDefaultState();
        decoFallenTree.minSize = 2;
        decoFallenTree.maxSize = 3;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPOrchard.decorationLogsId));
    }
}
