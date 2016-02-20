
package rtg.util;

import code.elix_x.coremods.antiidconflict.api.AICChangesWrapper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Loader;

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
        return Loader.isModLoaded("AIC") && AICChangesWrapper.isAICLoaded() && AICChangesWrapper.isExtendingBiomeIdsLimit();
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
