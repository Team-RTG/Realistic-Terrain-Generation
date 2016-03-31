package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.plains.topBlock;
    public static IBlockState fillerBlock = Biomes.plains.fillerBlock;

    public RealisticBiomeVanillaPlains() {

        super(
                Biomes.plains,
                Biomes.river,
                new TerrainVanillaPlains(),
                new SurfaceVanillaPlains(config, topBlock, fillerBlock));
    }
}
