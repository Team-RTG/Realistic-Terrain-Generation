package rtg.util;

import net.minecraft.world.biome.Biome;

/**
 *
 * @author Zeno410
 */
public class BiomeSet {

    private boolean [] biomes = new boolean[256];


    public class Water {
        public Water() {
            biomes[Biome.deepOcean.biomeID] = true;
            biomes[Biome.frozenOcean.biomeID] = true;
            biomes[Biome.frozenRiver.biomeID] = true;
            biomes[Biome.ocean.biomeID] = true;
            biomes[Biome.river.biomeID] = true;
        }
    }

    public boolean has(int biomeID) {
        return biomes[biomeID];
    }
}