package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdBeach;

import java.util.Random;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.coldBeach.topBlock;
    public static IBlockState fillerBlock = Biomes.coldBeach.fillerBlock;

    public RealisticBiomeVanillaColdBeach(BiomeConfig config) {
        super(config,
                Biomes.coldBeach,
                Biomes.river,
                new TerrainVanillaColdBeach(),
                new SurfaceVanillaColdBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte) 0, 1)
        );
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        // boulders
        for (int l = 0; l < 3f * strength; ++l) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
    }
}
