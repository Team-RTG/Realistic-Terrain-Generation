package teamrtg.rtg.world.biome;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderOverworld;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;

/**
 * @author topisani
 */
public class RealisticBiomeFaker {

    private final ChunkProviderOverworld fakeProvider;
    public static final RealisticBiomeBase[] fakeBiomes = new RealisticBiomeBase[256];

    public RealisticBiomeFaker(World world) {
        fakeProvider = new ChunkProviderOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());
    }

    public void convertBiomes(int cx, int cz, ChunkPrimer primer) {
        fakeProvider.setBlocksInChunk(cx, cz, primer);
    }

    public boolean isFakeBiome(int id) {
        return fakeBiomes[id] != null;
    }

    public int getHeightAt(int x, int z, ChunkPrimer primer) {
        for (int y = 255; y >= 0; --y) {
            IBlockState iblockstate = primer.getBlockState(x, y, z);

            if (iblockstate != null && iblockstate != Blocks.air.getDefaultState()) {
                return y;
            }
        }
        return 0;
    }

    public void generateSurfaces(int cx, int cz, ChunkPrimer primer, BiomeGenBase[] biomes) {
        fakeProvider.replaceBiomeBlocks(cx, cz, primer, biomes);
    }

    public static RealisticBiomeBase getFakeBiome(int id) {
        if (fakeBiomes[id] == null) {
            fakeBiomes[id] = new RealisticFakeBiome(Mods.RTG, BiomeGenBase.getBiome(id));
            new RealisticBiomeGenerator(fakeBiomes[id]);
        }
        return fakeBiomes[id];
    }
}
