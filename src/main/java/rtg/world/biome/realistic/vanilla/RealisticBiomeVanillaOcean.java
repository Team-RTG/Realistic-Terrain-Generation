package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaOcean;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.ocean.topBlock;
    public static IBlockState fillerBlock = Biomes.ocean.fillerBlock;

    public RealisticBiomeVanillaOcean() {

        super(
                Biomes.ocean,
                Biomes.river,
                new TerrainVanillaOcean(),
                new SurfaceVanillaOcean(config, Blocks.sand.getDefaultState(), Blocks.sand.getDefaultState(), Blocks.gravel.getDefaultState(), 20f, 0.2f));

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
    }
}
