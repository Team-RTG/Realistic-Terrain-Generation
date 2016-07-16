package teamrtg.rtg.core.world;

import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.PlaneLocation;
import teamrtg.rtg.api.util.TimeTracker;
import teamrtg.rtg.api.util.TimedHashMap;
import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 *
 * @author Zeno410
 */
public class LandscapeGenerator {
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private final int[] biomeData;
    private float[] [] weightings;
    private final OpenSimplexNoise simplex;
    private final CellNoise cell;
    private float [] weightedBiomes = new float [BiomeUtils.getRegisteredBiomes().length];
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private TimedHashMap<PlaneLocation,ChunkLandscape> storage = new TimedHashMap<PlaneLocation,ChunkLandscape>(60*1000);

    public LandscapeGenerator(OpenSimplexNoise simplex, CellNoise cell) {
        sampleArraySize = sampleSize * 2 + 5;
        biomeData = new int[sampleArraySize * sampleArraySize];
        this.simplex = simplex;
        this.cell = cell;
        setWeightings();
    }

    public static String biomeLayoutActivity = "Biome Layout";
    private static String rtgTerrain = "RTG Terrain";
    private static String rtgNoise = "RTG Noise";

    private void setWeightings() {
        weightings = new float [sampleArraySize * sampleArraySize][256];
        int adjustment = 4;// this should actually vary with sampleSize
        for (int i = 0; i < 16; i++) {
            for (int j=0; j<16; j++) {
                int locationIndex = ((int)(i + adjustment) * 25 + (j + adjustment));
                TimeTracker.manager.start("Weighting");
                float totalWeight = 0;
                float limit = (float)Math.pow((56f*56f),.7);
                // float limit = 56f;

                for (int mapX = 0 ; mapX < sampleArraySize; mapX ++) {
                    for (int mapZ = 0 ; mapZ < sampleArraySize; mapZ ++) {
                        float xDist = (i - chunkCoordinate(mapX));
                        float yDist = (j - chunkCoordinate(mapZ));
                        float distanceSquared = xDist*xDist + yDist*yDist;
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

    public int getBiomeDataAt(BiomeProviderRTG bprv, int worldX, int worldY) {
        int chunkX = worldX&15;
        int chunkY = worldY&15;
        ChunkLandscape target = this.landscape(bprv, worldX-chunkX, worldY-chunkY);
        return target.biomes[chunkX*16+chunkY].getID();
    }

    public synchronized ChunkLandscape landscape(BiomeProviderRTG bprv, int worldX, int worldY) {
        PlaneLocation location = new PlaneLocation.Invariant(worldX,worldY);
        ChunkLandscape preExisting = this.storage.get(location);
        if (preExisting != null) return preExisting;
        ChunkLandscape result = new ChunkLandscape();
        getNewerNoise(bprv, worldX, worldY, result);
        int [] biomeIndices= bprv.getBiomesGens(worldX, worldY,16,16);
        analyzer.newRepair(biomeIndices, result.biomes, this.biomeData, this.sampleSize, result.noise,result.river);//-bprv.getRiverStrength(cx * 16 + 7, cy * 16 + 7));
        storage.put(location, result);
        return result;
    }

    private synchronized void getNewerNoise(BiomeProviderRTG bprv, int x, int y,ChunkLandscape landscape) {
        //TODO: add the new border value to this. border is the actual distance to the biome border.
        // get area biome map
        TimeTracker.manager.start(rtgNoise);
        TimeTracker.manager.start(biomeLayoutActivity);
        for(int i = -sampleSize; i < sampleSize + 5; i++)
        {
            for(int j = -sampleSize; j < sampleSize + 5; j++)
            {
                biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = BiomeUtils.getId(bprv.getPreRepair(x + ((i * 8)), y + ((j * 8))));
            }
        }

        TimeTracker.manager.stop(biomeLayoutActivity);
        float river;

        int adjustment = 4;// this should actually vary with sampleSize
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

                landscape.noise[i * 16 + j] = 0f;

                TimeTracker.manager.stop("Weighting");
                TimeTracker.manager.start("Generating");
                river = bprv.getRiverStrength(x + i, y + j);
                landscape.river[i * 16 + j] = -river;
                float totalBorder = 0f;

                for(int k = 0; k < 256; k++)
                {

                    if(weightedBiomes[k] > 0f)
                    {


                        totalBorder += weightedBiomes[k];
                        landscape.noise[i * 16 + j] += RealisticBiomeGenerator.forBiome(k).terrainHeight(bprv.chunkProvider.rtgWorld, x + i, y + j, weightedBiomes[k], river + 1f) * weightedBiomes[k];
                        // 0 for the next column
                        weightedBiomes[k] = 0f;

                    }
                }
                if (totalBorder <.999||totalBorder>1.001) throw new RuntimeException("" + totalBorder);
                TimeTracker.manager.stop("Generating");
            }
        }

        //fill biomes array with biomeData

        TimeTracker.manager.start(biomeLayoutActivity);
        for (int i = 0; i < 16; i++) {
            for (int j=0; j<16; j++) {
                landscape.biomes[i*16+j] = RTGBiome.forBiome(bprv.getPreRepair(x + (((i-7) * 8+4)), y + (((j-7) * 8+4))));
            }
        }

        TimeTracker.manager.stop(biomeLayoutActivity);
        TimeTracker.manager.stop(rtgNoise);
        return;

    }

}