
package rtg.util;

import code.elix_x.coremods.antiidconflict.api.AICChangesWrapper;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 *
 * @author WhichOnesPink
 */
public class AICWrapper
{

    public AICWrapper()
    {
        
    }
    
    public boolean isAICExtendingBiomeIdsLimit()
    {
        if (Loader.isModLoaded("AIC") && AICChangesWrapper.isAICLoaded() && AICChangesWrapper.isExtendingBiomeIdsLimit()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void setBiomeArray(Chunk chunk, BiomeGenBase[] baseBiomesList, int[] xyinverted)
    {
        int[] biomes = AICChangesWrapper.getBiomeArray(chunk);
        for (int k = 0; k < biomes.length; ++k)
        {
            // biomes are y-first and terrain x-first
            biomes[k] = baseBiomesList[xyinverted[k]].biomeID;
        }
        AICChangesWrapper.setBiomeArray(chunk, biomes);
    }
}
