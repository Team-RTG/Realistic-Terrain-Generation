
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

    
    public BiomeAnalyzer() {
        determineRiverBiomes();
        determineOceanBiomes();
        determineSwampBiomes();
        determineBeachBiomes();
        determineLandBiomes();
        prepareSearchPattern();
    }

    private void determineRiverBiomes() {
        riverBiome = new boolean[256];
        for (int index = 0; index < 256; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("river")) {
                riverBiome[index] = true;
            }
        }
    }

    private void determineOceanBiomes() {
        oceanBiome = new boolean[256];
        for (int index = 0; index < 256; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
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
        swampBiome = new boolean[256];
        for (int index = 0; index < 256; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
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
            if (BiomeGenBase.getBiome(index).biomeID==BiomeGenBase.frozenRiver.biomeID) {
                swampBiome[index] = true;
            }
        }
    }
    private void determineLandBiomes() {
        landBiome = new boolean[256];
        for (int index = 0; index < 256; index++) {
            if (!oceanBiome[index]) {
                if (!riverBiome[index]) {
                     if (BiomeGenBase.getBiome(index) == null) continue;
                     if (beachBiome[index]) continue;
                     if (!BiomeGenBase.getBiome(index).biomeName.toLowerCase().equals("lake")) {
                         landBiome[index] = true;
                     }

                }
            }
        }
    }

    private void determineBeachBiomes() {
        beachBiome = new boolean[256];
        for (int index = 0; index < 256; index++) {
            if (BiomeGenBase.getBiome(index) == null) continue;
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("beach")) {
                beachBiome[index] = true;
            }
            if (BiomeGenBase.getBiome(index).biomeName.toLowerCase().contains("mangrove")) {
                beachBiome[index] = true;
            }
        }
    }

    public void repair(int [] genLayerBiomes, RealisticBiomeBase [] jitteredBiomes, float [] noise, float riverStrength) {
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


    /*private void prepareSearchPattern() {
        // returns a search pattern of indices into 16x16 biome arrays, stored in an array
        int [] result= new int[256];
        int index = 0;
        int i;
        int j;
        for (int ring = 0; ring <8; ring++) {
            int length = (ring*2)+1;
            // top
            i = 7-ring;
            j = 7-ring;
            for (int place = 0; place<length; place++) {
                result[index++] = i*16+j;
                j++;
            }
            //right
            for (int place = 0; place<length; place++) {
                result[index++] = i*16+j;
                i++;
            }
            // bottom
            for (int place = 0; place<length; place++) {
                result[index++] = i*16+j;
                j--;
            }
            // left
            for (int place = 0; place<length; place++) {
                result[index++] = i*16+j;
                i--;
            }
        }
        searchPattern = result;
    }*/

    private void prepareSearchPattern() {
        searchPattern = new CircularSearchCreator().pattern();
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
}
