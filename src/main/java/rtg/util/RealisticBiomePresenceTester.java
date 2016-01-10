
package rtg.util;

import org.apache.logging.log4j.Level;

import rtg.world.biome.realistic.RealisticBiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author WhichOnesPink
 */
public class RealisticBiomePresenceTester {

    public static void doBiomeCheck()
    {
        BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
        
        for (int i = 0; i < 256; i++)
        {
            if (b[i] != null)
            {
                BiomeGenBase biome = b[i];
                int biomeId = b[i].biomeID;
                String biomeName = b[i].biomeName;
                String biomeClass = b[i].getBiomeClass().getName();
                
                switch (biomeId) {
                
                    case 8:
                    case 9:
                        // Do nothing.
                        break;
                    
                    default:
                            
                        try {
                            RealisticBiomeBase rBiome = RealisticBiomeBase.getBiome(biomeId);
                            String rBiomeName = rBiome.biomeConfig.getRealisticBiomeName();
                        }
                        catch (Exception e) {
                            FMLLog.log(Level.WARN, "WARNING! RTG could not find a realistic version of %s (%d) from %s", biomeName, biomeId, biomeClass);
                        }
                        
                        break;
                }
            }
        }
    }
}
