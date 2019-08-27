package rtg.world.biome.realistic.zoesteria;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeZOEWhiteOaksHills extends RealisticBiomeZOEBase {

    public RealisticBiomeZOEWhiteOaksHills(Biome biome) {
        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainVanillaForestHills();
    }

    public static class TerrainVanillaForestHills extends TerrainBase {

        private float hillStrength = 30f;

        public TerrainVanillaForestHills() {

            this(72f, 30f);
        }

        public TerrainVanillaForestHills(float bh, float hs) {

            base = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainHighland(x, y, rtgWorld, river, 10f, 68f, hillStrength, base - 62f);

        }
    }
}
