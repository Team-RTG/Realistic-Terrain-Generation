package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.frozenOcean.topBlock;
    public static IBlockState fillerBlock = Biomes.frozenOcean.fillerBlock;

    public RealisticBiomeVanillaFrozenOcean() {

        super(
                Biomes.frozenOcean,
                Biomes.river,
                new TerrainVanillaFrozenOcean(),
                new SurfaceVanillaFrozenOcean(config, Blocks.sand.getDefaultState(), Blocks.sand.getDefaultState(), Blocks.gravel.getDefaultState(), 20f, 0.2f));

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
    }
}
