package rtg.world.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import rtg.api.RTGAPI;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.RTGChunkGenSettings;
import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomePatcher;

public class BiomeProviderRTG extends BiomeProvider implements IBiomeProviderRTG
{
    private final RTGWorld rtgWorld;
    private final RTGChunkGenSettings generatorSettings;
// TODO: [1.12] Keep these fields for when the custom GenLayer classes are written.
//  private GenLayer genBiomes;
//  private GenLayer biomeIndexLayer;
    private float[] borderNoise;
    private RealisticBiomePatcher biomePatcher;
    private double riverValleyLevel;
    private double  riverSeparation;
    private double  largeBendSize;
    private double  smallBendSize;

    public BiomeProviderRTG(RTGWorld rtgWorld) {

        super(rtgWorld.world().getWorldInfo());

        this.rtgWorld = rtgWorld; //new RTGWorld(world)
        this.generatorSettings = rtgWorld.getGeneratorSettings();

        this.borderNoise = new float[256];
        this.biomePatcher = new RealisticBiomePatcher();// TODO: [1.12] To be removed.

// TODO: [1.12] These 4 fields are a part of #getRiverStrength
        this.riverSeparation  = 975d / generatorSettings.riverFrequency;
        this.riverValleyLevel = 140.0d / 450.0d * generatorSettings.riverSize * generatorSettings.riverFrequency;
        this.largeBendSize    = 140d * generatorSettings.riverBendMult;
        this.smallBendSize    = 30d * generatorSettings.riverBendMult;

//      GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, ChunkGeneratorSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build());
//      agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
//      this.genBiomes = agenlayer[0]; //maybe this will be needed
//      this.biomeIndexLayer = agenlayer[1];
    }

    @Override
// TODO: [1.12] To be removed. This is an anitquated method that does the same thing as BiomeProvider#getBiomes but returns a int[] instead of Biome[].
    public int[] getBiomesGens(int worldX, int worldZ, int areaX, int areaZ) {

        int[] d = new int[areaX * areaZ];

        for (int i = 0; i < areaX; i++) {
            for (int j = 0; j < areaZ; j++) {
                d[i * areaX + j] = Biome.getIdForBiome(getBiomeGenAt(worldX + i, worldZ + j));
            }
        }
        return d;
    }

    @Override
// TODO: [1.12] To be moved to ChunkGeneratorRTG
    public float getRiverStrength(final int worldX, final int worldZ) {

        double pX = worldX;
        double pZ = worldZ;
        ISimplexData2D jitterData = SimplexData2D.newDisk();

        //New river curve function. No longer creates worldwide curve correlations along cardinal axes.
        this.rtgWorld.simplexInstance(1).multiEval2D((float) worldX / 240.0, (float) worldZ / 240.0, jitterData);
        pX += jitterData.getDeltaX() * largeBendSize;
        pZ += jitterData.getDeltaY() * largeBendSize;

        this.rtgWorld.simplexInstance(2).multiEval2D((float) worldX / 80.0, (float) worldZ / 80.0, jitterData);
        pX += jitterData.getDeltaX() * smallBendSize;
        pZ += jitterData.getDeltaY() * smallBendSize;

        pX /= riverSeparation;
        pZ /= riverSeparation;

        //New cellular noise.
        //TODO move the initialization of the results in a way that's more efficient but still thread safe.
        double riverFactor = rtgWorld.cellularInstance(0).eval2D(pX, pZ).interiorValue();

        // the output is a curved function of relative distance from the center, so adjust to make it flatter
        riverFactor = Terrain.bayesianAdjustment((float) riverFactor, 0.5f);
        if (riverFactor > riverValleyLevel) return 0; // no river effect
        return (float)(riverFactor / riverValleyLevel - 1d);
    }

    @Override
// TODO: [1.12] To be removed. Unneeded proxy of BiomeProvider#getBiome
    public Biome getBiomeGenAt(int worldX, int worldZ) {
        return this.getBiome(new BlockPos(worldX, 0, worldZ));
    }

    @Override
// TODO: [1.12] To be removed. Unneeded proxy of RTGAPI.getRTGBiome(BiomeProvider.getBiome(BlockPos))
    public IRealisticBiome getBiomeDataAt(int worldX, int worldZ) {

        IRealisticBiome output = RTGAPI.getRTGBiome(getBiome(new BlockPos(worldX, 0, worldZ)));
        if (output == null) output = biomePatcher.getPatchedRealisticBiome("No biome " + worldX + " " + worldZ);
        return output;
    }

    @Override
// TODO: [1.12] To be moved. The only usage is in the volcano generator.
    public boolean isBorderlessAt(int chunkX, int chunkZ) {

        int bx, bz;
        for (bx = -2; bx <= 2; bx++) {
            for (bz = -2; bz <= 2; bz++) {
// TODO: [1.12] This is just ridiculous. Replace the call to #getBiomeDataAt with #getBiome
                borderNoise[Biome.getIdForBiome(getBiomeDataAt(chunkX + bx * 16, chunkZ + bz * 16).baseBiome())] += 0.04f;
            }
        }

        bz = 0;
        for (bx = 0; bx < 256; bx++) {
            if (borderNoise[bx] > 0.98f) bz = 1;
            borderNoise[bx] = 0;
        }
        return bz == 1;
    }
}
