package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.atg.config.BiomeConfigATG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase atgGravelBeach;
    public static RealisticBiomeBase atgRockySteppe;
    public static RealisticBiomeBase atgShrubland;
    public static RealisticBiomeBase atgSnowyGravelBeach;
    public static RealisticBiomeBase atgTropicalShrubland;
    public static RealisticBiomeBase atgTundra;
    public static RealisticBiomeBase atgVolcano;
    public static RealisticBiomeBase atgWoodland;
    
	public RealisticBiomeATGBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(config, b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ATG"))
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for (int i = 0; i < 256; i++)
			{
				if (b[i] != null)
				{
					BiomeGenBase atgBiome = b[i];
					String biomeName = b[i].biomeName;
					String biomeClass = b[i].getBiomeClass().getName();
					
					if (biomeName == "Gravel Beach" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenGravelBeach")
					{
					    atgGravelBeach = new RealisticBiomeATGGravelBeach(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
					}
                    else if (biomeName == "Rocky Steppe" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenSteppe")
                    {
                        atgRockySteppe = new RealisticBiomeATGRockySteppe(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
                    else if (biomeName == "Shrubland" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenShrubland")
                    {
                        atgShrubland = new RealisticBiomeATGShrubland(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
                    else if (biomeName == "Snowy Gravel Beach" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenGravelBeach")
                    {
                        atgSnowyGravelBeach = new RealisticBiomeATGSnowyGravelBeach(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
                    else if (biomeName == "Tropical Shrubland" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenTropicalShrubland")
                    {
                        atgTropicalShrubland = new RealisticBiomeATGTropicalShrubland(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
                    else if (biomeName == "Tundra" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenTundra")
                    {
                        atgTundra = new RealisticBiomeATGTundra(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
                    else if (biomeName == "Volcano" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenVolcano")
                    {
                        atgVolcano = new RealisticBiomeATGVolcano(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
                    else if (biomeName == "Woodland" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenWoodland")
                    {
                        atgWoodland = new RealisticBiomeATGWoodland(atgBiome, BiomeConfigATG.biomeConfigATGGravelBeach);
                    }
				}
			}
		}
	}
}
