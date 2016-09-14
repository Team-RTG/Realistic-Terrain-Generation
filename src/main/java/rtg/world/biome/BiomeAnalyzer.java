
package rtg.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.config.rtg.ConfigRTG;
import rtg.util.BiomeUtils;
import rtg.util.CircularSearchCreator;
import rtg.world.biome.realistic.RealisticBiomeBase;

import static rtg.util.BiomeUtils.getName;


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
    private RealisticBiomeBase [] savedJittered;

    private RealisticBiomeBase scenicLakeBiome = RealisticBiomeBase.getBiome(ConfigRTG.scenicLakeBiome);
    private RealisticBiomeBase scenicFrozenLakeBiome =
        RealisticBiomeBase.getBiome(ConfigRTG.scenicFrozenLakeBiome);


    public BiomeAnalyzer() {
        determineRiverBiomes();
        determineOceanBiomes();
        determineSwampBiomes();
        determineBeachBiomes();
        determineLandBiomes();
        determinePreferredBeaches();
        prepareSearchPattern();
        setSearches();
        savedJittered = new RealisticBiomeBase[256];
    }

    private void determineRiverBiomes() {
        riverBiome = new boolean[BiomeUtils.getRegisteredBiomes().length];
        for (int index = 0; index < BiomeUtils.getRegisteredBiomes().length; index++) {
            if (Biome.getBiome(index) == null) continue;
            if (getName(Biome.getBiome(index)).toLowerCase().contains("river")) {
                riverBiome[index] = true;
            }
        }
    }

    private void determineOceanBiomes() {
        oceanBiome = new boolean[BiomeUtils.getRegisteredBiomes().length];
        for (int index = 0; index < BiomeUtils.getRegisteredBiomes().length; index++) {
            if (Biome.getBiome(index) == null) continue;
            if (getName(Biome.getBiome(index)).toLowerCase().contains("ocean")) {
                oceanBiome[index] = true;
            }
            if (getName(Biome.getBiome(index)).toLowerCase().contains("kelp")) {
                oceanBiome[index] = true;
            }
            if (getName(Biome.getBiome(index)).toLowerCase().contains("coral")) {
                oceanBiome[index] = true;
            }
        }
        oceanBiome[BiomeUtils.getId(Biomes.DEEP_OCEAN)]=true;// not getting set?
    }

    private void determineSwampBiomes() {
        swampBiome = new boolean[BiomeUtils.getRegisteredBiomes().length];
        for (int index = 0; index < BiomeUtils.getRegisteredBiomes().length; index++) {
            if (Biome.getBiome(index) == null) continue;
            String biomeName = BiomeUtils.getName(Biome.getBiome(index));
            if (biomeName.toLowerCase().contains("swamp"))              swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("bayou"))              swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("bog"))                swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("wetland"))            swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("sludge"))             swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("marsh"))              swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("fen"))                swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("moor"))               swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("quagmire"))           swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("ephemeral lake"))     swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("rainforest valley"))  swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("riparian zone"))      swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("ice sheet"))          swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("woodland lake"))      swampBiome[index] = true;
            if (biomeName.toLowerCase().contains("archipelago"))        swampBiome[index] = true;
            if (biomeName.toLowerCase().equals("shield"))               swampBiome[index] = true;
            if (BiomeUtils.getId(Biome.getBiome(index)) == BiomeUtils.getId(Biomes.FROZEN_RIVER)) swampBiome[index] = true;
        }
    }

    private void determineLandBiomes() {
        landBiome = new boolean[BiomeUtils.getRegisteredBiomes().length];
        for (int index = 0; index < BiomeUtils.getRegisteredBiomes().length; index++) {
            if (!oceanBiome[index]) {
                if (!riverBiome[index]) {
                    if (Biome.getBiome(index) == null) continue;
                    if (beachBiome[index]) continue;
                    if (!getName(Biome.getBiome(index)).toLowerCase().equals("lake")) {
                        landBiome[index] = true;
                    }

                }
            }
        }
    }

    private void determineBeachBiomes() {
        beachBiome = new boolean[BiomeUtils.getRegisteredBiomes().length];
        for (int index = 0; index < BiomeUtils.getRegisteredBiomes().length; index++) {
            if (Biome.getBiome(index) == null) continue;
            if (getName(Biome.getBiome(index)).toLowerCase().contains("beach")) {
                beachBiome[index] = true;
            }
            if (getName(Biome.getBiome(index)).toLowerCase().contains("mangrove")) {
                beachBiome[index] = true;
            }
        }
    }

    private void determinePreferredBeaches() {
        preferredBeach = new int[BiomeUtils.getRegisteredBiomes().length];
        for (int index = 0; index < BiomeUtils.getRegisteredBiomes().length; index++){
            if (Biome.getBiome(index) == null) continue;
            Biome biome = Biome.getBiome(index);
            if (biome==null) continue;
            RealisticBiomeBase realisticVersion = RealisticBiomeBase.getBiome(index);
            // no beach if set to no beach
            if (realisticVersion != null) {
                if (realisticVersion.disallowAllBeaches) preferredBeach[index] = index;
            }
            if (biome.getTemperature() <= 0.05f) {
                preferredBeach[index]= BiomeUtils.getId(Biomes.COLD_BEACH);
                continue;
            } // implied else;

            // sand beach if set to no stone beach
            if (realisticVersion != null) {
                if (realisticVersion.disallowStoneBeaches) {
                    preferredBeach[index] = BiomeUtils.getId(Biomes.BEACH);
                    continue;
                }
            }// implied else;
            // this code from Climate Control and is still crude

            float height = biome.getBaseHeight() + biome.getHeightVariation()*2;
            if ((height>(1.0f+0.5))) {
                preferredBeach[index] = BiomeUtils.getId(Biomes.STONE_BEACH);
            } else {
                preferredBeach[index] = BiomeUtils.getId(Biomes.BEACH);
            }

        }
    }

    /* HUNTING
     *
     */

    public void newRepair(int [] genLayerBiomes, RealisticBiomeBase [] jitteredBiomes, int [] biomeNeighborhood, int neighborhoodSize, float [] noise, float [] riverStrength) {
        int sampleSize = 8;
        if (neighborhoodSize != sampleSize) throw new RuntimeException("mismatch between chunk and analyzer neighborhood sizes");
        // currently just stuffs the genLayer into the jitter;
        for (int i = 0; i < 256; i++) {
            boolean canBeRiver = riverStrength[i] >0.7;
            // save what's there since the jitter keeps changing
            savedJittered [i]= jitteredBiomes[i];
            //if (savedJittered[i]== null) throw new RuntimeException();
            if (noise[i]>61.5) {
                // replace
                jitteredBiomes[i] =  RealisticBiomeBase.getBiome(genLayerBiomes[i]);
            } else {
                // check for river
                if (canBeRiver&&!oceanBiome[genLayerBiomes[i]]&&!swampBiome[genLayerBiomes[i]]) {
                    // make river
                    int riverBiomeID = BiomeUtils.getId(RealisticBiomeBase.getBiome(genLayerBiomes[i]).riverBiome);
                    jitteredBiomes[i] =  RealisticBiomeBase.getBiome(riverBiomeID);
                } else {
                    // replace
                    jitteredBiomes[i] =  RealisticBiomeBase.getBiome(genLayerBiomes[i]);
                }
            }

        }

        // put beaches on shores
        beachSearch.notHunted = true;
        beachSearch.absent = false;
        float beachTop = 64.5f;
        for (int i = 0; i < 256; i++) {
            if (beachSearch.absent) break; //no point
            float beachBottom = 61.5f;
            if (noise[i]< beachBottom ||noise[i]>riverAdjusted(beachTop,riverStrength[i])) continue;// this block isn't beach level
            if (swampBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) continue;// swamps are acceptable at beach level
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
            if (noise[i]<riverAdjusted(beachTop,riverStrength[i])) continue;// this block isn't above beach level
            int biomeID = BiomeUtils.getId(jitteredBiomes[i].baseBiome);
            if (landBiome[biomeID]) continue;// already land
            if (swampBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) continue;// swamps are acceptable above water
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
            float oceanTop = 61.5f;
            if (noise[i]> oceanTop) continue;// too hight
            if (oceanBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) continue;// obviously ocean is OK
            if (swampBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) continue;// swamps are acceptable
            if (riverBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) continue;// rivers stay rivers
            if (oceanSearch.notHunted) {
                oceanSearch.hunt(biomeNeighborhood);
            }

            int foundBiome = oceanSearch.biomes[i];
            if (foundBiome != NO_BIOME) {
                jitteredBiomes[i] = RealisticBiomeBase.getBiome(foundBiome);
            }
        }
        // convert remainder below sea level to lake biome
        for (int i = 0; i < 256; i++) {
            if (noise[i]<=61.5&&!riverBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) {
                // check for river
                if (!oceanBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)] &&
                    !swampBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)] &&
                    !beachBiome[BiomeUtils.getId(jitteredBiomes[i].baseBiome)]) {
                    // make river
                    int riverReplacement = BiomeUtils.getId(jitteredBiomes[i].riverBiome);
                    if (riverReplacement == BiomeUtils.getId(Biomes.FROZEN_RIVER)) {
                        jitteredBiomes[i] = scenicFrozenLakeBiome;
                    } else {
                        jitteredBiomes[i] = scenicLakeBiome;
                    }
                }
            }

        }
    }

    private void prepareSearchPattern() {
        //if (searchPattern.length != 256) throw new RuntimeException();
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
        private int size() {return 3;}
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
        void hunt(int[] biomeNeighborhood) {
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

        private void search(int xOffset, int yOffset, int [] biomeNeighborhood) {
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

        private int biomeIndex(int x, int z) {
            return x*16+z;
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
                    for (int z =0; z<8;z++) {
                        biomes[biomeIndex(x,z)+biomesOffset] = upperLeft;
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
                for (int z= 0; z<8; z++) {
                    addBiome(lowerRight);
                    for (int i  = 0; i < 4; i ++) {
                        quadrantBiomeWeighting[i] = 0;
                    }
                    // weighting strategy: weights go down as you move away from the corner.
                    // they go to 0 on the far edges so only the points on the edge have effects there
                    // for continuity with the next quadrant
                    addWeight(upperLeft,weightings[upperLeftFinding+findingsOffset]*(7-x)*(7-z));
                    addWeight(upperRight,weightings[upperRightFinding+findingsOffset]*x*(7-z));
                    addWeight(lowerLeft,weightings[lowerLeftFinding+findingsOffset]*(7-x)*z);
                    addWeight(lowerRight,weightings[lowerRightFinding+findingsOffset]*x*z);
                    biomes[biomeIndex(x,z)+biomesOffset] = preferredBiome();
                }
            }

        }

    }

    private float riverAdjusted (float top, float river) {
        if (river>=1) return top;
        float erodedRiver = river/RealisticBiomeBase.actualRiverProportion;
        if (erodedRiver <= 1f) {
            top = top*(1-erodedRiver)+62f*erodedRiver;
        }
        top = top*(1-river)+62f*river;
        return top;
    }
}

