package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGAthuraForest;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGAthuraForest;

public class RealisticBiomeBYGAthuraForest extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState athuraLogBlock = Block.getBlockFromName("BiomesYouGo:AthuraLog").getDefaultState();
    private static IBlockState athuraLeavesBlock = Block.getBlockFromName("BiomesYouGo:AthuraLeaves").getDefaultState();

    public RealisticBiomeBYGAthuraForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGAthuraForest(),
            new SurfaceBYGAthuraForest(config,
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

        DecoShrub decoShrubAthura = new DecoShrub();
        decoShrubAthura.logBlock = athuraLogBlock;
        decoShrubAthura.leavesBlock = athuraLeavesBlock;
        decoShrubAthura.maxY = 90;
        decoShrubAthura.strengthFactor = 4f;
        decoShrubAthura.chance = 8;
        this.addDeco(decoShrubAthura);

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
