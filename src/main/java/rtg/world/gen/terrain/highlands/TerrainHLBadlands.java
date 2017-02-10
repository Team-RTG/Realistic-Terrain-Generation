package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLBadlands extends TerrainBase
{
    private float hHeight;
    private float hWidth;
    private float vHeight;
    private float vWidth;
    private float bHeight;
    public static float mountainStart = 30;

    public TerrainHLBadlands(float hillHeight, float hillWidth, float varHeight, float varWidth, float baseHeight)
    {
        hHeight = hillHeight;
        hWidth = hillWidth;

        vHeight = varHeight;
        vWidth = varWidth;

        bHeight = baseHeight;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float h = simplex.noise2(x / vWidth, y / vWidth) * vHeight * river;
        h += simplex.noise2(x / 20f, y / 20f) * 2;

        float m = simplex.noise2(x / hWidth, y / hWidth) * hHeight * river;
        m *= m / 40f;

        float sm = simplex.noise2(x / 30f, y / 30f) * 8f;
        sm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += sm;

        float cm = cell.noise(x / 25D, y / 25D, 1D) * 12f;
        cm *= m / 20f > 3.75f ? 3.75f : m / 20f;
        m += cm;

        // this was originally in h but I moved it to m to get smoother plains
        m += simplex.noise2(x / 12f, y / 12f) * 3f;
        m += simplex.noise2(x / 5f, y / 5f) * 1.5f;

        // make mountains less common
        m = above(m, mountainStart);

		/*float l = simplex.noise2(x / lWidth, y / lWidth) * lHeight;
		l *= l / 25f;
		l = l < 8f ? 8f : l;*/
        //put in a little roll
        h += simplex.noise2(x / 100f, y / 100f) * 2f;

        // no lakes
        // now put in the channels

        float channelPressure = this.channelPressure(simplex, cell, x, y, border);

        // suppress if moutain area
        if (h+m>20) {
            channelPressure = 0;
        } else {
            if (h+m>0) {
                channelPressure = (1f -(1f-channelPressure)*(20f-m-h)/20f) ;
            }
        }
        float channelFlattening = this.channelFlattening(channelPressure, lakeWaterLevel, lakeDepressionLevel);

        // suppress towards borders
        channelFlattening = 1f - borderAdjusted(1f-channelFlattening, border, .8f, .5f);
        float result = riverized(bHeight + h + m +
            groundNoise(x, y, 2f, simplex),channelFlattening);
        return riverized(result,river);
    }

    public float channelPressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border)
    // so, rather than lakes, we have a channel network
    {
        //this code is borrowed from WorldChunkManagerRTG with vars changes
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float)x / 60.0, (float)y / 60.0, jitter);
        double pX = x + jitter.deltax() * 35f;
        double pY = y + jitter.deltay() * 35f;


        simplex.riverJitter().evaluateNoise((float)x / 10.0, y / (float)10.0, jitter);
        pX += jitter.deltax() * 3f;
        pY += jitter.deltay() * 3f;
            /*double[] simplexResults = new double[2];
    	    OpenSimplexNoise.noise(x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
            double pX = x + simplexResults[0] * 220f;
            double pY = y + simplexResults[1] * 220f;*/

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double[] results = simplexCell.river().eval(pX / 250.0, pY / 250.0);
        if (border<.5) border = .5f;
        float result = (float)((results[1]-results[0])/results[1]);
        return result;

    }

    private float lakeWaterLevel = 0.08f;// the lakeStrenght below which things should be below ater
    private float lakeDepressionLevel = 0.2f;// the lakeStrength below which land should start to be lowered

    public float channelFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > lakeDepressionLevel) return 1;
        if (pressure<lakeWaterLevel) return 0;
        return (float)((pressure-lakeWaterLevel)/(lakeDepressionLevel-lakeWaterLevel));
    }
}

