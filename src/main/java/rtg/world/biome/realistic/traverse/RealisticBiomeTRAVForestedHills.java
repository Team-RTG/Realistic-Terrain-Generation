package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVForestedHills extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVForestedHills(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaForestHills();
    }

    public class TerrainVanillaForestHills extends TerrainBase {

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
