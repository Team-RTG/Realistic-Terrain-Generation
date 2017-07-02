package rtg.world.gen;

import java.util.WeakHashMap;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;

import rtg.api.util.TimedHashMap;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.RTGWorld;
import rtg.util.TimeTracker;
import rtg.world.biome.BiomeAnalyzer;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;


/**
 *
 * @author Zeno410
 */
public class LandscapeGenerator {
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private final int[] biomeData;
    private float[] [] weightings;
    private final RTGWorld rtgWorld;
    private final OpenSimplexNoise simplex;
    private final CellNoise cell;
    private float [] weightedBiomes = new float [256];
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private TimedHashMap<ChunkPos,ChunkLandscape> storage = new TimedHashMap<>(60 * 1000);
    private RealisticBiomePatcher biomePatcher = new RealisticBiomePatcher();
    private final WeakHashMap<ChunkPos,float[]> cache = new WeakHashMap();
    private MesaBiomeCombiner mesaCombiner = new MesaBiomeCombiner();

    public LandscapeGenerator(RTGWorld rtgWorld) {
        this.rtgWorld = rtgWorld;
        sampleArraySize = sampleSize * 2 + 5;
        biomeData = new int[sampleArraySize * sampleArraySize];
        this.simplex = rtgWorld.simplex;
        this.cell = rtgWorld.cell;
        setWeightings();
    }

    private void setWeightings() {
        weightings = new float [sampleArraySize * sampleArraySize][256];
        for (int i = 0; i < 16; i++) {
            for (int j=0; j<16; j++) {
                TimeTracker.manager.start("Weighting");
                float limit = (float)Math.pow((56f*56f),.7);
                // float limit = 56f;

                for (int mapX = 0 ; mapX < sampleArraySize; mapX ++) {
                    for (int mapZ = 0 ; mapZ < sampleArraySize; mapZ ++) {
                        float xDist = (i - chunkCoordinate(mapX));
                        float zDist = (j - chunkCoordinate(mapZ));
                        float distanceSquared = xDist*xDist + zDist*zDist;
                        //float distance = (float)Math.sqrt(distanceSquared);
                        float distance = (float)Math.pow(distanceSquared,.7);
                        float weight = 1f - distance/limit;
                        if (weight <0) weight = 0;
                        weightings[mapX*sampleArraySize + mapZ][i * 16 + j] = weight;
                    }
                }
            }
        }
    }

    private int chunkCoordinate(int biomeMapCoordinate) {
        return (biomeMapCoordinate - sampleSize)*8;
    }

    public int getBiomeDataAt(IBiomeProviderRTG cmr, int cx, int cz) {
        int cx2 = cx&15;
        int cz2 = cz&15;
        ChunkLandscape target = this.landscape(cmr, cx-cx2, cz-cz2);
        return Biome.getIdForBiome(target.biome[cx2*16+cz2].baseBiome());
    }

    /*
     * All of the 'cx' and 'cz' parameters have been flipped when passing them.
     * Prior to flipping, the terrain was being XZ-chunk-flipped. - WhichOnesPink
     */
    synchronized ChunkLandscape landscape(IBiomeProviderRTG cmr, int cx, int cz) {
        ChunkPos chunkPos = new ChunkPos(cx, cz);
        ChunkLandscape preExisting = this.storage.get(chunkPos);
        if (preExisting != null) return preExisting;
        ChunkLandscape result = new ChunkLandscape();
        getNewerNoise(cmr, cx, cz, result);
        int [] biomeIndices= cmr.getBiomesGens(cx, cz,16,16);
        analyzer.newRepair(biomeIndices, result.biome, this.biomeData, this.sampleSize, result.noise, result.river);
        //-cmr.getRiverStrength(cx * 16 + 7, cy * 16 + 7));
        storage.put(chunkPos, result);
        return result;
    }

    private synchronized void getNewerNoise(IBiomeProviderRTG cmr, int cx, int cz, ChunkLandscape landscape) {
        // get area biome map
        TimeTracker.manager.start("RTG Noise");
        TimeTracker.manager.start("Biome Layout");

        for(int i = -sampleSize; i < sampleSize + 5; i++) {
            for(int j = -sampleSize; j < sampleSize + 5; j++) {
                biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] =
                Biome.getIdForBiome(cmr.getBiomeDataAt(cx + ((i * 8)), cz + ((j * 8))).baseBiome());
            }
        }

        TimeTracker.manager.stop("Biome Layout");
        float river;

        // fill the old smallRender
        for (int i = 0; i < 16; i++) {
            for (int j=0; j<16; j++) {
                TimeTracker.manager.start("Weighting");
                float totalWeight = 0;
                for (int mapX = 0 ; mapX < sampleArraySize; mapX ++) {
                    for (int mapZ = 0 ; mapZ < sampleArraySize; mapZ ++) {
                        float weight = weightings[mapX*sampleArraySize + mapZ][i * 16 +j];
                        if (weight > 0) {
                            totalWeight += weight;
                            weightedBiomes[biomeData[mapX*sampleArraySize + mapZ]] += weight;
                        }
                    }
                }
                // normalize biome weights
                for (int biomeIndex = 0; biomeIndex < weightedBiomes.length; biomeIndex ++) {
                    weightedBiomes[biomeIndex] /= totalWeight;
                }

                // combine mesa biomes
                mesaCombiner.adjust(weightedBiomes);
                
                landscape.noise[i * 16 + j] = 0f;

                TimeTracker.manager.stop("Weighting");
                TimeTracker.manager.start("Generating");
                river = cmr.getRiverStrength(cx + i, cz + j);
                landscape.river[i * 16 + j] = -river;
                float totalBorder = 0f;

                
                for(int k = 0; k < 256; k++)
                {
                    if(weightedBiomes[k] > 0f)
                    {
                        totalBorder += weightedBiomes[k];
                        RealisticBiomeBase realisticBiome = RealisticBiomeBase.getBiome(k);

                        // Do we need to patch the biome?
                        if (realisticBiome == null) {
                            realisticBiome = biomePatcher.getPatchedRealisticBiome(
                                "NULL biome (" + k + ") found when getting newer noise.");
                        }

                        landscape.noise[i * 16 + j] += realisticBiome.rNoise(this.rtgWorld, cx + i, cz + j, weightedBiomes[k], river + 1f) * weightedBiomes[k];

                        // 0 for the next column
                        weightedBiomes[k] = 0f;
                    }
                }
                if (totalBorder <.999||totalBorder>1.001) throw new RuntimeException("" + totalBorder);
                TimeTracker.manager.stop("Generating");
            }
        }

        //fill biomes array with biomeData

        TimeTracker.manager.start("Biome Layout");
        for (int i = 0; i < 16; i++) {
            for (int j=0; j<16; j++) {
                landscape.biome[i*16+j] =  cmr.getBiomeDataAt(cx + (((i-7) * 8+4)), cz + (((j-7) * 8+4)));
            }
        }
        TimeTracker.manager.stop("Biome Layout");
        TimeTracker.manager.stop("RTG Noise");
    }

    public float [] noiseFor(IBiomeProviderRTG cmr, int worldX, int worldZ) {
        ChunkPos location = new ChunkPos(worldX,worldZ);
        float [] result = cache.get(location);
        if (result != null) return result;
        // not found; we have to make it;

        result = new float[256];
        final int adjust = 24;// seems off? but decorations aren't matching their chunks.

        TimeTracker.manager.start("Biome Layout");
        for (int bx = -4; bx <= 4; bx++) {

            for (int bz = -4; bz <= 4; bz++) {
                result[getBiomeDataAt(cmr, worldX + adjust + bx * 4, worldZ + adjust + bz * 4)] += 0.01234569f;
            }
        }
        TimeTracker.manager.stop("Biome Layout");
        TimeTracker.manager.stop("Features"); 
        cache.put(location, result);
        return result;
    }
}
