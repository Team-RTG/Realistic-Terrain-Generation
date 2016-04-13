package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBayou;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBayou;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.bayou;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBayou(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBayou(),
			new SurfaceBOPBayou(config, topBlock, fillerBlock)
		);
        // turn off those dang ponds
        /*BiomeGenBayou bayou = (BiomeGenBayou)BOPCBiomes.bayou;
        BiomeDecorator decor = BOPCBiomes.bayou.theBiomeDecorator;
        if (1>0) throw new RuntimeException(""+bayou.toString()+ " " + decor.toString());
        BOPBiomeDecorator decorator = (BOPBiomeDecorator)BOPCBiomes.bayou.theBiomeDecorator;
        decorator.generateLakes = false;
        ArrayList<String> names = decorator.bopFeatures.getFeatureNames();
        String result = "";
        for (String name: names) {
            result += name + " ";
        }
        throw new RuntimeException(result);*/
	}

    @Override
    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border)
            // so, rather than lakes, we have a bayou network
    {
    	//this code is borrowed from WorldChunkManagerRTG with vars changes
            SimplexOctave.Disk jitter = new SimplexOctave.Disk();
            simplex.riverJitter().evaluateNoise((float)x / 40.0, (float)y / 40.0, jitter);
            double pX = x + jitter.deltax() * 35f;
            double pY = y + jitter.deltay() * 35f;
            /*double[] simplexResults = new double[2];
    	    OpenSimplexNoise.noise(x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
            double pX = x + simplexResults[0] * 220f;
            double pY = y + simplexResults[1] * 220f;*/

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double[] results = simplexCell.river().eval(pX / 150.0, pY / 150.0);
        if (border<.5) border = .5f;
        float result = (float)((results[1]-results[0]));
        if (result >1.01) throw new RuntimeException("" + results[0]+ " , "+results[1]);
        if (result<-.01) throw new RuntimeException("" + results[0]+ " , "+results[1]);
        return result;

    }

    private double lakeWaterLevel = 0.04;// the lakeStrenght below which things should be below ater
    private double lakeDepressionLevel = 0.3;// the lakeStrength below which land should start to be lowered

    @Override
    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > lakeDepressionLevel) return 1;
        if (pressure<lakeWaterLevel) return 0;
        return (float)((pressure-lakeWaterLevel)/(lakeDepressionLevel-lakeWaterLevel));
    }
}
