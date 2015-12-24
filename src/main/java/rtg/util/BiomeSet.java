package rtg.util;

import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
public class BiomeSet {

    private boolean [] biomes = new boolean[256];


    public class Water {
        public Water() {
            biomes[BiomeGenBase.deepOcean.biomeID] = true;
            biomes[BiomeGenBase.frozenOcean.biomeID] = true;
            biomes[BiomeGenBase.frozenRiver.biomeID] = true;
            biomes[BiomeGenBase.ocean.biomeID] = true;
            biomes[BiomeGenBase.river.biomeID] = true;
        }
    }

    public boolean has(int biomeID) {
        return biomes[biomeID];
    }
}