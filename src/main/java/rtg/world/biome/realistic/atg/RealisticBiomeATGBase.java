package rtg.world.biome.realistic.atg;

import org.apache.logging.log4j.Level;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase atgGravelBeach;
    public static RealisticBiomeBase atgSnowyGravelBeach;
    public static RealisticBiomeBase atgShrubland;
    public static RealisticBiomeBase atgRockySteppe;
    public static RealisticBiomeBase atgTropicalShrubland;
    public static RealisticBiomeBase atgTundra;
    public static RealisticBiomeBase atgVolcano;
    public static RealisticBiomeBase atgWoodland;
    
	public RealisticBiomeATGBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ATG") && ConfigATG.generateATGBiomes)
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
						if (ConfigATG.generateATGGravelBeach) {
						    atgGravelBeach = new RealisticBiomeATGGravelBeach(atgBiome);
							BiomeBase.addBiome(atgGravelBeach);
						}
					}
                    else if (biomeName == "Snowy Gravel Beach" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenGravelBeach")
                    {
                        if (ConfigATG.generateATGSnowyGravelBeach) {
                            atgSnowyGravelBeach = new RealisticBiomeATGSnowyGravelBeach(atgBiome);
                            BiomeBase.addBiome(atgSnowyGravelBeach);
                        }
                    }
                    else if (biomeName == "Shrubland" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenShrubland")
                    {
                        if (ConfigATG.generateATGShrubland) {
                            atgShrubland = new RealisticBiomeATGShrubland(atgBiome);
                            BiomeBase.addBiome(atgShrubland);
                        }
                    }
                    else if (biomeName == "Rocky Steppe" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenSteppe")
                    {
                        if (ConfigATG.generateATGRockySteppe) {
                            atgRockySteppe = new RealisticBiomeATGRockySteppe(atgBiome);
                            BiomeBase.addBiome(atgRockySteppe);
                        }
                    }
                    else if (biomeName == "Tropical Shrubland" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenTropicalShrubland")
                    {
                        if (ConfigATG.generateATGTropicalShrubland) {
                            atgTropicalShrubland = new RealisticBiomeATGTropicalShrubland(atgBiome);
                            BiomeBase.addBiome(atgTropicalShrubland);
                        }
                    }
                    else if (biomeName == "Tundra" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenTundra")
                    {
                        if (ConfigATG.generateATGTundra) {
                            atgTundra = new RealisticBiomeATGTundra(atgBiome);
                            BiomeBase.addBiome(atgTundra);
                        }
                    }
                    else if (biomeName == "Volcano" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenVolcano")
                    {
                        if (ConfigATG.generateATGVolcano) {
                            atgVolcano = new RealisticBiomeATGVolcano(atgBiome);
                            BiomeBase.addBiome(atgVolcano);
                        }
                    }
                    else if (biomeName == "Woodland" && biomeClass == "ttftcuts.atg.biome.ATGBiomeGenWoodland")
                    {
                        if (ConfigATG.generateATGWoodland) {
                            atgWoodland = new RealisticBiomeATGWoodland(atgBiome);
                            BiomeBase.addBiome(atgWoodland);
                        }
                    }
					else if (biomeClass.contains("ttftcuts.atg.biome"))
					{
						FMLLog.log(Level.INFO, "ATG biome (%s) could not be generated realistically.", biomeName);
					}
				}
			}
		}
	}
}
