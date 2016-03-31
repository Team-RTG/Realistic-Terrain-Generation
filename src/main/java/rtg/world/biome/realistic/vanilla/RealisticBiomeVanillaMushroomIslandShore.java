package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.mushroomIslandShore.topBlock;
    public static IBlockState fillerBlock = Biomes.mushroomIslandShore.fillerBlock;

    public RealisticBiomeVanillaMushroomIslandShore() {
        super(
                Biomes.mushroomIslandShore,
                Biomes.river,
                new TerrainVanillaMushroomIslandShore(),
                new SurfaceVanillaMushroomIslandShore(config, topBlock, fillerBlock, 67, topBlock, 0f)
        );
        this.noLakes = true;
    }
}
