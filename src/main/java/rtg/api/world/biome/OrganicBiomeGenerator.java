package rtg.api.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import rtg.api.util.LimitedMap;
import rtg.api.util.MathUtils;
import rtg.api.util.PlaneLocation;
import rtg.api.world.IRTGWorld;

/**
 * @author topisani
 */
// TODO: [1.12] Creating an instance of ChunkGeneratorOverworld will send a InitNoiseGensEvent.ContextOverworld event to be sent which
//              Could interfere with the intended funtionality of this class as any event listeners could potentially alter the
//              NoiseGeneratorPerlin instance (surfaceNoise) that is used to generate the surface.
//              Currently this class doesn't perform as it should. The terrain will not match the biome it is in and the terrain provided
//              by the ChunkGeneratorOverworld#setBlocksInChunk has random noise on the surface.
//              Possible solution: Make a generic RealisticBiome class for biomes not supported by RTG that has a proper terrain generator.
public class OrganicBiomeGenerator {

    public static boolean[] organicBiomes = new boolean[256];
    private final ChunkGeneratorOverworld organicProvider;
    private LimitedMap<PlaneLocation.Invariant, int[]> chunkHeights = new LimitedMap<>(64); //Keep the heights for the last 64 chunks around for a bit. We might need them
    private IRTGWorld rtgWorld;
    private NoiseGeneratorPerlin surfaceNoise;

    public OrganicBiomeGenerator(IRTGWorld rtgWorld) {
        this.rtgWorld = rtgWorld;
        this.organicProvider = new ChunkGeneratorOverworld(
            rtgWorld.world(),
            rtgWorld.world().getSeed(),
            rtgWorld.world().getWorldInfo().isMapFeaturesEnabled(),
            rtgWorld.world().getWorldInfo().getGeneratorOptions()
        );
        try { this.surfaceNoise = ReflectionHelper.getPrivateValue(ChunkGeneratorOverworld.class, this.organicProvider, "surfaceNoise", "field_185994_m"); }
        catch (Exception ignore) { this.surfaceNoise = new NoiseGeneratorPerlin(rtgWorld.world().rand, 4); }
    }

    public boolean isOrganicBiome(int id) {
        return organicBiomes[id];
    }

    public int getHeightAt(int x, int z) {
        return this.getHeightsAt(MathUtils.globalToChunk(x), MathUtils.globalToChunk(z))[(MathUtils.globalToLocal(x) << 4) + MathUtils.globalToLocal(z)];
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
                    if (primer.getBlockState(x, y, z) != Blocks.AIR.getDefaultState()) {
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
