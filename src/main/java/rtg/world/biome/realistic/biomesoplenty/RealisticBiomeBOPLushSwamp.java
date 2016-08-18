package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushSwamp;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushSwamp;

public class RealisticBiomeBOPLushSwamp extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.lush_swamp.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPLushSwamp(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPLushSwamp(),
            new SurfaceBOPLushSwamp(config, topBlock, fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.mossy_cobblestone.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.ALWAYS_GENERATE;
        decoFallenTree.logConditionChance = 4;
        decoFallenTree.logBlock = Blocks.log.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.leaves.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPLushSwamp.decorationLogsId));
    }
}
