package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.gen.RTGChunkGenSettings;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVRedDesert extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVRedDesert(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaDesert();
    }

    public class TerrainVanillaDesert extends TerrainBase {

        public TerrainVanillaDesert() {

            super(64);
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            RTGChunkGenSettings settings = rtgWorld.getGeneratorSettings();
            float duneHeight = (minDuneHeight + settings.sandDuneHeight);

            duneHeight *= (1f + rtgWorld.simplexInstance(2).noise2f(x / 330f, y / 330f)) / 2f;

            float stPitch = 200f;    // The higher this is, the more smoothly dunes blend with the terrain
            float stFactor = duneHeight;
            float hPitch = 70;    // Dune scale
            float hDivisor = 40;

            return terrainPolar(x, y, rtgWorld, river, stPitch, stFactor, hPitch, hDivisor, base) + groundNoise(x, y, 1f, rtgWorld);
        }
    }
}
