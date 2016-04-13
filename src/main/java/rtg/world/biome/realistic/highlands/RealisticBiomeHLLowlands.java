package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLLowlands;
import rtg.world.gen.terrain.highlands.TerrainHLLowlands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;

public class RealisticBiomeHLLowlands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.lowlands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLLowlands(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLLowlands(),
            new SurfaceHLLowlands(config, topBlock, fillerBlock));
    }

    // additional lakes
    private float largeBendSize = 25;
    private float mediumBendSize = 8;
    private float lakeInterval = 300;

    @Override
    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        float result = super.lakePressure(simplex, simplexCell, x, y, border);
        return Math.min(result, lowlandsLakePressure(simplex, simplexCell, x, y, border));
    }

    public float lowlandsLakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        //if (noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float)x / 40.0, (float)y / 40.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;
        simplex.mountain().evaluateNoise((float)x / 13.0, (float)y / 13.0, jitter);
        pX += jitter.deltax() * mediumBendSize;
        pY += jitter.deltay() * mediumBendSize;
        double [] lakeResults = simplexCell.river().eval((float)pX/ lakeInterval, (float)pY/ lakeInterval);
        float results = 1f-(float)((lakeResults[1]-lakeResults[0])/lakeResults[1]);
        if (results >1.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        if (results<-.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }
}
