package teamrtg.rtg.api.mods;

import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;

import java.util.ArrayList;

/**
 * Holds all of a mods biomes as public final fields
 * @author topisani
 */
public class ModBiomes {

    private ArrayList<RealisticBiomeBase> biomes = new ArrayList<>();

    public void initBiomes(ChunkProviderRTG chunkProvider) {}

    protected RealisticBiomeBase addBiome(RealisticBiomeBase biome) {
        biomes.add(biome);
        new RealisticBiomeGenerator(biome);
        return biome;
    }

    public RealisticBiomeBase[] getBiomes() {
        return biomes.toArray(new RealisticBiomeBase[biomes.size()]);
    }
}
