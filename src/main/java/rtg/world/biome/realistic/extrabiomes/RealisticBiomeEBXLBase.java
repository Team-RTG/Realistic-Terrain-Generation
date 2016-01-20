package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.extrabiomes.config.BiomeConfigEBXL;
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
			}
			
		    //AUTUMNWOODS
			if(BiomeManager.autumnwoods.isPresent()) {
			    
				ebxl_autumnwoods = new RealisticBiomeEBXLAutumnWoods(BiomeConfigEBXL.biomeConfigEBXLAutumnWoods);
			}
			
			//BIRCHFOREST
			if(BiomeManager.birchforest.isPresent()) {
			    
				ebxl_birchforest = new RealisticBiomeEBXLBirchForest(BiomeConfigEBXL.biomeConfigEBXLBirchForest);
			}
			
		    //EXTREME JUNGLE
			if(BiomeManager.extremejungle.isPresent()) {
			    
				ebxl_extremejungle = new RealisticBiomeEBXLExtremeJungle(BiomeConfigEBXL.biomeConfigEBXLExtremeJungle);
			}
			
		    //FORESTED HILLS
			if(BiomeManager.forestedhills.isPresent()) {
			    
				ebxl_forestedhills = new RealisticBiomeEBXLForestedHills(BiomeConfigEBXL.biomeConfigEBXLForestedHills);
			}
			
			//FORESTED ISLAND
			if(BiomeManager.forestedisland.isPresent()) {
			    
				ebxl_forestedisland = new RealisticBiomeEBXLForestedIsland(BiomeConfigEBXL.biomeConfigEBXLForestedIsland);
			}

			//GLACIER
			if(BiomeManager.glacier.isPresent()) {
			    
				ebxl_glacier = new RealisticBiomeEBXLGlacier(BiomeConfigEBXL.biomeConfigEBXLGlacier);
			}
			
			//GREENHILLS
			if(BiomeManager.greenhills.isPresent()) {
			    
				ebxl_greenhills = new RealisticBiomeEBXLGreenHills(BiomeConfigEBXL.biomeConfigEBXLGreenHills);
			}
			
			//GREENSWAMP
			if(BiomeManager.greenswamp.isPresent()) {
			    
				ebxl_greenswamp = new RealisticBiomeEBXLGreenSwamp(BiomeConfigEBXL.biomeConfigEBXLGreenSwamp);
			}
			
		    //ICEWASTELAND
			if(BiomeManager.icewasteland.isPresent()) {
			    
				ebxl_icewasteland = new RealisticBiomeEBXLIceWasteland(BiomeConfigEBXL.biomeConfigEBXLIceWasteland);
			}
			
		    //MARSH
			if(BiomeManager.marsh.isPresent()) {
			    
				ebxl_marsh = new RealisticBiomeEBXLMarsh(BiomeConfigEBXL.biomeConfigEBXLMarsh);
			}
			
		    //MEADOW
			if(BiomeManager.meadow.isPresent()) {
			    
				ebxl_meadow = new RealisticBiomeEBXLMeadow(BiomeConfigEBXL.biomeConfigEBXLMeadow);
			}
			
			//MINI JUNGLE
			if(BiomeManager.minijungle.isPresent()) {
			    
				ebxl_minijungle = new RealisticBiomeEBXLMiniJungle(BiomeConfigEBXL.biomeConfigEBXLMiniJungle);
			}
			
		    //MOUNTAIN DESERT
			if(BiomeManager.mountaindesert.isPresent()) {
			    
				ebxl_mountaindesert = new RealisticBiomeEBXLMountainDesert(BiomeConfigEBXL.biomeConfigEBXLMountainDesert);
			}
			
			//MOUNTAIN RIDGE
			if(BiomeManager.mountainridge.isPresent()) {
			    
				ebxl_mountainridge = new RealisticBiomeEBXLMountainRidge(BiomeConfigEBXL.biomeConfigEBXLMountainRidge);
			}
			
		    //MOUNTAIN TAIGA
			if(BiomeManager.mountaintaiga.isPresent()) {
			    
				ebxl_mountaintaiga = new RealisticBiomeEBXLMountainTaiga(BiomeConfigEBXL.biomeConfigEBXLMountainTaiga);
			}
			
		    //PINE FOREST
			if(BiomeManager.pineforest.isPresent()) {
			    
				ebxl_pineforest = new RealisticBiomeEBXLPineForest(BiomeConfigEBXL.biomeConfigEBXLPineForest);
			}
			
		    //RAINFOREST
			if(BiomeManager.rainforest.isPresent()) {
			    
				ebxl_rainforest = new RealisticBiomeEBXLRainforest(BiomeConfigEBXL.biomeConfigEBXLRainforest);
			}
			
			//REDWOOD FOREST
			if(BiomeManager.redwoodforest.isPresent()) {
			    
				ebxl_redwoodforest = new RealisticBiomeEBXLRedwoodForest(BiomeConfigEBXL.biomeConfigEBXLRedwoodForest);
			}
			
		    //REDWOOD LUSH
			if(BiomeManager.redwoodlush.isPresent()) {
			    
				ebxl_redwoodlush = new RealisticBiomeEBXLRedwoodLush(BiomeConfigEBXL.biomeConfigEBXLRedwoodLush);
			}
			
		    //SAVANNA
			if(BiomeManager.savanna.isPresent()) {
			    
				ebxl_savanna = new RealisticBiomeEBXLSavanna(BiomeConfigEBXL.biomeConfigEBXLSavanna);
			}
			
			//SHRUBLAND
			if(BiomeManager.shrubland.isPresent()) {
			    
				ebxl_shrubland = new RealisticBiomeEBXLShrubland(BiomeConfigEBXL.biomeConfigEBXLShrubland);
			}
			
			//SNOW FOREST
			if(BiomeManager.snowforest.isPresent()) {
			    
				ebxl_snowforest = new RealisticBiomeEBXLSnowForest(BiomeConfigEBXL.biomeConfigEBXLSnowForest);
			}
			
		    //SNOWY RAIN FOREST
			if(BiomeManager.snowyrainforest.isPresent()) {
			    
				ebxl_snowyrainforest = new RealisticBiomeEBXLSnowyRainforest(BiomeConfigEBXL.biomeConfigEBXLSnowyRainforest);
			}
			
		    //TEMPERATE RAINFOREST
			if(BiomeManager.temperaterainforest.isPresent()) {
			    
				ebxl_temperaterainforest = new RealisticBiomeEBXLTemperateRainforest(BiomeConfigEBXL.biomeConfigEBXLTemperateRainforest);
			}
			
		    //TUNDRA
			if(BiomeManager.tundra.isPresent()) {
			    
				ebxl_tundra = new RealisticBiomeEBXLTundra(BiomeConfigEBXL.biomeConfigEBXLTundra);
			}
			
			//WASTELAND
			if(BiomeManager.wasteland.isPresent()) {
			    
				ebxl_wasteland = new RealisticBiomeEBXLWasteland(BiomeConfigEBXL.biomeConfigEBXLWasteland);
			}
			
			//WOODLANDS
			if(BiomeManager.woodlands.isPresent()) {
			    
				ebxl_woodlands = new RealisticBiomeEBXLWoodlands(BiomeConfigEBXL.biomeConfigEBXLWoodlands);
			}
		}		
	}
}
