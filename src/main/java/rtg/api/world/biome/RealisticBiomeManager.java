package rtg.api.world.biome;

import java.util.ArrayList;

/**
 * Created by WhichOnesPink on 08/02/2017.
 */
public class RealisticBiomeManager {

    private static ArrayList<IRealisticBiome> realisticBiomes = new ArrayList<IRealisticBiome>();

    /*
     * Modders, use this method to register your realistic biomes during init().
     */
    public static void addBiome(IRealisticBiome realisticBiome) {

        realisticBiomes.add(realisticBiome);
    }

    public static ArrayList<IRealisticBiome> getBiomes() {
        return realisticBiomes;
    }
}
