package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainSwampMountain extends TerrainBase {

    private float heigth;
    private float width;

    public TerrainSwampMountain(float mountainHeight, float mountainWidth) {

        heigth = mountainHeight;
        width = mountainWidth;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        return terrainSwampMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, width, heigth, 150f, 32f, 56f);
    }
}
