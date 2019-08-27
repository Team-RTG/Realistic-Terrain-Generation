package rtg.world.biome.realistic.zoesteria;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeZOEStonyReef extends RealisticBiomeZOEBase {

    public RealisticBiomeZOEStonyReef(Biome biome) {
        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainVanillaOcean();
    }

    public static class TerrainVanillaOcean extends TerrainBase {

        public TerrainVanillaOcean() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainOcean(x, y, rtgWorld, river, 56f);
        }
    }
}
