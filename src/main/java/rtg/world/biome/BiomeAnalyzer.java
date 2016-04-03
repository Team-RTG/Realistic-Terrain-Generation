
package rtg.world.biome;

import rtg.util.CircularSearchCreator;
import rtg.world.biome.realistic.RealisticBiomeBase;

import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
public class BiomeAnalyzer {
    private boolean [] riverBiome;
    private boolean [] oceanBiome;
    private boolean [] swampBiome;
    private boolean [] beachBiome;
    private boolean [] landBiome;
    private int [] preferredBeach;
    private int [] searchPattern;
    private RealisticBiomeBase [] savedJittered = new RealisticBiomeBase[256];
    private int [] xyinverted = xyinverted();

    // beach fixing
    float beachTop = 64.5f;
    float beachBottom = 61.5f;
    float oceanTop = 61.5f;
    SearchStatus beach = new SearchStatus();
    SearchStatus land = new SearchStatus();
    SearchStatus ocean = new SearchStatus();

    private int sampleSize = 8;
    private int sampleArraySize = sampleSize*2+5;

    
    public BiomeAnalyzer() {
        determineRiverBiomes();
        determineOceanBiomes();
        determineSwampBiomes();
        determineBeachBiomes();
        determineLandBiomes();
        determinePreferredBeaches();
        prepareSearchPattern();
        setSearches();
    }

    private void determineRiverBiomes() {
        riverBiome = new boolean[BiomeGenBase.getBiomeGenArray().length];
        for (int index = 0; index < BiomeGenBase.getBiomeGenArray().length; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("river")) {
                riverBiome[index] = true;
            }
        }
    }

    private void determineOceanBiomes() {
        oceanBiome = new boolean[BiomeGenBase.getBiomeGenArray().length];
        for (int index = 0; index < BiomeGenBase.getBiomeGenArray().length; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("ocean")) {
                oceanBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("kelp")) {
                oceanBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("coral")) {
                oceanBiome[index] = true;
            }
        }
        oceanBiome[BiomeGenBase.deepOcean.biomeID]=true;// not getting set?
    }

    private void determineSwampBiomes() {
        swampBiome = new boolean[BiomeGenBase.getBiomeGenArray().length];
        for (int index = 0; index < BiomeGenBase.getBiomeGenArray().length; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("swamp")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("bayou")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("bog")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("wetland")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("sludge")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("marsh")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("fen")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("moor")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("quagmire")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("ephemeral lake")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("rainforest valley")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("riparian zone")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("ice sheet")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("woodland lake")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("archipelago")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().equals("shield")) {
                swampBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeID==BiomeGenBase.frozenRiver.biomeID) {
                swampBiome[index] = true;
            }
        }
    }
    private void determineLandBiomes() {
        landBiome = new boolean[BiomeGenBase.getBiomeGenArray().length];
        for (int index = 0; index < BiomeGenBase.getBiomeGenArray().length; index++) {
            if (!oceanBiome[index]) {
                if (!riverBiome[index]) {
                     if (BiomeGenBase.getBiome(index) == null) continue;
                     if (BiomeGenBase.getBiome(index).biomeName == null) continue;
                     if (beachBiome[index]) continue;
                     if (!BiomeGenBase.getBiome(index).biomeName.toLowerCase().equals("lake")) {
                         landBiome[index] = true;
                     }

                }
            }
        }
    }

    private void determineBeachBiomes() {
        beachBiome = new boolean[BiomeGenBase.getBiomeGenArray().length];
        for (int index = 0; index < BiomeGenBase.getBiomeGenArray().length; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("beach")) {
                beachBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("mangrove")) {
                beachBiome[index] = true;
            }
        }
    }

    private void determinePreferredBeaches() {
        preferredBeach = new int[BiomeGenBase.getBiomeGenArray().length];
        for (int index = 0; index < BiomeGenBase.getBiomeGenArray().length; index++){
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName == null) continue;
            if (BiomeGenBase.getBiome(index).temperature <= 0.05f) {
                preferredBeach[index]= BiomeGenBase.coldBeach.biomeID;
                continue;
            } // implied else;
            // this code from Climate Control and is still crude
            float height = BiomeGenBase.getBiome(index).rootHeight + BiomeGenBase.getBiome(index).heightVariation*2;
            if ((height>(1.0f+0.5))) {
                preferredBeach[index] = BiomeGenBase.stoneBeach.biomeID;
            } else {
                preferredBeach[index] = BiomeGenBase.beach.biomeID;
            }

        }
    }

    public void repair(int [] genLayerBiomes, RealisticBiomeBase [] jitteredBiomes, int [] biomeNeighborhood, int neighborhoodSize, float [] noise, float riverStrength) {
        if (neighborhoodSize != sampleSize) throw new RuntimeException("mismatch between chunk and analyzer neighborhood sizes");
        // currently just stuffs the genLayer into the jitter;
        boolean canBeRiver = riverStrength >0.05;
        for (int i = 0; i < 256; i++) {
            // save what's there since the jitter keeps changing
            savedJittered [i]= jitteredBiomes[i];
            //if (savedJittered[i]== null) throw new RuntimeException();
            if (noise[i]>61.5) {
                // replace
                jitteredBiomes[i] =  RealisticBiomeBase.getBiome(genLayerBiomes[xyinverted[i]]);
            } else {
                // check for river
                if (canBeRiver&&!oceanBiome[genLayerBiomes[xyinverted[i]]]&&!swampBiome[genLayerBiomes[xyinverted[i]]]) {
                    // make river
                    int riverBiomeID = RealisticBiomeBase.getBiome(genLayerBiomes[xyinverted[i]]).riverBiome.biomeID;
                    jitteredBiomes[i] =  RealisticBiomeBase.getBiome(riverBiomeID);
                } else {
                    // replace
                    jitteredBiomes[i] =  RealisticBiomeBase.getBiome(genLayerBiomes[xyinverted[i]]);
                }
            }

        }

        // put beaches on shores
        beach.absent = false;
        beach.notHunted = true;
        for (int i = 0; i < 256; i++) {
            if (beach.absent) break; //no point
            if (noise[i]<beachBottom||noise[i]>beachTop) continue;// this block isn't beach level
            if (swampBiome[jitteredBiomes[i].biomeID]) continue;// swamps are acceptable at beach level
            if (beach.notHunted) {
                huntForBeaches(this.savedJittered);
                if (!beach.absent) jitteredBiomes[i] = beach.biome;
            } else {
                //we already found it
                jitteredBiomes[i] = beach.biome;
            }
        }

        // put land higher up;
        land.absent = false;
        land.notHunted = true;
        for (int i = 0; i < 256; i++) {
            if (land.absent) break; //no point
            if (noise[i]<beachTop) continue;// this block isn't above beach level
            int biomeID = jitteredBiomes[i].biomeID;
            if (landBiome[biomeID]) continue;// already land
            if (swampBiome[jitteredBiomes[i].biomeID]) continue;// swamps are acceptable above water
            if (land.notHunted) {
                huntForLand(this.savedJittered);
                if (!land.absent) {
                    jitteredBiomes[i] = land.biome;
                    //if (beachBiome[land.biome.biomeID]) throw new RuntimeException();
                    //if (oceanBiome[land.biome.biomeID]) throw new RuntimeException();
                }
            } else {
                //we already found it
                jitteredBiomes[i] = land.biome;
            }
        }

        // put ocean below sea level
        ocean.absent = false;
        ocean.notHunted = true;
        for (int i = 0; i < 256; i++) {
            if (ocean.absent) break; //no point
            if (noise[i]>oceanTop) continue;// too hight
            if (oceanBiome[jitteredBiomes[i].biomeID]) continue;// obviously ocean is OK
            if (swampBiome[jitteredBiomes[i].biomeID]) continue;// swamps are acceptable
            if (riverBiome[jitteredBiomes[i].biomeID]) continue;// rivers stay rivers
            if (ocean.notHunted) {
                huntForOcean(this.savedJittered);
                if (!ocean.absent) jitteredBiomes[i] = ocean.biome;
            } else {
                //we already found it
                jitteredBiomes[i] = ocean.biome;
            }
        }
    }

    private void huntForBeaches(RealisticBiomeBase [] biomes) {
        beach.notHunted = false;
        // in case nothing found
        beach.absent = true;
        RealisticBiomeBase considered;
        for (int i = 0; i<256; i++) {
            considered = biomes[searchPattern[i]];
            if (beachBiome[considered.biomeID]) {
                beach.absent = false;
                beach.biome = considered;
                break;// we're done searching
            }
        }
    }

    private void huntForLand(RealisticBiomeBase [] biomes) {
        land.notHunted = false;
        // in case nothing found
        land.absent = true;
        RealisticBiomeBase considered;
        for (int i = 0; i<256; i++) {
            considered = biomes[searchPattern[i]];
            if (landBiome[considered.biomeID]) {
                land.absent = false;
                land.biome = considered;
                break;// we're done searching
            }
        }
    }

    private void huntForOcean(RealisticBiomeBase [] biomes) {
        ocean.notHunted = false;
        // in case nothing found
        ocean.absent = true;
        RealisticBiomeBase considered;
        for (int i = 0; i<256; i++) {
            considered = biomes[searchPattern[i]];
            if (oceanBiome[considered.biomeID]) {
                ocean.absent = false;
                ocean.biome = considered;
                break;// we're done searching
            }
        }
    }


    /* HUNTING
     *
     */

        public void newRepair(int [] genLayerBiomes, RealisticBiomeBase [] jitteredBiomes, int [] biomeNeighborhood, int neighborhoodSize, float [] noise, float riverStrength) {
        if (neighborhoodSize != sampleSize) throw new RuntimeException("mismatch between chunk and analyzer neighborhood sizes");
        // currently just stuffs the genLayer into the jitter;
        boolean canBeRiver = riverStrength >0.05;
        for (int i = 0; i < 256; i++) {
            // save what's there since the jitter keeps changing
            savedJittered [i]= jitteredBiomes[i];
            //if (savedJittered[i]== null) throw new RuntimeException();
            if (noise[i]>61.5) {
                // replace
                jitteredBiomes[i] =  RealisticBiomeBase.getBiome(genLayerBiomes[xyinverted[i]]);
            } else {
                // check for river
                if (canBeRiver&&!oceanBiome[genLayerBiomes[xyinverted[i]]]&&!swampBiome[genLayerBiomes[xyinverted[i]]]) {
                    // make river
                    int riverBiomeID = RealisticBiomeBase.getBiome(genLayerBiomes[xyinverted[i]]).riverBiome.biomeID;
                    jitteredBiomes[i] =  RealisticBiomeBase.getBiome(riverBiomeID);
                } else {
                    // replace
                    jitteredBiomes[i] =  RealisticBiomeBase.getBiome(genLayerBiomes[xyinverted[i]]);
                }
            }

        }

        // put beaches on shores
        beachSearch.notHunted = true;
        beachSearch.absent = false;
        for (int i = 0; i < 256; i++) {
            if (beachSearch.absent) break; //no point
            if (noise[i]<beachBottom||noise[i]>beachTop) continue;// this block isn't beach level
            if (swampBiome[jitteredBiomes[i].biomeID]) continue;// swamps are acceptable at beach level
            if (beachSearch.notHunted) {
                beachSearch.hunt(biomeNeighborhood);
                landSearch.hunt(biomeNeighborhood);
            }
            int foundBiome = beachSearch.biomes[i];
            if (foundBiome != NO_BIOME) {
                int nearestLandBiome = landSearch.biomes[i];
                if (nearestLandBiome>-1) {
                    foundBiome = preferredBeach[nearestLandBiome];
                }
                jitteredBiomes[i] = RealisticBiomeBase.getBiome(foundBiome);
            }
        }

        // put land higher up;
        landSearch.absent = false;
        landSearch.notHunted = true;
        for (int i = 0; i < 256; i++) {
            if (landSearch.absent) break; //no point
            if (noise[i]<beachTop) continue;// this block isn't above beach level
            int biomeID = jitteredBiomes[i].biomeID;
            if (landBiome[biomeID]) continue;// already land
            if (swampBiome[jitteredBiomes[i].biomeID]) continue;// swamps are acceptable above water
            if (landSearch.notHunted) {
                landSearch.hunt(biomeNeighborhood);
            }

            int foundBiome = landSearch.biomes[i];
            if (foundBiome != NO_BIOME) {
                jitteredBiomes[i] = RealisticBiomeBase.getBiome(foundBiome);
            }
        }

        // put ocean below sea level
        oceanSearch.absent = false;
        oceanSearch.notHunted = true;
        for (int i = 0; i < 256; i++) {
            if (oceanSearch.absent) break; //no point
            if (noise[i]>oceanTop) continue;// too hight
            if (oceanBiome[jitteredBiomes[i].biomeID]) continue;// obviously ocean is OK
            if (swampBiome[jitteredBiomes[i].biomeID]) continue;// swamps are acceptable
            if (riverBiome[jitteredBiomes[i].biomeID]) continue;// rivers stay rivers
            if (oceanSearch.notHunted) {
                oceanSearch.hunt(biomeNeighborhood);
            }

            int foundBiome = oceanSearch.biomes[i];
            if (foundBiome != NO_BIOME) {
                jitteredBiomes[i] = RealisticBiomeBase.getBiome(foundBiome);
            }
        }
    }

    private void prepareSearchPattern() {
        searchPattern = new CircularSearchCreator().pattern();
        //if (searchPattern.length != 256) throw new RuntimeException();
    }

    public int[] xyinverted() {
        int [] result = new int [256];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j<16;j++) {
                result[i*16+j]= j*16+i;
            }
        }
        for (int i = 0; i<256;i++) {
            if (result[result[i]]!=i) throw new RuntimeException(""+i + "" + result[i]+ " "+ result[result[i]]);
        }
        return result;
    }
    private class SearchStatus {
        boolean absent = false;
        boolean notHunted = true;
        RealisticBiomeBase biome;
    }


    private void setSearches() {
        beachSearch = new SmoothingSearchStatus(this.beachBiome);
        landSearch = new SmoothingSearchStatus(this.landBiome);
        oceanSearch = new SmoothingSearchStatus(this.oceanBiome);
    }

    private SmoothingSearchStatus beachSearch;
    private SmoothingSearchStatus landSearch;
    private SmoothingSearchStatus oceanSearch;


    private final static int NO_BIOME = -1;
    private class SmoothingSearchStatus {

        SmoothingSearchStatus(boolean []desired) {
            this.desired = desired;
        }
        boolean absent = false;
        boolean notHunted;
        private final int size() {return 3;}
        private int [] findings = new int [3*3];
        // weightings is part of a system to generate some variability in repaired chunks
        // weighting is based on how long the search went on (so quasipsuedorandom, based on direction
        // plus distance
        private float [] weightings = new float [3*3];
        public int [] biomes = new int [256];
        
        private void clear() {
            for (int i = 0;i <findings.length; i++) {
                findings[i] = -1;
            }
        }
        
        private boolean [] desired;
        private int arraySize;
        private int [] pattern;
        public void hunt(int [] biomeNeighborhood) {
            // 0,0 in the chunk is 9,9 int the array ; 8,8 is 10,10 and is treated as the center
            clear();
            int oldArraySize = arraySize;
            arraySize = (int)Math.sqrt(biomeNeighborhood.length);
            if (arraySize*arraySize != biomeNeighborhood.length) throw new RuntimeException("non-square array");
            if (arraySize != oldArraySize) {
                pattern = new CircularSearchCreator().pattern(arraySize/2-1, arraySize);
            }
            for (int xOffset = -1; xOffset<=1; xOffset++) {
                for (int yOffset = -1; yOffset<=1; yOffset++){
                    search(xOffset,yOffset, biomeNeighborhood);
                    // calling a routine because it gets too indented otherwise
                }
            }
            smoothBiomes();
        }

        private final void search(int xOffset, int yOffset, int [] biomeNeighborhood) {
            int offset = xOffset*arraySize +yOffset;
            int location = (xOffset+1)*size()+yOffset+1;
            // set to failed search, which sticks if nothing is found
            findings[location] = NO_BIOME;
            weightings[location] = 2f;
            for (int i = 0; i < pattern.length; i++) {
                int biome = biomeNeighborhood[pattern[i]+offset];
                if (desired[biome]) {
                    findings[location]=biome;
                    weightings[location] = (float)Math.sqrt(pattern.length)
                            -(float)Math.sqrt(i) + 2f ;
                    break;
                }
            }
        }

        private void setBiomes() {
            // uses the findings to set the replacement biomes
            // just copy the center to mockup the current system
            for (int i = 0; i < 256; i++) {
                biomes[i] = findings[4];
            }
            if (findings[4]==-1) {
                absent = true;
            }
        }

        private void smoothBiomes() {
            // more sophisticated version
            // offsets into findings and biomes
            // upperleft
            smoothQuadrant(biomeIndex(0,0),upperLeftFinding);
            smoothQuadrant(biomeIndex(8,0),upperRightFinding);
            smoothQuadrant(biomeIndex(0,8),lowerLeftFinding);
            smoothQuadrant(biomeIndex(8,8),lowerRightFinding);

        }

        private final int upperLeftFinding = 0;
        private final int upperRightFinding = 3;
        private final int lowerLeftFinding = 1;
        private final int lowerRightFinding = 4;

        private final int biomeIndex(int x, int y) {
            return x*16+y;
        }

        private final int [] quadrantBiome = new int[4];
        private final float [] quadrantBiomeWeighting = new float [4];
        private int biomeCount;
        private void addBiome(int biome) {
            for (int i = 0; i < biomeCount; i++) {
                if (biome == quadrantBiome[i]) return;
            }
            // not there, add
            quadrantBiome[biomeCount++] = biome;
        }
        private void addWeight(int biome, float weight) {
            for (int i = 0; i <biomeCount ; i++){
                if (biome == quadrantBiome[i]) {
                    quadrantBiomeWeighting[i] += weight;
                    return;
                }
            }
        }
        
        private int preferredBiome() {
            float bestWeight = 0;
            int result = -2;
            for (int i = 0; i <biomeCount ; i++){
                if (quadrantBiomeWeighting[i]>bestWeight) {
                    bestWeight =quadrantBiomeWeighting[i];
                    result = quadrantBiome[i];
                }
            }
            return result;
        }


        private void smoothQuadrant(int biomesOffset, int findingsOffset) {
            int upperLeft = findings[upperLeftFinding+findingsOffset];
            int upperRight = findings[upperRightFinding+findingsOffset];
            int lowerLeft = findings[lowerLeftFinding+findingsOffset];
            int lowerRight = findings[lowerRightFinding+findingsOffset];
            // check for uniformity
            if ((upperLeft == upperRight)&&(upperLeft == lowerLeft)&&(upperLeft == lowerRight)){
                // everythings the same; uniform fill;
                for (int x = 0; x<8;x++) {
                    for (int y =0; y<8;y++) {
                        biomes[biomeIndex(x,y)+biomesOffset] = upperLeft;
                    }
                }
                return;
            }
            // not all the same; we have to work;
            biomeCount = 0;
            addBiome(upperLeft);
            addBiome(upperRight);
            addBiome(lowerLeft);
            addBiome(lowerRight);
            for (int x = 0; x<8; x++) {
                for (int y = 0; y<8; y++) {
                    addBiome(lowerRight);
                    for (int i  = 0; i < 4; i ++) {
                        quadrantBiomeWeighting[i] = 0;
                    }
                    // weighting strategy: weights go down as you move away from the corner.
                    // they go to 0 on the far edges so only the points on the edge have effects there
                    // for continuity with the next quadrant
                    addWeight(upperLeft,weightings[upperLeftFinding+findingsOffset]*(7-x)*(7-y));
                    addWeight(upperRight,weightings[upperRightFinding+findingsOffset]*x*(7-y));
                    addWeight(lowerLeft,weightings[lowerLeftFinding+findingsOffset]*(7-x)*y);
                    addWeight(lowerRight,weightings[lowerRightFinding+findingsOffset]*x*y);
                    biomes[biomeIndex(x,y)+biomesOffset] = preferredBiome();
                }
            }

        }
        
        private void adjust(int [] chunkBiomeArray) {
            if (chunkBiomeArray.length != 256) throw new RuntimeException();
        }
    }
}
