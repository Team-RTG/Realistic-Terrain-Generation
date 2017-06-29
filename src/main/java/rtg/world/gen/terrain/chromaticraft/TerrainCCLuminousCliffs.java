package rtg.world.gen.terrain.chromaticraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainCCLuminousCliffs extends TerrainBase
{
    private float width;
    private float strength;
    private float terrainHeight;
    private float cragWidth;

    private int wavelength = 20;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
    private double amplitude = 5;


    public TerrainCCLuminousCliffs(float mountainWidth, float mountainStrength,  float height, float cragWidth)
    {
        super(height);
        width = mountainWidth;
        strength = mountainStrength*1.3f;
        terrainHeight = height;
        this.cragWidth = cragWidth;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {

        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        int pX = (int)Math.round(x + jitter.deltax() * amplitude);
        int pY = (int)Math.round(y + jitter.deltay() * amplitude);

        x = pX;
        y = pY;

        float firstJump = 0.3f;
        float secondJump = 0.5f;
        float firstScatter = 0.3f;
        float secondScatter = 0.45f;
        float adjust =0;
        float firstLevel = simplex.noise2(x / width, y / width)*5+10+simplex.noise2(x / 20f, y / 20f);
        firstLevel *= river;

        float secondAdded = simplex.octave(1).noise2(x / width, y / width)*10+40
            +simplex.noise2(x / 20f, y / 20f)*2f;
        float secondLevel = firstLevel + borderAdjusted(secondAdded, border, .8f, .7f);
        /*float secondLevel = simplex.octave(1).noise2(x / width, y / width)*15+50
                +simplex.noise2(x / 20f, y / 20f)*3f;*/

        float thirdAdded = simplex.octave(2).noise2(x / width, y / width)*15+30
            +simplex.noise2(x / 20f, y / 20f)*2f;
        /*float thirdLevel = simplex.octave(2).noise2(x / width, y / width)*25+80
                +simplex.noise2(x / 20f, y / 20f)*5f;*/

        float thirdLevel = secondLevel + borderAdjusted(thirdAdded, border, .95f, .88f);

        float scatter = Math.abs((float)simplex.octave(1).noise(x/cragWidth, y/cragWidth,1f));
        if (river < firstJump||scatter<firstScatter) {
            adjust = firstLevel;
        } else {
            if (river<secondJump||scatter<secondScatter) {
                if (scatter >firstScatter+0.1) {
                    adjust = secondLevel;
                } else {
                    float lift = (scatter-firstScatter)*10f;// 0 to 1
                    lift = (0.5f-lift)*2f;// -.5 to +.5
                    lift = unsignedPower(lift, 0.5f);
                    lift = -lift/2f+0.5f;
                    adjust = secondLevel*lift + firstLevel*(1.0f-lift);
                }
            } else {
                if (scatter >secondScatter+0.1f) {
                    adjust = thirdLevel;
                } else {
                    float lift = (scatter-secondScatter)*10f;
                    lift = lift*lift;
                    adjust = thirdLevel*lift + secondLevel*(1.0f-lift);
                }
            }
        }
        return terrainHeight + adjust;
    }

}
