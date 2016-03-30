package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaStoneBeach;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.stoneBeach.topBlock;
    public static IBlockState fillerBlock = Biomes.stoneBeach.fillerBlock;

    public RealisticBiomeVanillaStoneBeach() {
        super(
                Biomes.stoneBeach,
                Biomes.river,
                new TerrainVanillaStoneBeach(),
                new SurfaceVanillaStoneBeach(config, topBlock, fillerBlock, true, Blocks.gravel.getDefaultState(), 1f, 1.5f, 85f, 20f, 4f)
        );
    }
}
