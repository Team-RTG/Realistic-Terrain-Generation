package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainLonelyMountain extends TerrainBase {
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

    public TerrainLonelyMountain(float mountainWidth, float mountainStrength, float depthLake) {
        this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
    }

    public TerrainLonelyMountain(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height) {
        width = mountainWidth;
        strength = mountainStrength;
        lakeDepth = depthLake;
        lakeWidth = widthLake;
        terrainHeight = height;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainLonelyMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, strength, width, terrainHeight);
    }
}
