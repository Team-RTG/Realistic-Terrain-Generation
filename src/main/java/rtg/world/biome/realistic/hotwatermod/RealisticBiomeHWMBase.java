package rtg.world.biome.realistic.hotwatermod;

import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.hotwatermod.config.BiomeConfigHWM;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeHWMBase extends RealisticBiomeBase
{
    
    public static RealisticBiomeBase hwmHotSprings;
    
    public RealisticBiomeHWMBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("hot_water"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }
                    
                    BiomeGenBase bcBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName.equals("Hot Springs") && biomeClass.equals("sorazodia.hotwater.worldGen.BiomeHotSpring"))
                    {
                        hwmHotSprings = new RealisticBiomeHWMHotSprings(bcBiome, BiomeConfigHWM.biomeConfigHWMHotSprings);
                    }
                }
            }
        }
    }
}
