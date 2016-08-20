package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMountainFoothills;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMountainFoothills;

public class RealisticBiomeBOPMountainFoothills extends RealisticBiomeBOPBase {

    //TODO: Decidious
    public static Biome biome = BOPBiomes.mountain_foothills.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMountainFoothills(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPMountainFoothills(),
            new SurfaceBOPMountainFoothills(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.DIRT.getDefaultState(), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
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
