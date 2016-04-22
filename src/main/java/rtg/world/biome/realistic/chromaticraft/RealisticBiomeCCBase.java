package rtg.world.biome.realistic.chromaticraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.chromaticraft.config.BiomeConfigCC;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeCCBase extends RealisticBiomeBase
{	
    public static RealisticBiomeBase ccEnderForest;
    public static RealisticBiomeBase ccRainbowForest;
    
	public RealisticBiomeCCBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(config, b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ChromatiCraft"))
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for(int i = 0; i < 256; i++)
			{
				if(b[i] != null)
				{
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }
                    
					BiomeGenBase ccBiome = b[i];
					String biomeName = ccBiome.biomeName;
					String biomeClass = ccBiome.getBiomeClass().getName();
					
					if (biomeName == "Ender Forest" && biomeClass == "Reika.ChromatiCraft.World.BiomeEnderForest")
					{
					    ccEnderForest = new RealisticBiomeCCEnderForest(ccBiome, BiomeConfigCC.biomeConfigCCEnderForest);
					}
					else if (biomeName == "Rainbow Forest" && biomeClass == "Reika.ChromatiCraft.World.BiomeRainbowForest")
					{
					    ccRainbowForest = new RealisticBiomeCCRainbowForest(ccBiome, BiomeConfigCC.biomeConfigCCRainbowForest);
					}
				}
			}
		}
	}
}
