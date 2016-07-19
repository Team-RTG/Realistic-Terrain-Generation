package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPBayou extends RTGBiomeBOP {

    public RTGBiomeBOPBayou() {
        super(BOPBiomes.bayou.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlains(x, y, rtgWorld.simplex, river, 80f, 1f, 40f, 20f, 62f);
            }

            /*
            @Override
            public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float border)
            // so, rather than lakes, we have a bayou network
            {
                //this code is borrowed from WorldChunkManagerRTG with vars changes
                SimplexOctave.Disk jitter = new SimplexOctave.Disk();
                simplex.riverJitter().evaluateNoise((float)x / 40.0, (float)y / 40.0, jitter);
                double pX = x + jitter.deltax() * 35f;
                double pY = y + jitter.deltay() * 35f;
            //double[] simplexResults = new double[2];
    	    //OpenSimplexNoise.noise(x / 240.0, y / 240.0, riverOpenSimplexNoiseInstances, simplexResults);
            //double pX = x + simplexResults[0] * 220f;
            //double pY = y + simplexResults[1] * 220f;

                //New cellular noise.
                //TODO move the initialization of the results in a way that's more efficient but still thread safe.
                double[] results = simplexCell.river().eval(pX / 150.0, pY / 150.0);
                if (border<.5) border = .5f;
                float result = (float)((results[1]-results[0]));
                if (result >1.01) throw new RuntimeException("" + results[0]+ " , "+results[1]);
                if (result<-.01) throw new RuntimeException("" + results[0]+ " , "+results[1]);
                return result;

            }

            private double lakeWaterLevel = 0.04;// the lakeStrength below which things should be below water
            private double lakeDepressionLevel = 0.3;// the lakeStrength below which land should start to be lowered

            @Override
            public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
                // this number indicates a multiplier to height
                if (pressure > lakeDepressionLevel) return 1;
                if (pressure<lakeWaterLevel) return 0;
                return (float)((pressure-lakeWaterLevel)/(lakeDepressionLevel-lakeWaterLevel));
            }
            */
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.STONE_OR_COBBLE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
