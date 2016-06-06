package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainSwampMountain extends TerrainBase {
    private float heigth;
    private float width;

    public TerrainSwampMountain(float mountainHeight, float mountainWidth) {
        heigth = mountainHeight;
        width = mountainWidth;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        return terrainSwampMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, width, heigth, 150f, 32f, 56f);
    }
}
