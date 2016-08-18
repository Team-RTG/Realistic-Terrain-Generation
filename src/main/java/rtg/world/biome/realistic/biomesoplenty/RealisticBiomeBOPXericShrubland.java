package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPXericShrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPXericShrubland;

public class RealisticBiomeBOPXericShrubland extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.xeric_shrubland.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPXericShrubland(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPXericShrubland(),
            new SurfaceBOPXericShrubland(config,
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
