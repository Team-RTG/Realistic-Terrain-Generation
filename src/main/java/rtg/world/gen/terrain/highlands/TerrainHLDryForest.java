package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLDryForest extends TerrainBase

{
    public static float hillStrength = 60;
    public static float hillWidth = 100;
    public static float hillFloor = 20;

    public TerrainHLDryForest()
    {
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float b = (12f + (simplex.noise2(x / 300f, y / 300f) * 6f));
        float h = cell.noise(x / 200D, y / 200D, 1D) * b * river;
        h *= h * 1.5f;
        h = h > 155f ? 155f : h;

        // this creates "hills" which rise abruptly to relatively rounded tops
        float hill = (simplex.noise2(x / hillWidth, y / hillWidth)*hillStrength)*river;
        hill = above(hill,hillFloor);

        if(h > 2f)
        {
            float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
            h += simplex.noise2(x / 30f, y / 30f) * d;
            h += simplex.noise2(x / 50f, y / 50f) * d * 0.5f;

            if(h > 35f)
            {
                float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
                h += cell.noise(x / 25D, y / 25D, 1D) * d2;
            }
        }

        h += simplex.noise2(x / 18f, y / 18f) * 3;
        h += simplex.noise2(x / 8f, y / 8f) * 2;

        if (river<1)  {
            float result = 45f + h + (b * 2) + hill;
            return 62f+(result-62f)*river;
        }
        return 45f + h + (b * 2) + hill;
    }
}
