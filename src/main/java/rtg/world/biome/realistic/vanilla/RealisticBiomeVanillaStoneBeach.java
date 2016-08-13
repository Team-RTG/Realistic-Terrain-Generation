package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaStoneBeach;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.STONE_BEACH.topBlock;
    public static IBlockState fillerBlock = Biomes.STONE_BEACH.fillerBlock;

    public RealisticBiomeVanillaStoneBeach(BiomeConfig config) {

        super(config,
            Biomes.STONE_BEACH,
            Biomes.RIVER,
            new TerrainVanillaStoneBeach(),
            new SurfaceVanillaStoneBeach(config, topBlock, fillerBlock, true, Blocks.GRAVEL.getDefaultState(), 1f, 1.5f, 85f, 20f, 4f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
