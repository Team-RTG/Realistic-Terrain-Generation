package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdBeach;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.COLD_BEACH.topBlock;
    public static IBlockState fillerBlock = Biomes.COLD_BEACH.fillerBlock;

    public RealisticBiomeVanillaColdBeach(BiomeConfig config) {

        super(config,
            Biomes.COLD_BEACH,
            Biomes.RIVER,
            new TerrainVanillaColdBeach(),
            new SurfaceVanillaColdBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte) 0, 1)
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);
    }
}
