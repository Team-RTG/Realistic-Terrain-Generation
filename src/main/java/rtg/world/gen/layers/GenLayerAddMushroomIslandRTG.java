package rtg.world.gen.layers;

import rtg.world.biome.realistic.vanilla.*;
import rtg.world.gen.layers.*;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAddMushroomIslandRTG extends GenLayerRTG
{
    private static final String __OBFID = "CL_00000552";

    public GenLayerAddMushroomIslandRTG(long p_i2120_1_, GenLayer p_i2120_3_)
    {
        super(p_i2120_1_);
        this.parent = p_i2120_3_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int i1 = p_75904_1_ - 1;
        int j1 = p_75904_2_ - 1;
        int k1 = p_75904_3_ + 2;
        int l1 = p_75904_4_ + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

        for (int i2 = 0; i2 < p_75904_4_; ++i2)
        {
            for (int j2 = 0; j2 < p_75904_3_; ++j2)
            {
                int k2 = aint[j2 + 0 + (i2 + 0) * k1];
                int l2 = aint[j2 + 2 + (i2 + 0) * k1];
                int i3 = aint[j2 + 0 + (i2 + 2) * k1];
                int j3 = aint[j2 + 2 + (i2 + 2) * k1];
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                this.initChunkSeed((long)(j2 + p_75904_1_), (long)(i2 + p_75904_2_));

                if (k3 == 0 && k2 == 0 && l2 == 0 && i3 == 0 && j3 == 0 && this.nextInt(100) == 0)
                {
                    aint1[j2 + i2 * p_75904_3_] = RealisticBiomeVanillaBase.vanillaMushroomIsland.biomeID;
                }
                else
                {
                    aint1[j2 + i2 * p_75904_3_] = k3;
                }
            }
        }

        return aint1;
    }
}