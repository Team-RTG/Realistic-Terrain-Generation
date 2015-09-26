package rtg.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerMutationRTG extends GenLayer
{
    private GenLayer field_151628_d;

    public GenLayerMutationRTG(long seed, GenLayer parent, GenLayer p_i45479_4_)
    {
        super(seed);
        this.parent = parent;
        this.field_151628_d = p_i45479_4_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    @Override
    public int[] getInts(int x, int z, int length, int width)
    {
        int[] inputBiomeIds = this.parent.getInts(x - 1, z - 1, length + 2, width + 2);
        int[] aint1 = this.field_151628_d.getInts(x - 1, z - 1, length + 2, width + 2);
        int[] outputBiomeIds = IntCache.getIntCache(length * width);

        for (int zItr = 0; zItr < width; ++zItr)
        {
            for (int xItr = 0; xItr < length; ++xItr)
            {
                this.initChunkSeed((long)(xItr + x), (long)(zItr + z));
                int currentBiomeId = inputBiomeIds[xItr + 1 + (zItr + 1) * (length + 2)];
                int l1 = aint1[xItr + 1 + (zItr + 1) * (length + 2)];
                boolean hills = (l1 - 2) % 29 == 0;

                if (currentBiomeId != 0 && l1 >= 2 && (l1 - 2) % 29 == 1 && currentBiomeId < 128)
                {
                    BiomeGenBase mutatedBiome = BiomeGenBase.getBiome(currentBiomeId + 128);
                	
                    if (mutatedBiome != null && mutatedBiome instanceof BiomeGenMutated)
                    {
                        outputBiomeIds[xItr + zItr * length] = currentBiomeId + 128;
                    }
                    else
                    {
                        outputBiomeIds[xItr + zItr * length] = currentBiomeId;
                    }
                }
                else if (this.nextInt(3) != 0 && !hills)
                {
                    outputBiomeIds[xItr + zItr * length] = currentBiomeId;
                }
                else
                {
                    int mutatedBiomeId = currentBiomeId;
                    int j2;

                    if (currentBiomeId == BiomeGenBase.desert.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.desertHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.forest.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.forestHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.birchForest.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.birchForestHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.roofedForest.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.plains.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.taiga.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.taigaHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.megaTaiga.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.megaTaigaHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.coldTaiga.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.coldTaigaHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.plains.biomeID)
                    {
                        if (this.nextInt(3) == 0)
                        {
                            mutatedBiomeId = BiomeGenBase.forestHills.biomeID;
                        }
                        else
                        {
                            mutatedBiomeId = BiomeGenBase.forest.biomeID;
                        }
                    }
                    else if (currentBiomeId == BiomeGenBase.icePlains.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.iceMountains.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.jungle.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.jungleHills.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.ocean.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.deepOcean.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.extremeHills.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.extremeHillsPlus.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.savanna.biomeID)
                    {
                        mutatedBiomeId = BiomeGenBase.savannaPlateau.biomeID;
                    }
                    else if (compareBiomesById(currentBiomeId, BiomeGenBase.mesaPlateau_F.biomeID))
                    {
                        mutatedBiomeId = BiomeGenBase.mesa.biomeID;
                    }
                    else if (currentBiomeId == BiomeGenBase.deepOcean.biomeID && this.nextInt(3) == 0)
                    {
                        j2 = this.nextInt(2);

                        if (j2 == 0)
                        {
                            mutatedBiomeId = BiomeGenBase.plains.biomeID;
                        }
                        else
                        {
                            mutatedBiomeId = BiomeGenBase.forest.biomeID;
                        }
                    }

                    if (hills && mutatedBiomeId != currentBiomeId)
                    {
                        BiomeGenBase mutatedBiome = BiomeGenBase.getBiome(mutatedBiomeId + 128);
                        
                        if (mutatedBiome != null && mutatedBiome instanceof BiomeGenMutated)
                        {
                            mutatedBiomeId += 128;
                        }
                        else
                        {
                            mutatedBiomeId = currentBiomeId;
                        }
                    }

                    if (mutatedBiomeId == currentBiomeId)
                    {
                        outputBiomeIds[xItr + zItr * length] = currentBiomeId;
                    }
                    else
                    {
                        j2 = inputBiomeIds[xItr + 1 + (zItr + 1 - 1) * (length + 2)];
                        int k2 = inputBiomeIds[xItr + 1 + 1 + (zItr + 1) * (length + 2)];
                        int l2 = inputBiomeIds[xItr + 1 - 1 + (zItr + 1) * (length + 2)];
                        int i3 = inputBiomeIds[xItr + 1 + (zItr + 1 + 1) * (length + 2)];
                        int j3 = 0;

                        if (compareBiomesById(j2, currentBiomeId))
                        {
                            ++j3;
                        }

                        if (compareBiomesById(k2, currentBiomeId))
                        {
                            ++j3;
                        }

                        if (compareBiomesById(l2, currentBiomeId))
                        {
                            ++j3;
                        }

                        if (compareBiomesById(i3, currentBiomeId))
                        {
                            ++j3;
                        }

                        if (j3 >= 3)
                        {
                            outputBiomeIds[xItr + zItr * length] = mutatedBiomeId;
                        }
                        else
                        {
                            outputBiomeIds[xItr + zItr * length] = currentBiomeId;
                        }
                    }
                }
            }
        }

        return outputBiomeIds;
    }
}
