package enhancedbiomes.world.gen.layer;

import java.util.concurrent.Callable;

import enhancedbiomes.EnhancedBiomesMod;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.*;
import net.minecraftforge.event.terraingen.*;

public abstract class GenLayerEB extends GenLayer
{
    /**
     * seed from World#getWorldSeed that is used in the LCG prng
     */
    private long worldGenSeed;
    /**
     * parent GenLayer that was provided via the constructor
     */
    protected GenLayer parent;
    /**
     * final part of the LCG prng that uses the chunk X, Z coords along with the other two seeds to generate
     * pseudorandom numbers
     */
    private long chunkSeed;
    /**
     * base seed to the LCG prng provided via the constructor
     */
    protected long baseSeed;
    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        boolean flag = false;
        GenLayerEBIsland genlayerisland = new GenLayerEBIsland(1L);
        GenLayerEBFuzzyZoom genlayerfuzzyzoom = new GenLayerEBFuzzyZoom(2000L, genlayerisland);
        GenLayerEBAddIsland genlayeraddisland = new GenLayerEBAddIsland(1L, genlayerfuzzyzoom);
        GenLayerEBZoom genlayerzoom = new GenLayerEBZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerEBAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerEBAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerEBAddIsland(70L, genlayeraddisland);
        GenLayerEBRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerEBRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerEBAddSnow genlayeraddsnow = new GenLayerEBAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerEBAddIsland(3L, genlayeraddsnow);
        GenLayerEBEdge genlayeredge = new GenLayerEBEdge(2L, genlayeraddisland, GenLayerEBEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEBEdge(2L, genlayeredge, GenLayerEBEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEBEdge(3L, genlayeredge, GenLayerEBEdge.Mode.SPECIAL);
        genlayerzoom = new GenLayerEBZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerEBZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerEBAddIsland(4L, genlayerzoom);
        GenLayerEBAddMushroomIsland genlayeraddmushroomisland = new GenLayerEBAddMushroomIsland(5L, genlayeraddisland);
        GenLayerEBDeepOcean genlayerdeepocean = new GenLayerEBDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer3 = GenLayerEBZoom.magnify(1000L, genlayerdeepocean, 0);
        byte b0 = (byte) EnhancedBiomesMod.biomeSize;

        GenLayer genlayer = GenLayerEBZoom.magnify(1000L, genlayer3, 0);
        GenLayerEBRiverInit genlayerriverinit = new GenLayerEBRiverInit(100L, genlayer);
        Object object = par2WorldType.getBiomeLayer(par0, genlayer3);

        GenLayer genlayer1 = GenLayerEBZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayerEBHills genlayerhills = new GenLayerEBHills(1000L, (GenLayer)object, genlayer1);
        genlayer = GenLayerEBZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerEBZoom.magnify(1000L, genlayer, b0);
        GenLayerEBRiver genlayerriver = new GenLayerEBRiver(1L, genlayer);
        GenLayerEBSmooth genlayersmooth = new GenLayerEBSmooth(1000L, genlayerriver);
        object = new GenLayerEBRareBiome(1001L, genlayerhills);

        for (int j = 0; j < b0; ++j)
        {
            object = new GenLayerEBZoom((long)(1000 + j), (GenLayer)object);

            if (j == 0)
            {
                object = new GenLayerEBAddIsland(3L, (GenLayer)object);
            }

            if (j == 1)
            {
                object = new GenLayerEBShore(1000L, (GenLayer)object);
            	object = new GenLayerAlpineEdge(1000L, (GenLayer)object);
            	object = new GenLayerArchipelagoEdge(1000L, (GenLayer)object);
            	object = new GenLayerEphemeralEdge(1000L, (GenLayer)object);
            	object = new GenLayerMountainsEdge(1000L, (GenLayer)object);
            	object = new GenLayerScree(1000L, (GenLayer)object);
            }
        }

        GenLayerEBSmooth genlayersmooth1 = new GenLayerEBSmooth(1000L, (GenLayer)object);
        GenLayerEBRiverMix genlayerrivermix = new GenLayerEBRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerEBVoronoiZoom genlayervoronoizoom = new GenLayerEBVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(par0);
        genlayervoronoizoom.initWorldGenSeed(par0);
        return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }

    public GenLayerEB(long par1)
    {
    	super(par1);
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }
}