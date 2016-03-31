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
    private float terrainHeight;
    private float spikeWidth = 30;
    private float spikeHeight = 40;
    private HeightEffect heightEffect;
    /*
     * width = 230f strength = 120f lake = 50f;
     *
     * 230f, 120f, 50f
     */

    public TerrainVanillaExtremeHillsPlus() {
        width = 150f;
        strength = 120f;
        terrainHeight = 90f;
        MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
        mountainEffect.mountainHeight = strength;
        mountainEffect.mountainWavelength = width;
        mountainEffect.spikeHeight = this.spikeHeight;
        mountainEffect.spikeWavelength = this.spikeWidth;

        heightEffect = new JitterEffect(7f, 10f, mountainEffect);
        heightEffect = new JitterEffect(3f, 6f, heightEffect);
        //this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return riverized(heightEffect.added(simplex, cell, x, y) + terrainHeight, river);
    }
}
