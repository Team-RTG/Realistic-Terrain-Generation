package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeBYGAstralIsle extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGAstralIsle(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBayou();
    }

    public static class TerrainBOPBayou extends TerrainBase {

        public TerrainBOPBayou() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 80f, 1f, 40f, 20f, 62f);
        }
    }
}
