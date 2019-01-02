package rtg.world.biome;

import java.util.Arrays;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import rtg.api.RTGAPI;
import rtg.api.util.CircularSearchCreator;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.gen.ChunkLandscape;


public final class BiomeAnalyzer {

    private static final int NO_BIOME   = -1;
    private static final int MAX_BIOMES = 256;
    private boolean[] riverBiome = new boolean[MAX_BIOMES];
    private boolean[] oceanBiome = new boolean[MAX_BIOMES];
    private boolean[] swampBiome = new boolean[MAX_BIOMES];
    private boolean[] beachBiome = new boolean[MAX_BIOMES];
    private boolean[] landBiome  = new boolean[MAX_BIOMES];
    private int[] preferredBeach;
    //hardcode these because they are world-persistent
    private IRealisticBiome scenicLakeBiome       = RTGAPI.getRTGBiome(Biomes.RIVER);
    private IRealisticBiome scenicFrozenLakeBiome = RTGAPI.getRTGBiome(Biomes.FROZEN_RIVER);
    private SmoothingSearchStatus beachSearch;
    private SmoothingSearchStatus landSearch;
    private SmoothingSearchStatus oceanSearch;

    public BiomeAnalyzer() {
        initBiomes();
        setupBeachesForBiomes();
        setSearches();
    }

    public int[] xyinverted() {

        int[] result = new int[MAX_BIOMES];

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                result[i * 16 + j] = j * 16 + i;
            }
        }

        for (int i = 0; i < MAX_BIOMES; i++) {
            if (result[result[i]] != i) {
                throw new RuntimeException("" + i + " " + result[i] + " " + result[result[i]]);
            }
        }

        return result;
    }

    private void initBiomes() {

        ForgeRegistries.BIOMES.getValuesCollection().forEach(biome -> {

            int id = Biome.getIdForBiome(biome);
            if (BiomeDictionary.hasType(biome, Type.OCEAN)) {
                oceanBiome[id] = true;
            }
            else if (BiomeDictionary.hasType(biome, Type.RIVER)) {
                riverBiome[id] = true;
            }
            else if (BiomeDictionary.hasType(biome, Type.SWAMP)) {
                swampBiome[id] = true;
            }
            else if (BiomeDictionary.hasType(biome, Type.BEACH)) {
                beachBiome[id] = true;
            }
            else {
                landBiome[id] = true;
            }
        });
    }

    // TODO: [1.12] A biomes 'prefered beach' should be added to the API in IRealisticBiome with the data stored in RealisticBiomeBase. Should use the BiomeConfig beach entry.
    private void setupBeachesForBiomes() {

        preferredBeach = new int[MAX_BIOMES];

        for (int i = 0; i < preferredBeach.length; i++) {

            // We need to work with the realistic biome, so let's try to get it from the base biome, aborting if necessary.
            Biome biome = Biome.getBiome(i);
            if (biome == null) {
                continue;
            }
            IRealisticBiome realisticBiome = RTGAPI.RTG_BIOMES.getValueAt(i);
            if (realisticBiome == null) {
                continue;
            }

            preferredBeach[i] = realisticBiome.getBeachBiome().baseBiomeId();
        }
    }

    // TODO: [1.12] genLayerBiomes should be a Biome[] where we can just grab the IRealisticBiome from the new BiomeMap
    public void newRepair(final Biome[] genLayerBiomes, final int[] biomeNeighborhood, final ChunkLandscape landscape) {

        final IRealisticBiome[] jitteredBiomes = landscape.biome;
        final float[]           noise          = landscape.noise;
        final float[]           riverStrength  = landscape.river;

        IRealisticBiome realisticBiome;
        int realisticBiomeId;

        // currently just stuffs the genLayer into the jitter;
        for (int i = 0; i < MAX_BIOMES; i++) {

            realisticBiome = RTGAPI.getRTGBiome(genLayerBiomes[i]);
            realisticBiomeId = realisticBiome.baseBiomeId();

            boolean canBeRiver = riverStrength[i] > 0.7;

            if (noise[i] > 61.5) {
                // replace
                jitteredBiomes[i] = realisticBiome;
            }
            else {
                // check for river
                if (canBeRiver && !oceanBiome[realisticBiomeId] && !swampBiome[realisticBiomeId]) {
                    // make river
                    jitteredBiomes[i] = realisticBiome.getRiverBiome();
                }
                else {
                    // replace
                    jitteredBiomes[i] = realisticBiome;
                }
            }
        }

        // put beaches on shores
        beachSearch.setNotHunted();
        beachSearch.setAbsent();
        float beachTop = 64.5f;
        for (int i = 0; i < MAX_BIOMES; i++) {
            if (beachSearch.isAbsent()) {
                break; //no point
            }
            float beachBottom = 61.5f;
            if (noise[i] < beachBottom || noise[i] > riverAdjusted(beachTop, riverStrength[i])) {
                continue;// this block isn't beach level
            }
            int biomeID = Biome.getIdForBiome(jitteredBiomes[i].baseBiome());
            if (swampBiome[biomeID]) {
                continue;// swamps are acceptable at beach level
            }
            if (beachSearch.isNotHunted()) {
                beachSearch.hunt(biomeNeighborhood);
                landSearch.hunt(biomeNeighborhood);
            }
            int foundBiome = beachSearch.biomes[i];
            if (foundBiome != NO_BIOME) {
                int nearestLandBiome = landSearch.biomes[i];
                if (nearestLandBiome > -1) {
                    foundBiome = preferredBeach[nearestLandBiome];
                }
                jitteredBiomes[i] = RTGAPI.getRTGBiome(foundBiome);
            }
        }

        // put land higher up;
        landSearch.setAbsent();
        landSearch.setNotHunted();
        for (int i = 0; i < MAX_BIOMES; i++) {
            if (landSearch.isAbsent() && beachSearch.isAbsent()) {
                break; //no point
            }
            // skip if this block isn't above beach level, adjusted for river effect to prevent abrupt beach stops
            if (noise[i] < riverAdjusted(beachTop, riverStrength[i])) {
                continue;
            }
            int biomeID = Biome.getIdForBiome(jitteredBiomes[i].baseBiome());
            // already land
            if (landBiome[biomeID]) {
                continue;
            }
            // swamps are acceptable above water
            if (swampBiome[biomeID]) {
                continue;
            }
            if (landSearch.isNotHunted()) {
                landSearch.hunt(biomeNeighborhood);
            }
            int foundBiome = landSearch.biomes[i];

            if (foundBiome == NO_BIOME) {
                // no land found; try for a beach
                if (beachSearch.isNotHunted()) {
                    beachSearch.hunt(biomeNeighborhood);
                }
                foundBiome = beachSearch.biomes[i];
            }

            if (foundBiome != NO_BIOME) {
                jitteredBiomes[i] = RTGAPI.getRTGBiome(foundBiome);
            }
        }

        // put ocean below sea level
        oceanSearch.setAbsent();
        oceanSearch.setNotHunted();
        for (int i = 0; i < MAX_BIOMES; i++) {
            if (oceanSearch.isAbsent()) {
                break; //no point
            }
            float oceanTop = 61.5f;
            if (noise[i] > oceanTop) {
                continue;// too hight
            }
            int biomeID = Biome.getIdForBiome(jitteredBiomes[i].baseBiome());
            if (oceanBiome[biomeID]) {
                continue;// obviously ocean is OK
            }
            if (swampBiome[biomeID]) {
                continue;// swamps are acceptable
            }
            if (riverBiome[biomeID]) {
                continue;// rivers stay rivers
            }
            if (oceanSearch.isNotHunted()) {
                oceanSearch.hunt(biomeNeighborhood);
            }
            int foundBiome = oceanSearch.biomes[i];

            if (foundBiome != NO_BIOME) {
                jitteredBiomes[i] = RTGAPI.getRTGBiome(foundBiome);
            }
        }
        // convert remainder below sea level to lake biome
        for (int i = 0; i < MAX_BIOMES; i++) {
            int biomeID = Biome.getIdForBiome(jitteredBiomes[i].baseBiome());
            if (noise[i] <= 61.5 && !riverBiome[biomeID]) {
                // check for river
                if (!oceanBiome[biomeID] && !swampBiome[biomeID] && !beachBiome[biomeID]) {
                    int riverReplacement = jitteredBiomes[i].getRiverBiome().baseBiomeId(); // make river
                    if (riverReplacement == Biome.getIdForBiome(Biomes.FROZEN_RIVER)) {
                        jitteredBiomes[i] = scenicFrozenLakeBiome;
                    }
                    else {
                        jitteredBiomes[i] = scenicLakeBiome;
                    }
                }
            }
        }
    }

    private void setSearches() {
        beachSearch = new SmoothingSearchStatus(this.beachBiome);
        landSearch = new SmoothingSearchStatus(this.landBiome);
        oceanSearch = new SmoothingSearchStatus(this.oceanBiome);
    }

    private float riverAdjusted(float top, float river) {
        if (river >= 1) {
            return top;
        }
        float erodedRiver = river / RTGWorld.ACTUAL_RIVER_PROPORTION;
        if (erodedRiver <= 1f) {
            top = top * (1 - erodedRiver) + 62f * erodedRiver;
        }
        top = top * (1 - river) + 62f * river;
        return top;
    }

    private static final class SmoothingSearchStatus {

        private final int upperLeftFinding = 0;
        private final int upperRightFinding = 3;
        private final int lowerLeftFinding = 1;
        private final int lowerRightFinding = 4;
        private final int[] quadrantBiome = new int[4];
        private final float[] quadrantBiomeWeighting = new float[4];
        public int[] biomes = new int[MAX_BIOMES];
        private boolean absent = false;
        private boolean notHunted;
        private int[] findings = new int[3 * 3];
        // weightings is part of a system to generate some variability in repaired chunks weighting is
        // based on how long the search went on (so quasipsuedorandom, based on direction plus distance
        private float[] weightings = new float[3 * 3];
        private boolean[] desired;
        private int arraySize;
        private int[] pattern;
        private int biomeCount;

        private SmoothingSearchStatus(boolean[] desired) {
            this.desired = desired;
        }

        private int size() {
            return 3;
        }

        private void hunt(int[] biomeNeighborhood) {
            // 0,0 in the chunk is 9,9 int the array ; 8,8 is 10,10 and is treated as the center
            clear();
            int oldArraySize = arraySize;
            arraySize = (int) Math.sqrt(biomeNeighborhood.length);
            if (arraySize * arraySize != biomeNeighborhood.length) {
                throw new RuntimeException("non-square array");
            }

            if (arraySize != oldArraySize) {
                pattern = new CircularSearchCreator().pattern(arraySize / 2f - 1, arraySize);
            }

            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    search(xOffset, zOffset, biomeNeighborhood);
                }
            }
            // calling a routine because it gets too indented otherwise
            smoothBiomes();
        }

        private void search(int xOffset, int zOffset, int[] biomeNeighborhood) {
            int offset = xOffset * arraySize + zOffset;
            int location = (xOffset + 1) * size() + zOffset + 1;
            // set to failed search, which sticks if nothing is found
            findings[location] = NO_BIOME;
            weightings[location] = 2f;
            for (int i = 0; i < pattern.length; i++) {
                int biome = biomeNeighborhood[pattern[i] + offset];
                if (desired[biome]) {
                    findings[location] = biome;
                    weightings[location] = (float) Math.sqrt(pattern.length) - (float) Math.sqrt(i) + 2f;
                    break;
                }
            }
        }

        private void smoothBiomes() {
            // more sophisticated version offsets into findings and biomes upperleft
            smoothQuadrant(biomeIndex(0, 0), upperLeftFinding);
            smoothQuadrant(biomeIndex(8, 0), upperRightFinding);
            smoothQuadrant(biomeIndex(0, 8), lowerLeftFinding);
            smoothQuadrant(biomeIndex(8, 8), lowerRightFinding);
        }

        private void smoothQuadrant(int biomesOffset, int findingsOffset) {
            int upperLeft = findings[upperLeftFinding + findingsOffset];
            int upperRight = findings[upperRightFinding + findingsOffset];
            int lowerLeft = findings[lowerLeftFinding + findingsOffset];
            int lowerRight = findings[lowerRightFinding + findingsOffset];
            // check for uniformity
            if ((upperLeft == upperRight) && (upperLeft == lowerLeft) && (upperLeft == lowerRight)) {
                // everythings the same; uniform fill;
                for (int x = 0; x < 8; x++) {
                    for (int z = 0; z < 8; z++) {
                        biomes[biomeIndex(x, z) + biomesOffset] = upperLeft;
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
            for (int x = 0; x < 8; x++) {
                for (int z = 0; z < 8; z++) {
                    addBiome(lowerRight);
                    for (int i = 0; i < 4; i++) {
                        quadrantBiomeWeighting[i] = 0;
                    }
                    // weighting strategy: weights go down as you move away from the corner.
                    // they go to 0 on the far edges so only the points on the edge have effects there
                    // for continuity with the next quadrant
                    addWeight(upperLeft, weightings[upperLeftFinding + findingsOffset] * (7 - x) * (7 - z));
                    addWeight(upperRight, weightings[upperRightFinding + findingsOffset] * x * (7 - z));
                    addWeight(lowerLeft, weightings[lowerLeftFinding + findingsOffset] * (7 - x) * z);
                    addWeight(lowerRight, weightings[lowerRightFinding + findingsOffset] * x * z);
                    biomes[biomeIndex(x, z) + biomesOffset] = preferredBiome();
                }
            }
        }

        private void addBiome(int biome) {
            for (int i = 0; i < biomeCount; i++) {
                if (biome == quadrantBiome[i]) {
                    return;
                }
            }
            // not there, add
            quadrantBiome[biomeCount++] = biome;
        }

        private void addWeight(int biome, float weight) {
            for (int i = 0; i < biomeCount; i++) {
                if (biome == quadrantBiome[i]) {
                    quadrantBiomeWeighting[i] += weight;
                    return;
                }
            }
        }

        private int preferredBiome() {
            float bestWeight = 0;
            int result = -2;
            for (int i = 0; i < biomeCount; i++) {
                if (quadrantBiomeWeighting[i] > bestWeight) {
                    bestWeight = quadrantBiomeWeighting[i];
                    result = quadrantBiome[i];
                }
            }
            return result;
        }

        private int biomeIndex(int x, int z) {
            return x * 16 + z;
        }

        private void clear() {
            Arrays.fill(findings, -1);
        }

        private boolean isAbsent() {
            return absent;
        }

        private void setAbsent() {
            this.absent = false;
        }

        private boolean isNotHunted() {
            return notHunted;
        }

        private void setNotHunted() {
            this.notHunted = true;
        }
    }
}