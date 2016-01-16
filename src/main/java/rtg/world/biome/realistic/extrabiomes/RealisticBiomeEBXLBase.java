package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXL;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;
import extrabiomes.api.BiomeManager;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLBase extends RealisticBiomeBase
{
	public static RealisticBiomeBase ebxl_alpine;
	public static RealisticBiomeBase ebxl_autumnwoods;
	public static RealisticBiomeBase ebxl_birchforest;
	public static RealisticBiomeBase ebxl_extremejungle;
	public static RealisticBiomeBase ebxl_forestedhills;
	public static RealisticBiomeBase ebxl_forestedisland;
	public static RealisticBiomeBase ebxl_glacier;
	public static RealisticBiomeBase ebxl_greenhills;
	public static RealisticBiomeBase ebxl_greenswamp;
	public static RealisticBiomeBase ebxl_icewasteland;
	public static RealisticBiomeBase ebxl_marsh;
	public static RealisticBiomeBase ebxl_meadow;
	public static RealisticBiomeBase ebxl_minijungle;
	public static RealisticBiomeBase ebxl_mountaindesert;
	public static RealisticBiomeBase ebxl_mountainridge;
	public static RealisticBiomeBase ebxl_mountaintaiga;
	public static RealisticBiomeBase ebxl_pineforest;
	public static RealisticBiomeBase ebxl_rainforest;
	public static RealisticBiomeBase ebxl_redwoodforest;
	public static RealisticBiomeBase ebxl_redwoodlush;
	public static RealisticBiomeBase ebxl_savanna;
	public static RealisticBiomeBase ebxl_shrubland;
	public static RealisticBiomeBase ebxl_snowforest;
	public static RealisticBiomeBase ebxl_snowyrainforest;
	public static RealisticBiomeBase ebxl_temperaterainforest;
	public static RealisticBiomeBase ebxl_tundra;
	public static RealisticBiomeBase ebxl_wasteland;
	public static RealisticBiomeBase ebxl_woodlands;
	
	public RealisticBiomeEBXLBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ExtrabiomesXL"))
		{			
		    //ALPINE
			if(BiomeManager.alpine.isPresent()) {
			    
				ebxl_alpine = new RealisticBiomeEBXLAlpine(BiomeConfigEBXL.biomeConfigEBXLAlpine);
				
				if (ConfigEBXL.generateEBXLBiomes && ebxl_alpine.config._boolean(BiomeConfig.enableBiomeId)) {
				    
    				BiomeBase.addBiome(ebxl_alpine);
    				BiomeBase.addVillageBiome(ebxl_alpine);
				}
			}
			
		    //AUTUMNWOODS
			if(BiomeManager.autumnwoods.isPresent() && ConfigEBXL.generateEBXLAutumnWoods) {
			    
				ebxl_autumnwoods = new RealisticBiomeEBXLAutumnWoods(BiomeConfigEBXL.biomeConfigEBXLAutumnWoods);

                if (ConfigEBXL.generateEBXLBiomes && ebxl_autumnwoods.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_autumnwoods);
    				BiomeBase.addVillageBiome(ebxl_autumnwoods);
                }
			}
			
			//BIRCHFOREST
			if(BiomeManager.birchforest.isPresent() && ConfigEBXL.generateEBXLBirchForest) {
			    
				ebxl_birchforest = new RealisticBiomeEBXLBirchForest(BiomeConfigEBXL.biomeConfigEBXLBirchForest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_birchforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_birchforest);
    				BiomeBase.addVillageBiome(ebxl_birchforest);
                }
			}
			
		    //EXTREME JUNGLE
			if(BiomeManager.extremejungle.isPresent() && ConfigEBXL.generateEBXLExtremeJungle) {
			    
				ebxl_extremejungle = new RealisticBiomeEBXLExtremeJungle(BiomeConfigEBXL.biomeConfigEBXLExtremeJungle);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_extremejungle.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_extremejungle);
    				BiomeBase.addVillageBiome(ebxl_extremejungle);
                }
			}
			
		    //FORESTED HILLS
			if(BiomeManager.forestedhills.isPresent() && ConfigEBXL.generateEBXLForestedHills) {
			    
				ebxl_forestedhills = new RealisticBiomeEBXLForestedHills(BiomeConfigEBXL.biomeConfigEBXLForestedHills);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_forestedhills.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_forestedhills);
    				BiomeBase.addVillageBiome(ebxl_forestedhills);
                }
			}
			
			//FORESTED ISLAND
			if(BiomeManager.forestedisland.isPresent() && ConfigEBXL.generateEBXLForestedIsland) {
			    
				ebxl_forestedisland = new RealisticBiomeEBXLForestedIsland(BiomeConfigEBXL.biomeConfigEBXLForestedIsland);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_forestedisland.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_forestedisland);
    				BiomeBase.addVillageBiome(ebxl_forestedisland);
                }
			}

			//GLACIER
			if(BiomeManager.glacier.isPresent() && ConfigEBXL.generateEBXLGlacier) {
			    
				ebxl_glacier = new RealisticBiomeEBXLGlacier(BiomeConfigEBXL.biomeConfigEBXLGlacier);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_glacier.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_glacier);
    				BiomeBase.addVillageBiome(ebxl_glacier);
                }
			}
			
			//GREENHILLS
			if(BiomeManager.greenhills.isPresent() && ConfigEBXL.generateEBXLGreenHills) {
			    
				ebxl_greenhills = new RealisticBiomeEBXLGreenHills(BiomeConfigEBXL.biomeConfigEBXLGreenHills);

                if (ConfigEBXL.generateEBXLBiomes && ebxl_greenhills.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_greenhills);
    				BiomeBase.addVillageBiome(ebxl_greenhills);
                }
			}
			
			//GREENSWAMP
			if(BiomeManager.greenswamp.isPresent() && ConfigEBXL.generateEBXLGreenSwamp) {
			    
				ebxl_greenswamp = new RealisticBiomeEBXLGreenSwamp(BiomeConfigEBXL.biomeConfigEBXLGreenSwamp);

                if (ConfigEBXL.generateEBXLBiomes && ebxl_greenswamp.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_greenswamp);
    				BiomeBase.addVillageBiome(ebxl_greenswamp);
                }
			}
			
		    //ICEWASTELAND
			if(BiomeManager.icewasteland.isPresent() && ConfigEBXL.generateEBXLIceWasteland) {
			    
				ebxl_icewasteland = new RealisticBiomeEBXLIceWasteland(BiomeConfigEBXL.biomeConfigEBXLIceWasteland);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_icewasteland.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_icewasteland);
    				BiomeBase.addVillageBiome(ebxl_icewasteland);
                }
			}
			
		    //MARSH
			if(BiomeManager.marsh.isPresent() && ConfigEBXL.generateEBXLMarsh) {
			    
				ebxl_marsh = new RealisticBiomeEBXLMarsh(BiomeConfigEBXL.biomeConfigEBXLMarsh);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_marsh.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_marsh);
    				BiomeBase.addVillageBiome(ebxl_marsh);
                }
			}
			
		    //MEADOW
			if(BiomeManager.meadow.isPresent() && ConfigEBXL.generateEBXLMeadow) {
			    
				ebxl_meadow = new RealisticBiomeEBXLMeadow(BiomeConfigEBXL.biomeConfigEBXLMeadow);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_meadow.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_meadow);
    				BiomeBase.addVillageBiome(ebxl_meadow);
                }
			}
			
			//MINI JUNGLE
			if(BiomeManager.minijungle.isPresent() && ConfigEBXL.generateEBXLMiniJungle) {
			    
				ebxl_minijungle = new RealisticBiomeEBXLMiniJungle(BiomeConfigEBXL.biomeConfigEBXLMiniJungle);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_minijungle.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_minijungle);
    				BiomeBase.addVillageBiome(ebxl_minijungle);
                }
			}
			
		    //MOUNTAIN DESERT
			if(BiomeManager.mountaindesert.isPresent() && ConfigEBXL.generateEBXLMountainDesert) {
			    
				ebxl_mountaindesert = new RealisticBiomeEBXLMountainDesert(BiomeConfigEBXL.biomeConfigEBXLMountainDesert);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_mountaindesert.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_mountaindesert);
    				BiomeBase.addVillageBiome(ebxl_mountaindesert);
                }
			}
			
			//MOUNTAIN RIDGE
			if(BiomeManager.mountainridge.isPresent() && ConfigEBXL.generateEBXLMountainRidge) {
			    
				ebxl_mountainridge = new RealisticBiomeEBXLMountainRidge(BiomeConfigEBXL.biomeConfigEBXLMountainRidge);

                if (ConfigEBXL.generateEBXLBiomes && ebxl_mountainridge.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_mountainridge);
    				BiomeBase.addVillageBiome(ebxl_mountainridge);
                }
			}
			
		    //MOUNTAIN TAIGA
			if(BiomeManager.mountaintaiga.isPresent() && ConfigEBXL.generateEBXLMountainTaiga) {
			    
				ebxl_mountaintaiga = new RealisticBiomeEBXLMountainTaiga(BiomeConfigEBXL.biomeConfigEBXLMountainTaiga);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_mountaintaiga.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_mountaintaiga);
    				BiomeBase.addVillageBiome(ebxl_mountaintaiga);
                }
			}
			
		    //PINE FOREST
			if(BiomeManager.pineforest.isPresent() && ConfigEBXL.generateEBXLPineForest) {
			    
				ebxl_pineforest = new RealisticBiomeEBXLPineForest(BiomeConfigEBXL.biomeConfigEBXLPineForest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_pineforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_pineforest);
    				BiomeBase.addVillageBiome(ebxl_pineforest);
                }
			}
			
		    //RAINFOREST
			if(BiomeManager.rainforest.isPresent() && ConfigEBXL.generateEBXLRainforest) {
			    
				ebxl_rainforest = new RealisticBiomeEBXLRainforest(BiomeConfigEBXL.biomeConfigEBXLRainforest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_rainforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_rainforest);
    				BiomeBase.addVillageBiome(ebxl_rainforest);
                }
			}
			
			//REDWOOD FOREST
			if(BiomeManager.redwoodforest.isPresent() && ConfigEBXL.generateEBXLRedwoodForest) {
			    
				ebxl_redwoodforest = new RealisticBiomeEBXLRedwoodForest(BiomeConfigEBXL.biomeConfigEBXLRedwoodForest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_redwoodforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_redwoodforest);
    				BiomeBase.addVillageBiome(ebxl_redwoodforest);
                }
			}
			
		    //REDWOOD LUSH
			if(BiomeManager.redwoodlush.isPresent() && ConfigEBXL.generateEBXLRedwoodLush) {
			    
				ebxl_redwoodlush = new RealisticBiomeEBXLRedwoodLush(BiomeConfigEBXL.biomeConfigEBXLRedwoodLush);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_redwoodlush.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_redwoodlush);
    				BiomeBase.addVillageBiome(ebxl_redwoodlush);
                }
			}
			
		    //SAVANNA
			if(BiomeManager.savanna.isPresent() && ConfigEBXL.generateEBXLSavanna) {
			    
				ebxl_savanna = new RealisticBiomeEBXLSavanna(BiomeConfigEBXL.biomeConfigEBXLSavanna);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_savanna.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_savanna);
    				BiomeBase.addVillageBiome(ebxl_savanna);
                }
			}
			
			//SHRUBLAND
			if(BiomeManager.shrubland.isPresent() && ConfigEBXL.generateEBXLShrubland) {
			    
				ebxl_shrubland = new RealisticBiomeEBXLShrubland(BiomeConfigEBXL.biomeConfigEBXLShrubland);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_shrubland.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_shrubland);
    				BiomeBase.addVillageBiome(ebxl_shrubland);
                }
			}
			
			//SNOW FOREST
			if(BiomeManager.snowforest.isPresent() && ConfigEBXL.generateEBXLSnowForest) {
			    
				ebxl_snowforest = new RealisticBiomeEBXLSnowForest(BiomeConfigEBXL.biomeConfigEBXLSnowForest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_snowforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_snowforest);
    				BiomeBase.addVillageBiome(ebxl_snowforest);
                }
			}
			
		    //SNOWY RAIN FOREST
			if(BiomeManager.snowyrainforest.isPresent() && ConfigEBXL.generateEBXLSnowyRainforest) {
			    
				ebxl_snowyrainforest = new RealisticBiomeEBXLSnowyRainforest(BiomeConfigEBXL.biomeConfigEBXLSnowyRainforest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_snowyrainforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_snowyrainforest);
    				BiomeBase.addVillageBiome(ebxl_snowyrainforest);
                }
			}
			
		    //TEMPERATE RAINFOREST
			if(BiomeManager.temperaterainforest.isPresent() && ConfigEBXL.generateEBXLTemperateRainforest) {
			    
				ebxl_temperaterainforest = new RealisticBiomeEBXLTemperateRainforest(BiomeConfigEBXL.biomeConfigEBXLTemperateRainforest);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_temperaterainforest.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_temperaterainforest);
    				BiomeBase.addVillageBiome(ebxl_temperaterainforest);
                }
			}
			
		    //TUNDRA
			if(BiomeManager.tundra.isPresent() && ConfigEBXL.generateEBXLTundra) {
			    
				ebxl_tundra = new RealisticBiomeEBXLTundra(BiomeConfigEBXL.biomeConfigEBXLTundra);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_tundra.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_tundra);
    				BiomeBase.addVillageBiome(ebxl_tundra);
                }
			}
			
			//WASTELAND
			if(BiomeManager.wasteland.isPresent() && ConfigEBXL.generateEBXLWasteland) {
			    
				ebxl_wasteland = new RealisticBiomeEBXLWasteland(BiomeConfigEBXL.biomeConfigEBXLWasteland);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_wasteland.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_wasteland);
    				BiomeBase.addVillageBiome(ebxl_wasteland);
                }
			}
			
			//WOODLANDS
			if(BiomeManager.woodlands.isPresent() && ConfigEBXL.generateEBXLWoodlands) {
			    
				ebxl_woodlands = new RealisticBiomeEBXLWoodlands(BiomeConfigEBXL.biomeConfigEBXLWoodlands);
                
                if (ConfigEBXL.generateEBXLBiomes && ebxl_woodlands.config._boolean(BiomeConfig.enableBiomeId)) {
                    
    				BiomeBase.addBiome(ebxl_woodlands);
    				BiomeBase.addVillageBiome(ebxl_woodlands);
                }
			}
		}		
	}
}
