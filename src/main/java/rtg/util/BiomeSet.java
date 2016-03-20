package rtg.util;

import rtg.world.biome.realistic.RealisticBiomeBase;

import static net.minecraft.init.Biomes.*;

/**
 * @author Zeno410
 */
public class BiomeSet {

    private boolean[] biomes = new boolean[256];


    public class Water {
        public Water() {
            biomes[RealisticBiomeBase.getIdForBiome(deepOcean)] = true;
            biomes[RealisticBiomeBase.getIdForBiome(frozenOcean)] = true;
            biomes[RealisticBiomeBase.getIdForBiome(frozenRiver)] = true;
            biomes[RealisticBiomeBase.getIdForBiome(ocean)] = true;
            biomes[RealisticBiomeBase.getIdForBiome(river)] = true;
        }
    }

    public boolean has(int biomeID) {
        return biomes[biomeID];
    }
}