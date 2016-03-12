package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLFlyingMountains extends TerrainBase
{
	private float width;
	private float strength;
	private float lakeDepth;
	private float lakeWidth;
	private float terrainHeight;
    private float cragWidth;

	/*
	 * width = 230f
	 * strength = 120f
	 * lake = 50f;
	 *
	 * 230f, 120f, 50f
	 */

	public TerrainHLFlyingMountains(float mountainWidth, float mountainStrength, float depthLake, float cragWidth)
	{
		this(mountainWidth, mountainStrength, depthLake, 260f, 63f,cragWidth);

	}

	public TerrainHLFlyingMountains(float mountainWidth, float mountainStrength, float depthLake, float widthLake, float height, float cragWidth)
	{
        super(height);
		width = mountainWidth;
		strength = mountainStrength*1.3f;
		lakeDepth = depthLake;
		lakeWidth = widthLake;
		terrainHeight = height;
        this.cragWidth = cragWidth;
	}

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        float firstJump = 0.2f;
        float secondJump = 0.4f;
        float firstScatter = 0.2f;
        float secondScatter = 0.35f;
        float adjust =0;
        float firstLevel = simplex.noise2(x / width, y / width)*5+10+simplex.noise2(x / 20f, y / 20f);
        firstLevel *= river;
        float secondLevel = simplex.octave(1).noise2(x / width, y / width)*15+50
                +simplex.noise2(x / 20f, y / 20f)*3f;
        float thirdLevel = simplex.octave(2).noise2(x / width, y / width)*25+80
                +simplex.noise2(x / 20f, y / 20f)*5f;
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
