package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.util.noise.SimplexOctave;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBayou;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBayou;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.bayou.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;
    private double lakeWaterLevel = 0.04;// the lakeStrenght below which things should be below ater
    private double lakeDepressionLevel = 0.3;// the lakeStrength below which land should start to be lowered

    public RealisticBiomeBOPBayou(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPBayou(),
                new SurfaceBOPBayou(config, topBlock, fillerBlock)
        );
        // turn off those dang ponds
        /*BiomeGenBayou bayou = (BiomeGenBayou)BOPBiomes.bayou.get();
        BiomeDecorator decor = BOPBiomes.bayou.theBiomeDecorator;
        if (1>0) throw new RuntimeException(""+bayou.toString()+ " " + decor.toString());
        BOPBiomeDecorator decorator = (BOPBiomeDecorator)BOPBiomes.bayou.theBiomeDecorator;
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
        simplex.riverJitter().evaluateNoise(x / 40.0, y / 40.0, jitter);
        double pX = x + jitter.deltax() * 35f;
        double pY = y + jitter.deltay() * 35f;
            /*double[] simplexResults = new double[2];
            OpenSimplexNoise.noise(x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
            double pX = x + simplexResults[0] * 220f;
            double pY = y + simplexResults[1] * 220f;*/

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double[] results = simplexCell.river().eval(pX / 150.0, pY / 150.0);
        if (border < .5) border = .5f;
        float result = (float) ((results[1] - results[0]));
        if (result > 1.01) throw new RuntimeException("" + results[0] + " , " + results[1]);
        if (result < -.01) throw new RuntimeException("" + results[0] + " , " + results[1]);
        ;
        return result;

    }

    @Override
    public double lakeFlattening(double pressure, double bottomLevel, double topLevel) {
        // this number indicates a multiplier to height
        if (pressure > lakeDepressionLevel) return 1;
        if (pressure < lakeWaterLevel) return 0;
        return (pressure - lakeWaterLevel) / (lakeDepressionLevel - lakeWaterLevel);
    }
}
