package com.sdj64.highlands.biome;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.sdj64.highlands.Config;
import com.sdj64.highlands.HighlandsSettings;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

/*
 * Highlands biomes - Highlands API
 * 
 * This class contains all of the biomes for Highlands.
 * Only access this class in Post Initialization!
 * The values are populated during Highlands initialization.
 */
public class HighlandsBiomes {

	//main biomes
    public static BiomeGenBase highlandsBiome;
    public static BiomeGenBase pinelands;
    public static BiomeGenBase autumnForest;
    public static BiomeGenBase alps;
    public static BiomeGenBase meadow;
    public static BiomeGenBase tropicDryForest;
    public static BiomeGenBase redwoodForest;
    public static BiomeGenBase lowlands;
    public static BiomeGenBase mojave;
    public static BiomeGenBase poplarHills;
    public static BiomeGenBase badlands;
    public static BiomeGenBase greyMtns;
    public static BiomeGenBase tropHills;
    public static BiomeGenBase dryForest;
    public static BiomeGenBase adirondack;
    public static BiomeGenBase bambooForest;
    public static BiomeGenBase dunes;
    
    //Sub Biomes
    public static BiomeGenBase lake;
    public static BiomeGenBase baldHill;
    public static BiomeGenBase tropicalIslands;
    
    
    //ArrayList of biomes for the Highlands worldtype
    public static ArrayList<BiomeGenBase> biomesForHighlands = new ArrayList<BiomeGenBase>();
    
    //ArrayList of Highlands biomes not including default ones, these will be added to the default world
    //Currently not used since BiomeManager doesn't really do different biomes for different world types
    //public static ArrayList<BiomeGenBase> biomesForDefault = new ArrayList<BiomeGenBase>();
    
    //ArrayList of sub-biomes, controls which Highlands biomes generate as sub-biomes (currently used for Lake and Bald Hill)
    public static ArrayList<BiomeGenBase> subBiomes = new ArrayList<BiomeGenBase>();
    
    //ArrayList of biomes that have foothills, not that are foothills.
    public static ArrayList<BiomeGenBase> foothillsBiomes = new ArrayList<BiomeGenBase>();
    
    
private static String biomePrefix = "";
	
	public static void constructBiomes()
	{
		
		biomePrefix = Config.biomePrefix.getBoolean(false) ? "Highlands_" : "";
		
		//main biomes
		
		if(Config.alpsGenerate.getBoolean(true))
		{
			alps = new BiomeGenAlps(Config.alpsID.getInt()).setBiomeName(biomePrefix+"Alps");
			biomesForHighlands.add(alps);
			createFoothills(alps);
		}
		if(Config.badlandsGenerate.getBoolean(true))
		{
			badlands = new BiomeGenBadlands(Config.badlandsID.getInt()).setBiomeName(biomePrefix+"Badlands");
			biomesForHighlands.add(badlands);
			createFoothills(badlands);
		}
		if(Config.poplarHillsGenerate.getBoolean(true))
		{
			poplarHills = new BiomeGenPoplarHills(Config.poplarHillsID.getInt()).setBiomeName(biomePrefix+"Poplar Hills");
			biomesForHighlands.add(poplarHills);
		}
		if(Config.highlandsbGenerate.getBoolean(true))
		{
			highlandsBiome = new BiomeGenHighlands(Config.highlandsbID.getInt()).setBiomeName(biomePrefix+"Highlands");
			biomesForHighlands.add(highlandsBiome);
		}
		if(Config.lowlandsGenerate.getBoolean(true))
		{
			lowlands = new BiomeGenLowlands(Config.lowlandsID.getInt()).setBiomeName(biomePrefix+"Lowlands");
			biomesForHighlands.add(lowlands);
		}
		if(Config.meadowGenerate.getBoolean(true))
		{
			meadow = new BiomeGenMeadow(Config.meadowID.getInt()).setBiomeName(biomePrefix+"Meadow");
			biomesForHighlands.add(meadow);
		}
		if(Config.pinelandsGenerate.getBoolean(true))
		{
			pinelands = new BiomeGenPinelands(Config.pinelandsID.getInt()).setBiomeName(biomePrefix+"Pinelands");
			biomesForHighlands.add(pinelands);
		}
		if(Config.redwoodForestGenerate.getBoolean(true))
		{
			redwoodForest = new BiomeGenRedwoodForest(Config.redwoodForestID.getInt()).setBiomeName(biomePrefix+"Redwood Forest");
			biomesForHighlands.add(redwoodForest);
		}
		if(Config.mojaveGenerate.getBoolean(true))
		{
			mojave = new BiomeGenMojave(Config.mojaveID.getInt()).setBiomeName(biomePrefix+"Mojave");
			biomesForHighlands.add(mojave);
		}
		if(Config.greyMtnsGenerate.getBoolean(true))
		{
			greyMtns = new BiomeGenGreyMountains(Config.greyMtnsID.getInt()).setBiomeName(biomePrefix+"Grey Mountains");
			biomesForHighlands.add(greyMtns);
			createFoothills(greyMtns);
		}
		if(Config.tropHillsGenerate.getBoolean(true))
		{
			tropHills = new BiomeGenTropHills(Config.tropHillsID.getInt()).setBiomeName(biomePrefix+"Tropical Hills");
			biomesForHighlands.add(tropHills);
		}
		if(Config.dryForestGenerate.getBoolean(true))
		{
			dryForest = new BiomeGenDryForest(Config.dryForestID.getInt()).setBiomeName(biomePrefix+"Dry Forest");
			biomesForHighlands.add(dryForest);
		}
		if(Config.adirondackGenerate.getBoolean(true))
		{
			adirondack = new BiomeGenAdirondacks(Config.adirondackID.getInt()).setBiomeName(biomePrefix+"Adirondacks");
			biomesForHighlands.add(adirondack);
			createFoothills(adirondack);
		}
		if(Config.bambooForestGenerate.getBoolean(true))
		{
			bambooForest = new BiomeGenBambooForest(Config.bambooForestID.getInt()).setBiomeName(biomePrefix+"Bamboo Forest");
			biomesForHighlands.add(bambooForest);
		}
		if(Config.dunesGenerate.getBoolean(true))
		{
			dunes = new BiomeGenDunes(Config.dunesID.getInt()).setBiomeName(biomePrefix+"Dunes");
			biomesForHighlands.add(dunes);
		}
		
		
		//sub-biomes
		if(Config.lakeGenerate.getBoolean(true))
		{
			lake = new BiomeGenLake(Config.lakeID.getInt()).setBiomeName(biomePrefix+"Lake");
			subBiomes.add(lake);
		}
		if(Config.baldHillGenerate.getBoolean(true))
		{
			baldHill = new BiomeGenBaldHill(Config.baldHillID.getInt()).setBiomeName(biomePrefix+"Bald Hill");
			subBiomes.add(baldHill);
		}
		if(Config.tropicalIslandsGenerate.getBoolean(true))
		{
			tropicalIslands = new BiomeGenTropicalIslands(Config.tropicalIslandsID.getInt()).setBiomeName(biomePrefix+"Tropical Islands");
			subBiomes.add(tropicalIslands);
		}
	}
	
	
	//sets up sub-biome lists after all biomes are initialized.
	public static void setUpAllSubBiomes()
	{
		ArrayList<BiomeGenBase> enabledBiomes = new ArrayList<BiomeGenBase>();
		for(BiomeGenBase b : biomesForHighlands)enabledBiomes.add(b);
		for(BiomeGenBase b : subBiomes)enabledBiomes.add(b);
		
		addSubBiome(alps, BiomeGenBase.frozenRiver, enabledBiomes);
		addSubBiome(autumnForest, baldHill, enabledBiomes);
		addSubBiome(autumnForest, lake, enabledBiomes);
		addSubBiome(poplarHills, meadow, enabledBiomes);
		addSubBiome(poplarHills, lake, enabledBiomes);
		addSubBiome(meadow, lake, enabledBiomes);
		addSubBiome(highlandsBiome, BiomeGenBase.forest, enabledBiomes);
		addSubBiome(pinelands, autumnForest, enabledBiomes);
		addSubBiome(redwoodForest, highlandsBiome, enabledBiomes);
		addSubBiome(redwoodForest, lake, enabledBiomes);
		addSubBiome(mojave, BiomeGenBase.mesa, enabledBiomes);
		addSubBiome(mojave, BiomeGenBase.savanna, enabledBiomes);
		addSubBiome(tropHills, lake, enabledBiomes);
		addSubBiome(dryForest, BiomeGenBase.savanna, enabledBiomes);
	}
	
	public static void addSubBiome(BiomeGenBase parent, BiomeGenBase sub, ArrayList<BiomeGenBase> list)
	{
		if(parent != null && sub != null && list.contains(parent) && list.contains(sub) && parent instanceof BiomeGenBaseHighlands){
			((BiomeGenBaseHighlands)parent).subBiomes.add(sub);
		}
	}
	
	public static void setUpBiomeManager()
	{
		for(int i = 0; i < biomesForHighlands.size(); i++)
		{
			BiomeGenBase hlb = biomesForHighlands.get(i);
			if(!(hlb == null))
			{
				//System.out.println(hlb.biomeName + " has been added to the biome list.");
				
				BiomeEntry entry = new BiomeEntry(hlb, 10);
				BiomeType type = (hlb.temperature < 0.3 ? BiomeType.ICY : hlb.temperature < 0.5 ? BiomeType.COOL
						: hlb.temperature < 1.0 ? BiomeType.WARM : BiomeType.DESERT);
				BiomeManager.addBiome(type, entry);
				if(hlb.temperature >= 0.5 && hlb.temperature <= 0.7)
					BiomeManager.addBiome(BiomeType.COOL, entry);
				if(hlb.temperature >= 0.9 && hlb.temperature <= 1.0)
					BiomeManager.addBiome(BiomeType.DESERT, entry);
				BiomeManager.addSpawnBiome(hlb);
				BiomeManager.addStrongholdBiome(hlb);
				if(hlb.equals(meadow) || hlb.equals(highlandsBiome)
						|| hlb.equals(lowlands) || hlb.equals(mojave))
					BiomeManager.addVillageBiome(hlb, true);
			}
			
		}
		if(HighlandsSettings.vanillaBiomeChanges)
			BiomeManager.addVillageBiome(BiomeGenBase.icePlains, true);
		
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(BiomeGenBase.desert, 10));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(BiomeGenBase.savanna, 10));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(BiomeGenBase.mesaPlateau, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(BiomeGenBase.mesaPlateau_F, 5));
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(BiomeGenBase.mesa, 5));
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(BiomeGenBase.jungle, 10));
		
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(BiomeGenBase.megaTaiga, 10));
	}
	
	
	public static void modifyVanillaBiomes(){
		
		if(HighlandsSettings.vanillaBiomeChanges){
		
			BiomeGenBase.extremeHills.minHeight = 1.0F;
			BiomeGenBase.swampland.minHeight = -0.1F;
			BiomeGenBase.savannaPlateau.minHeight = 1.0F;
			BiomeGenBase.stoneBeach.maxHeight = 0.5F;
			BiomeGenBase.river.minHeight = -0.8F;
			BiomeGenBase.river.maxHeight = 0.0F;
		}
	}
    
	/**
	 * Creates a foothills biome, which has half the height of its parent mountain biome.
	 * @param b1 the mountain biome to create foothills for
	 * @return
	 */
	public static BiomeGenBase createFoothills(BiomeGenBase b1){
		Class<? extends BiomeGenBase> biomeClass = b1.getBiomeClass();
		if(b1.biomeID > 127){
			System.out.println("Error generating foothills biome- parent ID " + b1.biomeID + " is over 127.");
			return null;
		}
		else if(BiomeGenBase.getBiome(b1.biomeID + 128) != null){
			System.out.println("Error generating foothills biome- foothills ID " + (b1.biomeID+128) + " is taken.");
			return null;
		}
		BiomeGenBase fh = null;
		try{
			Constructor<?> biomeCons = biomeClass.getConstructor(int.class);
			fh = (BiomeGenBase) biomeCons.newInstance(b1.biomeID + 128);
			fh.maxHeight = b1.maxHeight /2;
			fh.minHeight = b1.minHeight /2;
			fh.setBiomeName(b1.biomeName + " foothills");
			foothillsBiomes.add(b1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fh;
	}
}







