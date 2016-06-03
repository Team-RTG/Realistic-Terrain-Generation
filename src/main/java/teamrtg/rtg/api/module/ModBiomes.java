package teamrtg.rtg.api.module;

import teamrtg.rtg.api.world.biome.RTGBiomeBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.ArrayList;

/**
 * Holds all of a mods biomes as public final fields
 * @author topisani
 */
public class ModBiomes {

    private ArrayList<RTGBiomeBase> biomes = new ArrayList<>();

    public void initBiomes() {}

    protected RTGBiomeBase addBiome(RTGBiomeBase biome) {
        biomes.add(biome);
        new RealisticBiomeGenerator(biome);
        return biome;
    }

    public RTGBiomeBase[] getBiomes() {
        return biomes.toArray(new RTGBiomeBase[biomes.size()]);
    }
}
