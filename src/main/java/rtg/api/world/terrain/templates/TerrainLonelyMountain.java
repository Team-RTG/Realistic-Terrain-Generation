package rtg.api.world.terrain.templates;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

public class TerrainLonelyMountain extends TerrainBase {

    private float width;
    private float strength;

    public TerrainLonelyMountain(float mountainWidth, float mountainStrength, float height) {

        width = mountainWidth;
        strength = mountainStrength;
        base = height;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainLonelyMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, strength, width, base);
    }
}
