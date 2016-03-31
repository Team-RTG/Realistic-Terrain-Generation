package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {
    public static BiomeGenBase vanillaBiome = Biomes.river;
    public static IBlockState topBlock = vanillaBiome.topBlock;
    public static IBlockState fillerBlock = vanillaBiome.fillerBlock;

    public RealisticBiomeVanillaRiver() {
        super(
                vanillaBiome,
                Biomes.river,
                new TerrainVanillaRiver(),
                new SurfaceVanillaRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
