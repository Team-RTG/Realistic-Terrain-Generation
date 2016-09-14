
package rtg.world.gen;

import rtg.util.*;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.IBiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 *
 * @author Zeno410
 */
class LandscapeGenerator {
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private final int[] biomeData;
    private float[] [] weightings;
    private final OpenSimplexNoise simplex;
    private final CellNoise cell;
    private float [] weightedBiomes = new float [BiomeUtils.getRegisteredBiomes().length];
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private TimedHashMap<PlaneLocation,ChunkLandscape> storage = new TimedHashMap<>(60 * 1000);
    
    LandscapeGenerator(OpenSimplexNoise simplex, CellNoise cell) {
        sampleArraySize = sampleSize * 2 + 5;
        biomeData = new int[sampleArraySize * sampleArraySize];
        this.simplex = simplex;
        this.cell = cell;
        setWeightings();
    }

    static String biomeLayoutActivity = "Biome Layout";

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

    int getBiomeDataAt(IBiomeProviderRTG cmr, int worldX, int worldY) {
        int chunkX = worldX&15;
        int chunkY = worldY&15;
        ChunkLandscape target = this.landscape(cmr, worldX-chunkX, worldY-chunkY);
        return BiomeUtils.getId(target.biome[chunkX*16+chunkY].baseBiome);
    }

    /*
     * All of the 'worldX' and 'worldZ' parameters have been flipped when passing them.
     * Prior to flipping, the terrain was being XZ-chunk-flipped. - WhichOnesPink
     */
    synchronized ChunkLandscape landscape(IBiomeProviderRTG cmr, int worldX, int worldZ) {
        PlaneLocation location = new PlaneLocation.Invariant(worldZ,worldX);
        ChunkLandscape preExisting = this.storage.get(location);
        if (preExisting != null) return preExisting;
        ChunkLandscape result = new ChunkLandscape();
        getNewerNoise(cmr, worldZ, worldX, result);
        int [] biomeIndices= cmr.getBiomesGens(worldZ, worldX,16,16);
        analyzer.newRepair(biomeIndices, result.biome, this.biomeData, this.sampleSize, result.noise,result.river);//-cmr.getRiverStrength(cx * 16 + 7, cy * 16 + 7));
        storage.put(location, result);
        return result;
    }

    private synchronized void getNewerNoise(IBiomeProviderRTG cmr, int x, int z, ChunkLandscape landscape) {
        // get area biome map
        String rtgNoise = "RTG Noise";
        TimeTracker.manager.start(rtgNoise);
        TimeTracker.manager.start(biomeLayoutActivity);
        for(int i = -sampleSize; i < sampleSize + 5; i++)
    	{
    		for(int j = -sampleSize; j < sampleSize + 5; j++)
    		{
    			biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = BiomeUtils.getId(cmr.getBiomeDataAt(x + ((i * 8)), z + ((j * 8))).baseBiome);
    		}
    	}

        TimeTracker.manager.stop(biomeLayoutActivity);
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

    			landscape.noise[i * 16 + j] = 0f;

                TimeTracker.manager.stop("Weighting");
                TimeTracker.manager.start("Generating");
    			river = cmr.getRiverStrength(x + i, z + j);
                landscape.river[i * 16 + j] = -river;
                float totalBorder = 0f;

    			for(int k = 0; k < 256; k++)
    			{

    				if(weightedBiomes[k] > 0f)
    				{


                        totalBorder += weightedBiomes[k];
    					landscape.noise[i * 16 + j] += RealisticBiomeBase.getBiome(k).
                                rNoise(simplex, cell, x + i, z + j, weightedBiomes[k], river + 1f) * weightedBiomes[k];
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
                landscape.biome[i*16+j] =  cmr.getBiomeDataAt(x + (((i-7) * 8+4)), z + (((j-7) * 8+4)));
            }
        }

        TimeTracker.manager.stop(biomeLayoutActivity);
        TimeTracker.manager.stop(rtgNoise);

    }

}
