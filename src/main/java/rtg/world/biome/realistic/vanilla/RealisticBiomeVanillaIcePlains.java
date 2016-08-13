package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlains;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Blocks.SNOW.getDefaultState();
    public static IBlockState fillerBlock = Blocks.SNOW.getDefaultState();

    public RealisticBiomeVanillaIcePlains(BiomeConfig config) {

        super(config,
            Biome.icePlains,
            Biome.frozenRiver,
            new TerrainVanillaIcePlains(),
            new SurfaceVanillaIcePlains(config, topBlock, fillerBlock, topBlock, topBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

//        DecoBoulder decoBoulder = new DecoBoulder();
//        decoBoulder.checkRiver = true;
//        decoBoulder.minRiver = 0.87f;
//        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
//        decoBoulder.chance = 16;
//        decoBoulder.maxY = 95;
//        decoBoulder.strengthFactor = 5f;
//        this.addDeco(decoBoulder);
//
//        DecoFallenTree decoFallenTree = new DecoFallenTree();
//        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
//        decoFallenTree.logConditionChance = 24;
//        decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(1);
//        decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
//        decoFallenTree.minSize = 1;
//        decoFallenTree.maxSize = 5;
//        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaIcePlains.decorationLogsId));
    }
}
