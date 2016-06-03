package teamrtg.rtg.api.module;

import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.ArrayList;

/**
 * Holds all of a mods biomes as public final fields
 * @author topisani
 */
public class ModBiomes {

    private ArrayList<RTGBiome> biomes = new ArrayList<>();

    public void initBiomes() {}

    protected RTGBiome addBiome(RTGBiome biome) {
        biomes.add(biome);
        new RealisticBiomeGenerator(biome);
        return biome;
    }

    public RTGBiome[] getBiomes() {
        return biomes.toArray(new RTGBiome[biomes.size()]);
    }
}
