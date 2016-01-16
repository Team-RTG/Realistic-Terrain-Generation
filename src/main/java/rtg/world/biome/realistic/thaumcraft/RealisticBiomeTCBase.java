package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCBase extends RealisticBiomeBase
{	
    public static RealisticBiomeBase tcMagicalForest;
    public static RealisticBiomeBase tcTaintedLand;
    public static RealisticBiomeBase tcEerie;
    
	public RealisticBiomeTCBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	/*
	THAUMCRAFT BIOMES
	
	118: "Tainted Land"
	119: "Magical Forest"
	*/
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("Thaumcraft"))
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for(int i = 0; i < 256; i++)
			{
				if(b[i] != null)
				{
					BiomeGenBase tcBiome = b[i];
					String biomeName = tcBiome.biomeName;
					String biomeClass = tcBiome.getBiomeClass().getName();
					
                    if (biomeName == "Magical Forest" && biomeClass == "thaumcraft.common.lib.world.biomes.BiomeGenMagicalForest")
                    {
                        tcMagicalForest = new RealisticBiomeTCMagicalForest(tcBiome, BiomeConfigTC.biomeConfigTCMagicalForest);
                        
                        if (ConfigTC.generateTCBiomes && tcMagicalForest.config._boolean("biomeEnabled")) {

                            BiomeBase.addBiome(tcMagicalForest);
                            BiomeBase.addVillageBiome(tcMagicalForest);
                        }
                    }
                    else if (biomeName == "Tainted Land" && biomeClass == "thaumcraft.common.lib.world.biomes.BiomeGenTaint")
					{
                        tcTaintedLand = new RealisticBiomeTCTaintedLand(tcBiome, BiomeConfigTC.biomeConfigTCTaintedLand);
                        
						if (ConfigTC.generateTCBiomes && tcTaintedLand.config._boolean("biomeEnabled")) {

						    BiomeBase.addBiome(tcTaintedLand);
						    BiomeBase.addVillageBiome(tcTaintedLand);
						}
					}
                    else if (biomeName.equals("Eerie") && biomeClass.contains("thaumcraft.common.lib.world.biomes"))
					{
                        tcEerie = new RealisticBiomeTCEerie(tcBiome, BiomeConfigTC.biomeConfigTCEerie);
                        
						if (ConfigTC.generateTCBiomes && tcEerie.config._boolean("biomeEnabled")) {

						    BiomeBase.addBiome(tcEerie);
						    BiomeBase.addVillageBiome(tcEerie);
						}
					}

				}
			}
		}
	}
}