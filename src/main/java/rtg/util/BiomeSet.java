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
            biomes[BiomeUtils.getId(Biomes.DEEP_OCEAN)] = true;
            biomes[BiomeUtils.getId(Biomes.FROZEN_OCEAN)] = true;
            biomes[BiomeUtils.getId(Biomes.FROZEN_RIVER)] = true;
            biomes[BiomeUtils.getId(Biomes.OCEAN)] = true;
            biomes[BiomeUtils.getId(Biomes.RIVER)] = true;
        }
    }

    public boolean has(int biomeID) {
        return biomes[biomeID];
    }
}