package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVCliffs extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVCliffs(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPOvergrownCliffs(300f, 100f, 0f);
    }

    public class TerrainBOPOvergrownCliffs extends TerrainBase {

        private float width;
        private float strength;
        private float lakeDepth;
        private float lakeWidth;
        private float terrainHeight;

	/*
     * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

        public TerrainBOPOvergrownCliffs(float mountainWidth, float mountainStrength, float depthLake) {

            this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
        }

        public TerrainBOPOvergrownCliffs(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            lakeDepth = depthLake;
            lakeWidth = widthLake;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, rtgWorld, river, strength, width, terrainHeight);
        }
    }
}
