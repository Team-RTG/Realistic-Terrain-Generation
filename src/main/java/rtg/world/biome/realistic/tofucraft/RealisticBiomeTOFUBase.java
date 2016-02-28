package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFU;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUBase extends RealisticBiomeBase
{

    public static RealisticBiomeBase tofuLeekPlains;
    public static RealisticBiomeBase tofuTofuBuildings;
    public static RealisticBiomeBase tofuTofuExtremeHills;
    public static RealisticBiomeBase tofuTofuExtremeHillsEdge;
    public static RealisticBiomeBase tofuTofuForest;
    public static RealisticBiomeBase tofuTofuForestHills;
    public static RealisticBiomeBase tofuTofuPlainHills;
    public static RealisticBiomeBase tofuTofuPlains;
    public static RealisticBiomeBase tofuTofuRiver;
    
	public RealisticBiomeTOFUBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(config, b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("TofuCraft"))
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
                    
					BiomeGenBase tofuBiome = b[i];
					String biomeName = b[i].biomeName;
					String biomeClass = b[i].getBiomeClass().getName();
					
					if (biomeName == "LeekPlains" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenLeekPlains")
					{
					    tofuLeekPlains = new RealisticBiomeTOFULeekPlains(tofuBiome, BiomeConfigTOFU.biomeConfigTOFULeekPlains);
					}
                    else if (biomeName == "TofuBuildings" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuBuildings")
                    {
                        tofuTofuBuildings = new RealisticBiomeTOFUTofuBuildings(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuBuildings);
                    }
                    else if (biomeName == "TofuExtremeHills" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuHills")
                    {
                        tofuTofuExtremeHills = new RealisticBiomeTOFUTofuExtremeHills(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuExtremeHills);
                    }
                    else if (biomeName == "TofuExtremeHillsEdge" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuHills")
                    {
                        tofuTofuExtremeHillsEdge = new RealisticBiomeTOFUTofuExtremeHillsEdge(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuExtremeHillsEdge);
                    }
                    else if (biomeName == "TofuForest" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuForest")
                    {
                        tofuTofuForest = new RealisticBiomeTOFUTofuForest(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuForest);
                    }
                    else if (biomeName == "TofuForestHills" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuForest")
                    {
                        tofuTofuForestHills = new RealisticBiomeTOFUTofuForestHills(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuForestHills);
                    }
                    else if (biomeName == "TofuPlainHills" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuPlains")
                    {
                        tofuTofuPlainHills = new RealisticBiomeTOFUTofuPlainHills(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuPlainHills);
                    }
                    else if (biomeName == "TofuPlains" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuPlains")
                    {
                        tofuTofuPlains = new RealisticBiomeTOFUTofuPlains(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuPlains);
                    }
                    else if (biomeName == "TofuRiver" && biomeClass == "tsuteto.tofu.world.biome.BiomeGenTofuRiver")
                    {
                        tofuTofuRiver = new RealisticBiomeTOFUTofuRiver(tofuBiome, BiomeConfigTOFU.biomeConfigTOFUTofuRiver);
                    }
				}
			}
		}
	}
}
