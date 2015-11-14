package rtg.world.biome.realistic.extrabiomes;

import cpw.mods.fml.common.Loader;
import extrabiomes.api.BiomeManager;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceDesertMountain;
import rtg.world.gen.surface.SurfaceGrassland;
import rtg.world.gen.surface.SurfaceGrasslandMix1;
import rtg.world.gen.surface.SurfaceMarshFix;
import rtg.world.gen.surface.SurfaceMountainSnow;
import rtg.world.gen.surface.SurfaceMountainStone;
import rtg.world.gen.surface.SurfacePolar;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainGrasslandFlats;
import rtg.world.gen.terrain.TerrainGrasslandHills;
import rtg.world.gen.terrain.TerrainHighland;
import rtg.world.gen.terrain.TerrainHilly;
import rtg.world.gen.terrain.TerrainMarsh;
import rtg.world.gen.terrain.TerrainMountainRiver;
import rtg.world.gen.terrain.TerrainMountainSpikes;
import rtg.world.gen.terrain.TerrainPolar;
import rtg.world.gen.terrain.TerrainSwampMountain;
import rtg.world.gen.terrain.TerrainSwampRiver;

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
		
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ExtrabiomesXL") && ConfigEBXL.generateEBXLBiomes)
		{			
		    //ALPINE
			if(BiomeManager.alpine.isPresent() && ConfigEBXL.generateEBXL_alpine) {
				ebxl_alpine = new RealisticBiomeEBXLAlpine();
				BiomeBase.addBiome(ebxl_alpine);
			}
			
		    //AUTUMNWOODS
			if(BiomeManager.autumnwoods.isPresent() && ConfigEBXL.generateEBXL_autumnwoods) {
				ebxl_autumnwoods = new RealisticBiomeEBXLAutumnWoods();
				BiomeBase.addBiome(ebxl_autumnwoods);
			}
			
			//BIRCHFOREST
			if(BiomeManager.birchforest.isPresent() && ConfigEBXL.generateEBXL_birchforest) {
				ebxl_birchforest = new RealisticBiomeEBXLBirchForest();
				BiomeBase.addBiome(ebxl_birchforest);
			}
			
		    //EXTREME JUNGLE
			if(BiomeManager.extremejungle.isPresent() && ConfigEBXL.generateEBXL_extremejungle) {
				ebxl_extremejungle = new RealisticBiomeEBXLExtremeJungle();
				BiomeBase.addBiome(ebxl_extremejungle);
			}
			
		    //FORESTED HILLS
			if(BiomeManager.forestedhills.isPresent() && ConfigEBXL.generateEBXL_forestedhills) {
				ebxl_forestedhills = new RealisticBiomeEBXLForestedHills();
				BiomeBase.addBiome(ebxl_forestedhills);
			}
			
			//FORESTED ISLAND
			if(BiomeManager.forestedisland.isPresent() && ConfigEBXL.generateEBXL_forestedisland) {
				ebxl_forestedisland = new RealisticBiomeEBXLForestedIsland();
				BiomeBase.addBiome(ebxl_forestedisland);
			}

			//GLACIER
			if(BiomeManager.glacier.isPresent() && ConfigEBXL.generateEBXL_glacier) {
				ebxl_glacier = new RealisticBiomeEBXLGlacier();
				BiomeBase.addBiome(ebxl_glacier);
			}
			
			//GREENHILLS
			if(BiomeManager.greenhills.isPresent() && ConfigEBXL.generateEBXL_greenhills) {
				ebxl_greenhills = new RealisticBiomeEBXLGreenHills();
				BiomeBase.addBiome(ebxl_greenhills);
			}
			
			//GREENSWAMP
			if(BiomeManager.greenswamp.isPresent() && ConfigEBXL.generateEBXL_greenswamp) {
				ebxl_greenswamp = new RealisticBiomeEBXLGreenSwamp();
				BiomeBase.addBiome(ebxl_greenswamp);
			}
			
		    //ICEWASTELAND
			if(BiomeManager.icewasteland.isPresent() && ConfigEBXL.generateEBXL_icewasteland) {
				ebxl_icewasteland = new RealisticBiomeEBXLIceWasteland();
				BiomeBase.addBiome(ebxl_icewasteland);
			}
			
		    //MARSH
			if(BiomeManager.marsh.isPresent() && ConfigEBXL.generateEBXL_marsh) {
				ebxl_marsh = new RealisticBiomeEBXLMarsh();
				BiomeBase.addBiome(ebxl_marsh);
			}
			
		    //MEADOW
			if(BiomeManager.meadow.isPresent() && ConfigEBXL.generateEBXL_meadow) {
				ebxl_meadow = new RealisticBiomeEBXLMeadow();
				BiomeBase.addBiome(ebxl_meadow);
			}
			
			//MINI JUNGLE
			if(BiomeManager.minijungle.isPresent() && ConfigEBXL.generateEBXL_minijungle) {
				ebxl_minijungle = new RealisticBiomeEBXLMiniJungle();
				BiomeBase.addBiome(ebxl_minijungle);
			}
			
		    //MOUNTAIN DESERT
			if(BiomeManager.mountaindesert.isPresent() && ConfigEBXL.generateEBXL_mountaindesert) {
				ebxl_mountaindesert = new RealisticBiomeEBXLMountainDesert();
				BiomeBase.addBiome(ebxl_mountaindesert);
			}
			
			//MOUNTAIN RIDGE
			if(BiomeManager.mountainridge.isPresent() && ConfigEBXL.generateEBXL_mountainridge) {
				ebxl_mountainridge = new RealisticBiomeEBXLMountainRidge();
				BiomeBase.addBiome(ebxl_mountainridge);
			}
			
		    //MOUNTAIN TAIGA
			if(BiomeManager.mountaintaiga.isPresent() && ConfigEBXL.generateEBXL_mountaintaiga) {
				ebxl_mountaintaiga = new RealisticBiomeEBXLMountainTaiga();
				BiomeBase.addBiome(ebxl_mountaintaiga);
			}
			
		    //PINE FOREST
			if(BiomeManager.pineforest.isPresent() && ConfigEBXL.generateEBXL_pineforest) {
				ebxl_pineforest = new RealisticBiomeEBXLPineForest();
				BiomeBase.addBiome(ebxl_pineforest);
			}
			
		    //RAINFOREST
			if(BiomeManager.rainforest.isPresent() && ConfigEBXL.generateEBXL_rainforest) {
				ebxl_rainforest = new RealisticBiomeEBXLRainforest();
				BiomeBase.addBiome(ebxl_rainforest);
			}
			
			//REDWOOD FOREST
			if(BiomeManager.redwoodforest.isPresent() && ConfigEBXL.generateEBXL_redwoodforest) {
				ebxl_redwoodforest = new RealisticBiomeEBXLRedwoodForest();
				BiomeBase.addBiome(ebxl_redwoodforest);
			}
			
		    //REDWOOD LUSH
			if(BiomeManager.redwoodlush.isPresent() && ConfigEBXL.generateEBXL_redwoodlush) {
				ebxl_redwoodlush = new RealisticBiomeEBXLRedwoodLush();
				BiomeBase.addBiome(ebxl_redwoodlush);
			}
			
		    //SAVANNA
			if(BiomeManager.savanna.isPresent() && ConfigEBXL.generateEBXL_savanna) {
				ebxl_savanna = new RealisticBiomeEBXLSavanna();
				BiomeBase.addBiome(ebxl_savanna);
			}
			
			//SHRUBLAND
			if(BiomeManager.shrubland.isPresent() && ConfigEBXL.generateEBXL_shrubland) {
				ebxl_shrubland = new RealisticBiomeEBXLShrubland();
				BiomeBase.addBiome(ebxl_shrubland);
			}
			
			//SNOW FOREST
			if(BiomeManager.snowforest.isPresent() && ConfigEBXL.generateEBXL_snowforest) {
				ebxl_snowforest = new RealisticBiomeEBXLSnowForest();
				BiomeBase.addBiome(ebxl_snowforest);
			}
			
		    //SNOWY RAIN FOREST
			if(BiomeManager.snowyrainforest.isPresent() && ConfigEBXL.generateEBXL_snowyrainforest) {
				ebxl_snowyrainforest = new RealisticBiomeEBXLSnowyRainforest();
				BiomeBase.addBiome(ebxl_snowyrainforest);
			}
			
		    //TEMPERATE RAINFOREST
			if(BiomeManager.temperaterainforest.isPresent() && ConfigEBXL.generateEBXL_temperaterainforest) {
				ebxl_temperaterainforest = new RealisticBiomeEBXLTemperateRainforest();
				BiomeBase.addBiome(ebxl_temperaterainforest);
			}
			
		    //TUNDRA
			if(BiomeManager.tundra.isPresent() && ConfigEBXL.generateEBXL_tundra) {
				ebxl_tundra = new RealisticBiomeEBXLTundra();
				BiomeBase.addBiome(ebxl_tundra);
			}
			
			//WASTELAND
			if(BiomeManager.wasteland.isPresent() && ConfigEBXL.generateEBXL_wasteland) {
				ebxl_wasteland = new RealisticBiomeEBXLWasteland();
				BiomeBase.addBiome(ebxl_wasteland);
			}
			
			//WOODLANDS
			if(BiomeManager.woodlands.isPresent() && ConfigEBXL.generateEBXL_woodlands) {
				ebxl_woodlands = new RealisticBiomeEBXLWoodlands();
				BiomeBase.addBiome(ebxl_woodlands);
			}
		}		
	}
}
