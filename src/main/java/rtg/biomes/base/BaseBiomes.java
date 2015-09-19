package rtg.biomes.base;

import rtg.api.RTGBiomes;
import rtg.config.ConfigRTG;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BaseBiomes 
{
	public static void load()
	{
		//RIVER
		RTGBiomes.baseRiverIce = new BaseBiomeRiver(ConfigRTG.biomeIDs[0], 0, "Ice River");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverIce, Type.RIVER, Type.COLD, Type.SNOWY);
		
		RTGBiomes.baseRiverCold = new BaseBiomeRiver(ConfigRTG.biomeIDs[1], 1, "Cold River");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverCold, Type.RIVER, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseRiverTemperate = new BaseBiomeRiver(ConfigRTG.biomeIDs[2], 2, "Temperate River");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverTemperate,	Type.RIVER, Type.COLD, Type.FOREST);
		
		RTGBiomes.baseRiverHot = new BaseBiomeRiver(ConfigRTG.biomeIDs[3], 3, "Hot River");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverHot, Type.RIVER, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseRiverWet = new BaseBiomeRiver(ConfigRTG.biomeIDs[4], 4, "Wet River");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverWet, Type.RIVER, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseRiverOasis = new BaseBiomeRiver(ConfigRTG.biomeIDs[5], 5, "Oasis River");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverOasis, Type.RIVER, Type.HOT, Type.WET, Type.JUNGLE);
		
		
		//OCEAN
		RTGBiomes.baseOceanIce = new BaseBiomeOcean(ConfigRTG.biomeIDs[6], 0, "Ice Ocean");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanIce, Type.OCEAN, Type.BEACH, Type.COLD, Type.SNOWY);
		
		RTGBiomes.baseOceanCold = new BaseBiomeOcean(ConfigRTG.biomeIDs[7], 1, "Cold Ocean");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanCold, Type.OCEAN, Type.BEACH, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseOceanTemperate = new BaseBiomeOcean(ConfigRTG.biomeIDs[8], 2, "Temperate Ocean");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanTemperate,	Type.OCEAN, Type.BEACH, Type.COLD, Type.FOREST);
		
		RTGBiomes.baseOceanHot = new BaseBiomeOcean(ConfigRTG.biomeIDs[9], 3, "Hot Ocean");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanHot, Type.OCEAN, Type.BEACH, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseOceanWet = new BaseBiomeOcean(ConfigRTG.biomeIDs[10], 4, "Wet Ocean");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanWet, Type.OCEAN, Type.BEACH, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseOceanOasis = new BaseBiomeOcean(ConfigRTG.biomeIDs[11], 5, "Oasis Ocean");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanOasis, Type.OCEAN, Type.BEACH, Type.HOT, Type.WET, Type.JUNGLE);
		
		
		//LAND
		RTGBiomes.baseSnowDesert = new BaseBiomeSnowDesert(ConfigRTG.biomeIDs[12], "Snow Desert");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseSnowDesert, Type.COLD, Type.SNOWY, Type.WASTELAND);
		
		RTGBiomes.baseSnowForest = new BaseBiomeSnowForest(ConfigRTG.biomeIDs[13], "Snow Forest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseSnowForest, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseColdPlains = new BaseBiomeColdPlains(ConfigRTG.biomeIDs[14], "Cold Plains");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseColdPlains, Type.COLD, Type.WASTELAND);
		
		RTGBiomes.baseColdForest = new BaseBiomeColdForest(ConfigRTG.biomeIDs[15], "Cold Forest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseColdForest, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseHotPlains = new BaseBiomeHotPlains(ConfigRTG.biomeIDs[16], "Hot Plains");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotPlains, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		RTGBiomes.baseHotForest = new BaseBiomeHotForest(ConfigRTG.biomeIDs[17], " Hot Forest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotForest, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		RTGBiomes.baseHotDesert = new BaseBiomeHotDesert(ConfigRTG.biomeIDs[18], "Hot Desert");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotDesert, Type.HOT, Type.DRY, Type.SANDY );
		
		RTGBiomes.basePlains = new BaseBiomePlains(ConfigRTG.biomeIDs[19], "Temperate Plains");
		BiomeDictionary.registerBiomeType(RTGBiomes.basePlains, Type.PLAINS);
		
		RTGBiomes.baseTropicalIsland = new BaseBiomeTropicalIsland(ConfigRTG.biomeIDs[20], "Hot Tropical Island");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseTropicalIsland, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseRedwood = new BaseBiomeRedwood(ConfigRTG.biomeIDs[21], "Cold Redwood Forest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRedwood, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseJungle = new BaseBiomeJungle(ConfigRTG.biomeIDs[22], "Hot Jungle");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseJungle, Type.HOT, Type.WET, Type.JUNGLE);		
		
		RTGBiomes.baseOasis = new BaseBiomeHotDesert(ConfigRTG.biomeIDs[23], "Hot Oasis");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOasis, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseTemperateForest = new BaseBiomeTemperateForest(ConfigRTG.biomeIDs[24], "Temperate Forest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseTemperateForest, Type.FOREST, Type.WET);
	}
}
