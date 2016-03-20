package rtg.util;

import net.minecraft.world.biome.BiomeGenBase;

import static net.minecraft.init.Biomes.*;

/**
 * @author Zeno410
 */
public class BiomeSet {

    private boolean[] biomes = new boolean[256];


    public class Water {
        public Water() {
            biomes[BiomeGenBase.getIdForBiome(deepOcean)] = true;
            biomes[BiomeGenBase.getIdForBiome(frozenOcean)] = true;
            biomes[BiomeGenBase.getIdForBiome(frozenRiver)] = true;
            biomes[BiomeGenBase.getIdForBiome(ocean)] = true;
            biomes[BiomeGenBase.getIdForBiome(river)] = true;
        }
    }

    public boolean has(int biomeID) {
        return biomes[biomeID];
    }
}