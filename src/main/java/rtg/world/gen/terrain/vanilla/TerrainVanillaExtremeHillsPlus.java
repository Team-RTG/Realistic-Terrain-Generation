package rtg.world.gen.terrain.vanilla;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.MountainsWithPassesEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaExtremeHillsPlus extends TerrainBase {

    private float width;
    private float strength;
    private float lakeDepth;
    private float lakeWidth;
    private float terrainHeight;
    private float spikeWidth = 30;
    private float spikeHeight = 40;
    private HeightEffect heightEffect;
    /*
     * width = 230f strength = 120f lake = 50f;
     *
     * 230f, 120f, 50f
     */

    public TerrainVanillaExtremeHillsPlus(float mountainWidth, float mountainStrength, float height) {
        width = mountainWidth;
        strength = mountainStrength;
        terrainHeight = height;
        MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
        mountainEffect.mountainHeight = strength;
        mountainEffect.mountainWavelength = width;
        mountainEffect.spikeHeight = this.spikeHeight;
        mountainEffect.spikeWavelength = this.spikeWidth;

        heightEffect = new JitterEffect(7f, 10f, mountainEffect);
        heightEffect = new JitterEffect(3f, 6f, heightEffect);
        //this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
    }

    public TerrainVanillaExtremeHillsPlus(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height) {

        width = mountainWidth;
        strength = mountainStrength;
        lakeDepth = depthLake;
        lakeWidth = widthLake;
        terrainHeight = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return riverized(heightEffect.added(simplex, cell, x, y) + terrainHeight, river);
        //return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
    }
}
