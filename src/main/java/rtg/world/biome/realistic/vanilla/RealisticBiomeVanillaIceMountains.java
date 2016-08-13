package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIceMountains;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biome.iceMountains.topBlock;
    public static IBlockState fillerBlock = Biome.iceMountains.fillerBlock;

    public RealisticBiomeVanillaIceMountains(BiomeConfig config) {

        super(config,
            Biome.iceMountains,
            Biome.frozenRiver,
            new TerrainVanillaIceMountains(230f, 60f, 68f),
            new SurfaceVanillaIceMountains(config, topBlock, fillerBlock, Blocks.SNOW.getDefaultState(), Blocks.SNOW.getDefaultState(), Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), 60f,
                -0.14f, 14f, 0.25f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
