
package rtg.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CircularSearchCreator;
import rtg.world.biome.realistic.RealisticBiomeBase;



/**
 * @author Zeno410, Modified by srs_bsns 20160914
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
    private RealisticBiomeBase scenicFrozenLakeBiome = RealisticBiomeBase.getBiome(ConfigRTG.scenicFrozenLakeBiome);
    private SmoothingSearchStatus beachSearch;
    private SmoothingSearchStatus landSearch;
    private SmoothingSearchStatus oceanSearch;
    private final static int NO_BIOME = -1;

    public BiomeAnalyzer() {
        determineRiverBiomes();
        determineOceanBiomes();
        determineSwampBiomes();
        determineBeachBiomes();
        determineLandBiomes();
        setupDefaultBeachesForBiomes();
        prepareSearchPattern();
        setSearches();
        savedJittered = new RealisticBiomeBase[256];
    }

    /**
     *
     * @author Zeno410, Modified by srs_bsns 20160914
     */
    private class SmoothingSearchStatus
    {
        boolean absent = false;
        boolean notHunted;
        private int size() {return 3;}
        private int [] findings = new int [3*3];
        // weightings is part of a system to generate some variability in repaired chunks weighting is
        // based on how long the search went on (so quasipsuedorandom, based on direction plus distance
        private float [] weightings = new float [3*3];
        public int [] biomes = new int [256];
        private boolean [] desired;
        private int arraySize;
        private int [] pattern;
        private final int upperLeftFinding = 0;
        private final int upperRightFinding = 3;
        private final int lowerLeftFinding = 1;
        private final int lowerRightFinding = 4;
        private final int [] quadrantBiome = new int[4];
        private final float [] quadrantBiomeWeighting = new float [4];
        private int biomeCount;

        SmoothingSearchStatus(boolean[] desired) { this.desired = desired; }

        void hunt(int[] biomeNeighborhood) {
            // 0,0 in the chunk is 9,9 int the array ; 8,8 is 10,10 and is treated as the center
            clear();
            int oldArraySize = arraySize;
            arraySize = (int)Math.sqrt(biomeNeighborhood.length);
            if (arraySize*arraySize != biomeNeighborhood.length)
                throw new RuntimeException("non-square array");
            if (arraySize != oldArraySize)
                pattern = new CircularSearchCreator().pattern(arraySize/2-1, arraySize);
            for (int xOffset = -1; xOffset<=1; xOffset++)
                for (int zOffset = -1; zOffset<=1; zOffset++)
                    search(xOffset,zOffset, biomeNeighborhood);
            // calling a routine because it gets too indented otherwise
            smoothBiomes();
        }

        private void search(int xOffset, int zOffset, int [] biomeNeighborhood) {
            int offset = xOffset*arraySize +zOffset;
            int location = (xOffset+1)*size()+zOffset+1;
            // set to failed search, which sticks if nothing is found
            findings[location] = NO_BIOME;
            weightings[location] = 2f;
            for (int i = 0; i < pattern.length; i++) {
                int biome = biomeNeighborhood[pattern[i]+offset];
                if (desired[biome]) {
                    findings[location]=biome;
                    weightings[location] = (float)Math.sqrt(pattern.length) -(float)Math.sqrt(i) + 2f ;
                    break;
                }
            }
        }

        private void smoothBiomes() {
            // more sophisticated version offsets into findings and biomes upperleft
            smoothQuadrant(biomeIndex(0,0),upperLeftFinding);
            smoothQuadrant(biomeIndex(8,0),upperRightFinding);
            smoothQuadrant(biomeIndex(0,8),lowerLeftFinding);
            smoothQuadrant(biomeIndex(8,8),lowerRightFinding);
        }

        private void smoothQuadrant(int biomesOffset, int findingsOffset) {
            int upperLeft = findings[upperLeftFinding+findingsOffset];
            int upperRight = findings[upperRightFinding+findingsOffset];
            int lowerLeft = findings[lowerLeftFinding+findingsOffset];
            int lowerRight = findings[lowerRightFinding+findingsOffset];
            // check for uniformity
            if ((upperLeft == upperRight)&&(upperLeft == lowerLeft)&&(upperLeft == lowerRight)){
                // everythings the same; uniform fill;
                for (int x = 0; x<8;x++)
                    for (int z =0; z<8;z++)
                        biomes[biomeIndex(x,z)+biomesOffset] = upperLeft;
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
                    for (int i  = 0; i < 4; i ++) quadrantBiomeWeighting[i] = 0;
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

        private void addBiome(int biome) {
            for (int i = 0; i < biomeCount; i++)
                if (biome == quadrantBiome[i]) return;
            // not there, add
            quadrantBiome[biomeCount++] = biome;
        }

        private void addWeight(int biome, float weight) {
            for (int i = 0; i <biomeCount ; i++) {
                if (biome == quadrantBiome[i]) {
                    quadrantBiomeWeighting[i] += weight;
                    return;
                }
            }
        }

        private int preferredBiome() {
            float bestWeight = 0;
            int result = -2;
            for (int i = 0; i <biomeCount ; i++) {
                if (quadrantBiomeWeighting[i]>bestWeight) {
                    bestWeight =quadrantBiomeWeighting[i];
                    result = quadrantBiome[i];
                }
            }
            return result;
        }

        private int biomeIndex(int x, int z)
        {
            return x*16+z;
        }

        private void clear()
        {
            for (int i = 0;i <findings.length; i++) findings[i] = -1;
        }

    }

    private void determineRiverBiomes() {
        riverBiome = new boolean[256];
        for (int i = 0; i < riverBiome.length; i++) {
            Biome biome = Biome.getBiome(i);
            if (biome == null) continue;
            if (biome.getBiomeName().toLowerCase().contains("river")) riverBiome[i] = true;
        }
    }

    private void determineOceanBiomes() {
        oceanBiome = new boolean[256];
        for (int i = 0; i < oceanBiome.length; i++) {
            Biome biome = Biome.getBiome(i);
            if (biome == null) continue;
            if (biome.getBiomeName().toLowerCase().contains("ocean")) oceanBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("kelp"))  oceanBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("coral")) oceanBiome[i] = true;
        }
        oceanBiome[Biome.getIdForBiome(Biomes.DEEP_OCEAN)]=true;// not getting set?
    }

    private void determineSwampBiomes() {
        swampBiome = new boolean[256];
        for (int i = 0; i < swampBiome.length; i++) {
            Biome biome = Biome.getBiome(i);
            if (biome == null) continue;
            if (biome.getBiomeName().toLowerCase().contains("swamp"))             swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("bayou"))             swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("bog"))               swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("wetland"))           swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("sludge"))            swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("marsh"))             swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("fen"))               swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("moor"))              swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("quagmire"))          swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("ephemeral lake"))    swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("rainforest valley")) swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("riparian zone"))     swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("ice sheet"))         swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("woodland lake"))     swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().contains("archipelago"))       swampBiome[i] = true;
            if (biome.getBiomeName().toLowerCase().equals("shield"))              swampBiome[i] = true;
            if (Biome.getIdForBiome(biome) == Biome.getIdForBiome(Biomes.FROZEN_RIVER)) swampBiome[i] = true;
        }
    }

    private void determineLandBiomes() {
        landBiome = new boolean[256];
        for (int i = 0; i < landBiome.length; i++) {
            if (!oceanBiome[i] && !riverBiome[i] && !beachBiome[i]) {
                Biome biome = Biome.getBiome(i);
                if (biome == null) continue;
                if (!biome.getBiomeName().toLowerCase().equals("lake")) landBiome[i] = true;
            }
        }
    }

    private void determineBeachBiomes() {
        beachBiome = new boolean[256];
        for (int i = 0; i < beachBiome.length; i++) {
            Biome biome = Biome.getBiome(i);
            if (biome == null) continue;
            if (biome.getBiomeName().toLowerCase().contains("beach") ||
                biome.getBiomeName().toLowerCase().contains("mangrove")) beachBiome[i] = true;
        }
    }

    private void setupDefaultBeachesForBiomes() {

        preferredBeach = new int[256];

        for (int i = 0; i < preferredBeach.length; i++) {

            // We need to work with the realistic biome, so let's try to get it from the base biome, aborting if necessary.
            Biome biome = Biome.getBiome(i);
            if (biome == null) continue;
            RealisticBiomeBase realisticBiome = RealisticBiomeBase.getBiome(i);
            if (realisticBiome == null) continue;

            /*
             * Now that we have the realistic biome, let's make sure all biomes get a default beach.
             * To do this, let's try to determine the best beach based on the biome's height & temperature.
             * (Some of this code is from Climate Control, and it's still a bit crude. - Zeno)
             */
            float height = biome.getBaseHeight() + (biome.getHeightVariation() * 2f);
            float temp = biome.getTemperature();

            // Store the temp-specific beach in a variable so we can use it later on.
            Biome beach = (temp <= 0.05f) ? Biomes.COLD_BEACH : Biomes.BEACH;

            preferredBeach[i] = Biome.getIdForBiome(beach);

            // If this is a mountainous biome, then it should have a stone beach, unless disallowed.
            if ((height > (1.0f + 0.5f))) {
                preferredBeach[i] = Biome.getIdForBiome(realisticBiome.disallowStoneBeaches ? beach : Biomes.STONE_BEACH);
            }

            // If beaches aren't allowed in this biome, then use this biome as the beach.
            if (realisticBiome.disallowAllBeaches) {
                preferredBeach[i] = i;
            }
        }
    }

    public static Biome getDefaultBeachForBiome(Biome biome) {

        if (biome == null) throw new RuntimeException("Found NULL biome when getting default beach for biome.");

        RealisticBiomeBase realisticBiome = RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome));
        if (realisticBiome == null) throw new RuntimeException("Found NULL realistic biome when getting default beach for biome.");

        /*
         * Let's try to determine the best beach based on the biome's height & temperature.
         * (Some of this code is from Climate Control, and it's still a bit crude. - Zeno)
         */
        float height = biome.getBaseHeight() + (biome.getHeightVariation() * 2f);
        float temp = biome.getTemperature();

        // Use a cold beach if the temperature is low enough; otherwise, just use a normal beach.
        Biome beach = (temp <= 0.05f) ? Biomes.COLD_BEACH : Biomes.BEACH;

        // If this is a mountainous biome, then it should have a stone beach by default.
        if ((height > (1.0f + 0.5f))) {
            beach = Biomes.STONE_BEACH;
        }

        return beach;
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
                    int riverBiomeID = Biome.getIdForBiome(RealisticBiomeBase.getBiome(genLayerBiomes[i]).riverBiome);
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
            if (swampBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) continue;// swamps are acceptable at beach level
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
            // this block isn't above beach level
            if (noise[i] < riverAdjusted(beachTop, riverStrength[i])) continue;
            int biomeID = Biome.getIdForBiome(jitteredBiomes[i].baseBiome);
            // already land
            if (landBiome[biomeID]) continue;
            // swamps are acceptable above water
            if (swampBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) continue;
            if (landSearch.notHunted) landSearch.hunt(biomeNeighborhood);
            int foundBiome = landSearch.biomes[i];
            if (foundBiome != NO_BIOME) jitteredBiomes[i] = RealisticBiomeBase.getBiome(foundBiome);
        }

        // put ocean below sea level
        oceanSearch.absent = false;
        oceanSearch.notHunted = true;
        for (int i = 0; i < 256; i++) {
            if (oceanSearch.absent) break; //no point
            float oceanTop = 61.5f;
            if (noise[i]> oceanTop) continue;// too hight
            if (oceanBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) continue;// obviously ocean is OK
            if (swampBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) continue;// swamps are acceptable
            if (riverBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) continue;// rivers stay rivers
            if (oceanSearch.notHunted) oceanSearch.hunt(biomeNeighborhood);
            int foundBiome = oceanSearch.biomes[i];
            if (foundBiome != NO_BIOME) jitteredBiomes[i] = RealisticBiomeBase.getBiome(foundBiome);
        }
        // convert remainder below sea level to lake biome
        for (int i = 0; i < 256; i++) {
            if (noise[i]<=61.5&&!riverBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) {
                // check for river
                if (!oceanBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)] &&
                    !swampBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)] &&
                    !beachBiome[Biome.getIdForBiome(jitteredBiomes[i].baseBiome)]) {
                    int riverReplacement = Biome.getIdForBiome(jitteredBiomes[i].riverBiome); // make river
                    if (riverReplacement == Biome.getIdForBiome(Biomes.FROZEN_RIVER))
                        jitteredBiomes[i] = scenicFrozenLakeBiome;
                    else jitteredBiomes[i] = scenicLakeBiome;
                }
            }
        }
    }

    private void prepareSearchPattern() { /*if (searchPattern.length != 256) throw new RuntimeException();*/ }

    private void setSearches() {
        beachSearch = new SmoothingSearchStatus(this.beachBiome);
        landSearch  = new SmoothingSearchStatus(this.landBiome);
        oceanSearch = new SmoothingSearchStatus(this.oceanBiome);
    }

    private float riverAdjusted (float top, float river) {
        if (river>=1) return top;
        float erodedRiver = river/RealisticBiomeBase.actualRiverProportion;
        if (erodedRiver <= 1f) top = top*(1-erodedRiver)+62f*erodedRiver;
        top = top*(1-river)+62f*river;
        return top;
    }
}
