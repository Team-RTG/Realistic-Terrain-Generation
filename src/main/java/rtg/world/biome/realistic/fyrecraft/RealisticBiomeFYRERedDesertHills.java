package rtg.world.biome.realistic.fyrecraft;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeFYRERedDesertHills extends RealisticBiomeFYREBase {

    public RealisticBiomeFYRERedDesertHills(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaDesertHills(10f, 80f, 68f, 200f);
    }

    public static class TerrainVanillaDesertHills extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainVanillaDesertHills(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            return terrainHighland(x, y, rtgWorld, river, start, width, height, base - 62f);
        }
    }
}
