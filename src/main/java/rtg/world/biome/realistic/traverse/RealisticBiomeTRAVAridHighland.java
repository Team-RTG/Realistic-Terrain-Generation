package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVAridHighland extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVAridHighland(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainHLAdirondacks(230f, 60f, 88f);
    }

    public class TerrainHLAdirondacks extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;
        private int wavelength = 40;
        private ISimplexData2D jitter = SimplexData2D.newDisk();
        private double amplitude = 10;

        public TerrainHLAdirondacks(float mountainWidth, float mountainStrength, float height) {
            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            rtgWorld.simplexInstance(1).multiEval2D((float) x / wavelength, (float) y / wavelength, jitter);
            int pX = (int) Math.round(x + jitter.getDeltaX() * amplitude);
            int pY = (int) Math.round(y + jitter.getDeltaY() * amplitude);
            x = pX;
            y = pY;

            return terrainLonelyMountain(x, y, rtgWorld, river, strength, width, terrainHeight);
        }
    }
}
