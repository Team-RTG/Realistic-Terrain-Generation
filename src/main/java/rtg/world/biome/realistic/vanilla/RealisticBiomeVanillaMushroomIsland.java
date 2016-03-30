package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIsland;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.mushroomIsland.topBlock;
    public static IBlockState fillerBlock = Biomes.mushroomIsland.fillerBlock;

    public RealisticBiomeVanillaMushroomIsland() {
        super(
                Biomes.mushroomIsland,
                Biomes.river,
                new TerrainVanillaMushroomIsland(),
                new SurfaceVanillaMushroomIsland(config, topBlock, fillerBlock, 67, topBlock, 0f)
        );
        this.noLakes = true;
    }
}
