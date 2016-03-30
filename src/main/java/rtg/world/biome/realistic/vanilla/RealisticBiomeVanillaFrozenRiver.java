package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenRiver;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.frozenRiver.topBlock;
    public static IBlockState fillerBlock = Biomes.frozenRiver.fillerBlock;

    public RealisticBiomeVanillaFrozenRiver() {
        super(
                Biomes.frozenRiver,
                Biomes.frozenRiver,
                new TerrainVanillaFrozenRiver(),
                new SurfaceVanillaFrozenRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
