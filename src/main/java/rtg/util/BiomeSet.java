package rtg.util;

import net.minecraft.init.Biomes;

/**
 *
 * @author Zeno410
 */
public class BiomeSet {

    private boolean [] biomes = new boolean[256];


    public class Water {
        public Water() {
            biomes[Biomes.DEEP_OCEAN.biomeID] = true;
            biomes[Biomes.FROZEN_OCEAN.biomeID] = true;
            biomes[Biomes.FROZEN_RIVER.biomeID] = true;
            biomes[Biomes.OCEAN.biomeID] = true;
            biomes[Biomes.RIVER.biomeID] = true;
        }
    }

    public boolean has(int biomeID) {
        return biomes[biomeID];
    }
}