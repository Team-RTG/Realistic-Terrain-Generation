package rtg.api.mods;

import rtg.world.biome.realistic.RealisticBiomeBase;

import java.util.ArrayList;

/**
 * Holds all of a mods biomes as public final fields
 *
 * @author topisani
 */
public class ModBiomes {

    private ArrayList<RealisticBiomeBase> biomes = new ArrayList<>();

    public void initBiomes() {}

    protected RealisticBiomeBase addBiome(RealisticBiomeBase biome) {
        biomes.add(biome);
        return biome;
    }

    public RealisticBiomeBase[] getBiomes() {
        return biomes.toArray(new RealisticBiomeBase[biomes.size()]);
    }
}
