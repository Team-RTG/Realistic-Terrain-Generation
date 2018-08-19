package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVDesertShrubland extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVDesertShrubland(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPShrubland();
    }

    public class TerrainBOPShrubland extends TerrainBase {

        public TerrainBOPShrubland() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 100f, 65f);
        }
    }
}
