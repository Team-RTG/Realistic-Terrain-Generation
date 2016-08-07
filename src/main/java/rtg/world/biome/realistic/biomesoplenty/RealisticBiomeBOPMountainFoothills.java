package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMountainFoothills;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMountainFoothills;

public class RealisticBiomeBOPMountainFoothills extends RealisticBiomeBOPBase {

    //TODO: Decidious
    public static BiomeGenBase bopBiome = BOPBiomes.mountain_foothills.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPMountainFoothills(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPMountainFoothills(),
            new SurfaceBOPMountainFoothills(config,
                topBlock, //Block top
                fillerBlock, //Block filler,
                Blocks.dirt.getDefaultState(), //IBlockState mixTop,
                fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
