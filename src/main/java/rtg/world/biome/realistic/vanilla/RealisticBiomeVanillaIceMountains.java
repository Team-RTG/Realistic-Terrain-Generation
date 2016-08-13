package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIceMountains;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.ICE_MOUNTAINS.topBlock;
    public static IBlockState fillerBlock = Biomes.ICE_MOUNTAINS.fillerBlock;

    public RealisticBiomeVanillaIceMountains(BiomeConfig config) {

        super(config,
            Biomes.ICE_MOUNTAINS,
            Biomes.FROZEN_RIVER,
            new TerrainVanillaIceMountains(230f, 60f, 68f),
            new SurfaceVanillaIceMountains(config, topBlock, fillerBlock, Blocks.SNOW.getDefaultState(), Blocks.SNOW.getDefaultState(), Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), 60f,
                -0.14f, 14f, 0.25f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
