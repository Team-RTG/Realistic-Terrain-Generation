package enhancedbiomes.handlers;

import java.io.File;
import java.util.ArrayList;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.MinecraftForge;
import static net.minecraft.world.biome.BiomeGenBase.*;

public class VanillaHandler 
{	
	public static int plainsGen;
	public static boolean villagePlains;
	
	public static int desertGen;
	public static boolean villageDesert;
	
	public static int extremeHillsGen;
	public static boolean villageExtremeHills;
	
	public static int forestGen;
	public static boolean villageForest;
	
	public static int taigaGen;
	public static boolean villageTaiga;
	
	public static int swamplandGen;
	public static boolean villageSwampland;
	
	public static int jungleGen;
	public static boolean villageJungle;
	
	public static int icePlainsGen;
	public static boolean villageIcePlains;
			
	public static boolean villageMushroomIsland;
	
	public static int birchForestGen;
	public static boolean villageBirchForest;
	
	public static int roofedForestGen;
	public static boolean villageRoofedForest;
	
	public static int coldTaigaGen;
	public static boolean villageColdTaiga;
	
	public static int megaTaigaGen;
	public static boolean villageMegaTaiga;
	
	public static int savannaGen;
	public static boolean villageSavanna;
	
	public static int mesaGen;
	public static boolean villageMesa;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Vanilla.cfg");
		Configuration config = new Configuration(configFile);
		config.load();
		
		plainsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Plains biome", 10).getInt();
		desertGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Desert biome", 10).getInt();
		extremeHillsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Extreme Hills biome", 10).getInt();
		forestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Forest biome", 10).getInt();
		taigaGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Taiga biome", 10).getInt();
		swamplandGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Swamp biome", 10).getInt();
		jungleGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Jungle biome", 10).getInt();
		icePlainsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Ice Plains biome", 10).getInt();
		birchForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Birch biome", 10).getInt();
		roofedForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Roofed Forest biome", 10).getInt();
		coldTaigaGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Cold Taiga biome", 10).getInt();
		megaTaigaGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Mega Taiga biome", 10).getInt();
		savannaGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Savanna biome", 10).getInt();
		mesaGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Mesa biome", 10).getInt();
		  		  		  		  
		villagePlains = config.get(config.CATEGORY_GENERAL, "Generate villages in Plains biome", true).getBoolean(true);
		villageDesert = config.get(config.CATEGORY_GENERAL, "Generate villages in Desert biome", true).getBoolean(true);
		villageExtremeHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Extreme Hills biome", true).getBoolean(true);
		villageForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Forest biome", true).getBoolean(true);
		villageTaiga = config.get(config.CATEGORY_GENERAL, "Generate villages in Taiga biome", true).getBoolean(true);
		villageSwampland = config.get(config.CATEGORY_GENERAL, "Generate villages in Swampland biome", true).getBoolean(true);
		villageIcePlains = config.get(config.CATEGORY_GENERAL, "Generate villages in Ice Plains biome", true).getBoolean(true);
		villageMushroomIsland = config.get(config.CATEGORY_GENERAL, "Generate villages in Mushroom Island biome", true).getBoolean(true);
		villageJungle = config.get(config.CATEGORY_GENERAL, "Generate villages in Jungle biome", true).getBoolean(true);
		villageBirchForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Birch Forest biome", true).getBoolean(true);
		villageRoofedForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Roofed Forest biome", true).getBoolean(true);
		villageColdTaiga = config.get(config.CATEGORY_GENERAL, "Generate villages in Cold Taiga biome", true).getBoolean(true);
		villageMegaTaiga = config.get(config.CATEGORY_GENERAL, "Generate villages in Mega Taiga biome", true).getBoolean(true);
		villageSavanna = config.get(config.CATEGORY_GENERAL, "Generate villages in Savanna biome", true).getBoolean(true);
		villageMesa = config.get(config.CATEGORY_GENERAL, "Generate villages in Mesa biome", true).getBoolean(true);

		config.save();	
	}
	
	public static void load()
	{
		BiomeGenManager.addEBWarmBiome(plains, plainsGen);
		BiomeGenManager.addEBHotBiome(desert, desertGen);
		BiomeGenManager.addEBCoolBiome(extremeHills, extremeHillsGen);
		BiomeGenManager.addEBWarmBiome(forest, forestGen);
		BiomeGenManager.addEBCoolBiome(taiga, taigaGen);
		BiomeGenManager.addEBWarmBiome(swampland, swamplandGen);
		BiomeGenManager.addEBWarmBiome(jungle, jungleGen);
		BiomeGenManager.addEBFrozenBiome(icePlains, icePlainsGen);
		BiomeGenManager.addEBWarmBiome(birchForest, birchForestGen);
		BiomeGenManager.addEBWarmBiome(roofedForest, roofedForestGen);
		BiomeGenManager.addEBFrozenBiome(coldTaiga, coldTaigaGen);
		BiomeGenManager.addEBCoolBiome(megaTaiga, megaTaigaGen);
		BiomeGenManager.addEBHotBiome(savanna, savannaGen);
		BiomeGenManager.addEBHotBiome(mesa, mesaGen);
		/**/
		
		if(villagePlains == false) {			
			removeVillageBiome(plains); }
		register(plains, Blocks.planks, 0);

		if(villagePlains == true) {			
			addVillageBiome(plains, true); }
		register(plains, Blocks.planks, 0);

		if(villageDesert == false) {			
			removeVillageBiome(desert); }

		if(villageDesert == true) {			
			addVillageBiome(desertHills, true); }
		register(desert, Blocks.planks, 0, false);
		BiomeWoods.register(desertHills, Blocks.planks, 0, false);

		if(villageExtremeHills == true) {			
			addVillageBiome(extremeHills, true); 			
			BiomeManager.addVillageBiome(extremeHillsEdge, true); 			
			addVillageBiome(extremeHillsPlus, true); }
		register(extremeHills, Blocks.planks, 1);
		BiomeWoods.register(extremeHillsEdge, Blocks.planks, 1);
		register(extremeHillsPlus, Blocks.planks, 1);

		if(villageForest == true) {			
			addVillageBiome(forest, true); 			
			BiomeManager.addVillageBiome(forestHills, true); }
		register(forest, Blocks.planks, 0);
		BiomeWoods.register(forestHills, Blocks.planks, 0);

		if(villageTaiga == true) {			
			addVillageBiome(taiga, true); 			
			BiomeManager.addVillageBiome(taigaHills, true); }
		register(taiga, Blocks.planks, 1);
		BiomeWoods.register(taigaHills, Blocks.planks, 1);

		if(villageSwampland == true) {			
			addVillageBiome(swampland, true); }
		register(swampland, Blocks.planks, 0);

		if(villageIcePlains == true) {			
			addVillageBiome(icePlains, true); 			
			BiomeManager.addVillageBiome(iceMountains, true); }
		register(icePlains, Blocks.planks, 1);
		BiomeWoods.register(iceMountains, Blocks.planks, 1);

		if(villageMushroomIsland == true) {			
			BiomeManager.addVillageBiome(mushroomIsland, true);			
			BiomeManager.addVillageBiome(mushroomIslandShore, true); }
		BiomeWoods.register(mushroomIsland, Blocks.planks, 0);
		BiomeWoods.register(mushroomIslandShore, Blocks.planks, 0);

		if(villageJungle == true) {			
			addVillageBiome(jungle, true);
			addVillageBiome(jungleEdge, true);
			BiomeManager.addVillageBiome(jungleHills, true);}
		register(jungle, Blocks.planks, 3);
		register(jungleEdge, Blocks.planks, 3);
		BiomeWoods.register(jungleHills, Blocks.planks, 3);

		if(villageBirchForest == true) {			
			addVillageBiome(birchForest, true); 			
			addVillageBiome(birchForestHills, true); }
		register(birchForest, Blocks.planks, 2);
		register(birchForestHills, Blocks.planks, 2);

		if(villageRoofedForest == true) {			
			addVillageBiome(roofedForest, true); }
		register(roofedForest, Blocks.planks, 5);

		if(villageColdTaiga == true) {			
			addVillageBiome(coldTaiga, true); 			
			BiomeManager.addVillageBiome(coldTaigaHills, true); }
		register(coldTaiga, Blocks.planks, 1);
		BiomeWoods.register(coldTaigaHills, Blocks.planks, 1);

		if(villageMegaTaiga == true) {			
			addVillageBiome(megaTaiga, true); 			
			addVillageBiome(megaTaigaHills, true); }
		register(megaTaiga, Blocks.planks, 1);
		register(megaTaigaHills, Blocks.planks, 1);

		if(villageSavanna == true) {			
			addVillageBiome(savanna, true); 			
			addVillageBiome(savannaPlateau, true); }
		register(savanna, Blocks.planks, 4);
		register(savannaPlateau, Blocks.planks, 4);

		if(villageMesa == true) {			
			addVillageBiome(mesa, true); 			
			addVillageBiome(mesaPlateau, true); 			
			addVillageBiome(mesaPlateau_F, true); }
		register(mesa, Blocks.planks, 0);
		register(mesaPlateau, Blocks.planks, 0);
		register(mesaPlateau_F, Blocks.planks, 0);
	}
	
	public static void register(BiomeGenBase biome, Block planksID, int planksMeta)
	{
		BiomeWoods.register(biome, planksID, planksMeta);
		
		if(biome.biomeID < 128 && EnhancedBiomesMod.woodList[biome.biomeID + 128] == null)
		{
			BiomeWoods.register(biome, planksID, planksMeta);
		}
	}
	
	public static void register(BiomeGenBase biome, Block planksID, int planksMeta, boolean isWoodenVillage)
	{
		EnhancedBiomesMod.woodList[biome.biomeID] = planksID;
		EnhancedBiomesMod.woodMetaList[biome.biomeID] = (byte) planksMeta;
		EnhancedBiomesMod.isStoneVillageList[biome.biomeID] = !isWoodenVillage;
		
		if(biome.biomeID < 128 && EnhancedBiomesMod.woodList[biome.biomeID + 128] == null)
		{
			EnhancedBiomesMod.woodList[biome.biomeID + 128] = planksID;
			EnhancedBiomesMod.woodMetaList[biome.biomeID + 128] = (byte) planksMeta;
			EnhancedBiomesMod.isStoneVillageList[biome.biomeID + 128] = !isWoodenVillage;
		}
	}

    public static void addVillageBiome(BiomeGenBase biome, boolean canSpawn)
    {
    	if (!MapGenVillage.villageSpawnBiomes.contains(biome))
        {
            ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>(MapGenVillage.villageSpawnBiomes);
            biomes.add(biome);
            MapGenVillage.villageSpawnBiomes = biomes;
        }
    	if (biome.biomeID < 128 && BiomeGenBase.getBiome(biome.biomeID + 128) != null && !MapGenVillage.villageSpawnBiomes.contains(BiomeGenBase.getBiome(biome.biomeID + 128)))
        {
            ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>(MapGenVillage.villageSpawnBiomes);
            biomes.add(BiomeGenBase.getBiome(biome.biomeID + 128));
            MapGenVillage.villageSpawnBiomes = biomes;
        }
    }

    public static void removeVillageBiome(BiomeGenBase biome)
    {
        if (MapGenVillage.villageSpawnBiomes.contains(biome))
        {
            ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>(MapGenVillage.villageSpawnBiomes);
            biomes.remove(biome);
            MapGenVillage.villageSpawnBiomes = biomes;
        }
        if (biome.biomeID < 128 && BiomeGenBase.getBiome(biome.biomeID + 128) != null && MapGenVillage.villageSpawnBiomes.contains(BiomeGenBase.getBiome(biome.biomeID + 128)))
        {
            ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>(MapGenVillage.villageSpawnBiomes);
            biomes.remove(BiomeGenBase.getBiome(biome.biomeID + 128));
            MapGenVillage.villageSpawnBiomes = biomes;
        }
    }
}
