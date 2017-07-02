package rtg.api.world.biome;

import java.lang.reflect.Field;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderOverworld;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import rtg.api.util.LimitedMap;
import rtg.api.util.Logger;
import rtg.api.util.PlaneLocation;
import rtg.api.world.IRTGWorld;
import static rtg.api.util.MathUtils.globalToChunk;
import static rtg.api.util.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class OrganicBiomeGenerator {

    public static boolean[] organicBiomes = new boolean[256];
    private final ChunkProviderOverworld organicProvider;
    private LimitedMap<PlaneLocation.Invariant, int[]> chunkHeights = new LimitedMap<>(64); //Keep the heights for the last 64 chunks around for a bit. We might need them
    private IRTGWorld rtgWorld;
    private NoiseGeneratorPerlin surfaceNoise;

    public OrganicBiomeGenerator(IRTGWorld rtgWorld) {
        this.rtgWorld = rtgWorld;
        organicProvider = new ChunkProviderOverworld(rtgWorld.world(), rtgWorld.world().getSeed(), rtgWorld.world().getWorldInfo().isMapFeaturesEnabled(), rtgWorld.world().getWorldInfo().getGeneratorOptions());

        Field field;
        try {
            // Obf name
            field = organicProvider.getClass().getDeclaredField("field_185994_m");
            field.setAccessible(true);
            this.surfaceNoise = (NoiseGeneratorPerlin) field.get(organicProvider);
        } catch (Exception e) {
            // Either we are in a dev environment or something is very wrong
            try {
                // Deobf name
                field = organicProvider.getClass().getDeclaredField("surfaceNoise");
                field.setAccessible(true);
                this.surfaceNoise = (NoiseGeneratorPerlin) field.get(organicProvider);
            } catch (Exception e2) {
                Logger.fatal(
                    "Failed to access private field 'surfaceNoise' in ChunkProviderOverworld. Are you in a deobfuscated environment with other mappings?",
                    e2
                );
            }
        }
    }

    public boolean isOrganicBiome(int id) {
        return organicBiomes[id];
    }

    public int getHeightAt(int x, int z) {
        return this.getHeightsAt(globalToChunk(x), globalToChunk(z))[globalToLocal(x) * 16 + globalToLocal(z)];
    }

    public int[] getHeightsAt(int cx, int cz) {
        PlaneLocation.Invariant inLoc = new PlaneLocation.Invariant(cx, cz);
        int[] heights = null;
        for (PlaneLocation.Invariant location : chunkHeights.keySet()) {
            if (location.equals(inLoc)) heights = chunkHeights.get(location);
        }
        if (heights == null) heights = this.organicTerrain(cx, cz);
        return heights;
    }

    public int[] organicTerrain(int cx, int cz) {
        ChunkPrimer primer = new ChunkPrimer();
        organicProvider.setBlocksInChunk(cx, cz, primer);
        int[] heights = new int[256];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 255; y >= 0; --y) {
                    IBlockState iblockstate = primer.getBlockState(x, y, z);
                    if (iblockstate != null && iblockstate != Blocks.AIR.getDefaultState()) {
                        heights[x * 16 + z] = y;
                        break;
                    }
                }
            }
        }
        chunkHeights.put(new PlaneLocation.Invariant(cx, cz), heights);
        return heights;
    }

    public void organicSurface(int bx, int bz, ChunkPrimer primer, Biome biome) {
        biome.genTerrainBlocks(rtgWorld.world(), rtgWorld.rand(), primer, bx, bz, this.surfaceNoise.getValue(bx, bz));
    }
}
