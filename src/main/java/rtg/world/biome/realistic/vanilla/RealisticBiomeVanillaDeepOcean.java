package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDeepOcean;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.deepOcean.topBlock;
    public static IBlockState fillerBlock = Biomes.deepOcean.fillerBlock;

    public RealisticBiomeVanillaDeepOcean(BiomeConfig config) {

        super(config,
                Biomes.deepOcean,
                Biomes.river,
                new TerrainVanillaDeepOcean(),
                new SurfaceVanillaDeepOcean(config, Blocks.gravel.getDefaultState(), Blocks.gravel.getDefaultState(), Blocks.clay.getDefaultState(), 20f, 0.1f));

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
    }
}
