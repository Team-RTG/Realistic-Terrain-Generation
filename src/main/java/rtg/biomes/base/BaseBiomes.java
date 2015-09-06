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
		RTGBiomes.baseRiverIce = new BaseBiomeRiver(ConfigRTG.biomeIDs[0], 0, "rtg_riverIce");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverIce, Type.RIVER, Type.COLD, Type.SNOWY);
		
		RTGBiomes.baseRiverCold = new BaseBiomeRiver(ConfigRTG.biomeIDs[1], 1, "rtg_riverCold");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverCold, Type.RIVER, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseRiverTemperate = new BaseBiomeRiver(ConfigRTG.biomeIDs[2], 2, "rtg_riverTemperate");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverTemperate,	Type.RIVER, Type.COLD, Type.FOREST);
		
		RTGBiomes.baseRiverHot = new BaseBiomeRiver(ConfigRTG.biomeIDs[3], 3, "rtg_riverHot");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverHot, Type.RIVER, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseRiverWet = new BaseBiomeRiver(ConfigRTG.biomeIDs[4], 4, "rtg_riverWet");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverWet, Type.RIVER, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseRiverOasis = new BaseBiomeRiver(ConfigRTG.biomeIDs[5], 5, "rtg_riverOasis");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverOasis, Type.RIVER, Type.HOT, Type.WET, Type.JUNGLE);
		
		
		//OCEAN
		RTGBiomes.baseOceanIce = new BaseBiomeOcean(ConfigRTG.biomeIDs[6], 0, "rtg_oceanIce");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanIce, Type.OCEAN, Type.BEACH, Type.COLD, Type.SNOWY);
		
		RTGBiomes.baseOceanCold = new BaseBiomeOcean(ConfigRTG.biomeIDs[7], 1, "rtg_oceanCold");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanCold, Type.OCEAN, Type.BEACH, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseOceanTemperate = new BaseBiomeOcean(ConfigRTG.biomeIDs[8], 2, "rtg_oceanTemperate");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanTemperate,	Type.OCEAN, Type.BEACH, Type.COLD, Type.FOREST);
		
		RTGBiomes.baseOceanHot = new BaseBiomeOcean(ConfigRTG.biomeIDs[9], 3, "rtg_oceanHot");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanHot, Type.OCEAN, Type.BEACH, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseOceanWet = new BaseBiomeOcean(ConfigRTG.biomeIDs[10], 4, "rtg_oceanWet");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanWet, Type.OCEAN, Type.BEACH, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseOceanOasis = new BaseBiomeOcean(ConfigRTG.biomeIDs[11], 5, "rtg_oceanOasis");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanOasis, Type.OCEAN, Type.BEACH, Type.HOT, Type.WET, Type.JUNGLE);
		
		
		//LAND
		RTGBiomes.baseSnowDesert = new BaseBiomeSnowDesert(ConfigRTG.biomeIDs[12], "rtg_snowDesert");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseSnowDesert, Type.COLD, Type.SNOWY, Type.WASTELAND);
		
		RTGBiomes.baseSnowForest = new BaseBiomeSnowForest(ConfigRTG.biomeIDs[13], "rtg_snowForest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseSnowForest, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseColdPlains = new BaseBiomeColdPlains(ConfigRTG.biomeIDs[14], "rtg_coldPlains");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseColdPlains, Type.COLD, Type.WASTELAND);
		
		RTGBiomes.baseColdForest = new BaseBiomeColdForest(ConfigRTG.biomeIDs[15], "rtg_coldForest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseColdForest, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseHotPlains = new BaseBiomeHotPlains(ConfigRTG.biomeIDs[16], "rtg_hotPlains");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotPlains, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		RTGBiomes.baseHotForest = new BaseBiomeHotForest(ConfigRTG.biomeIDs[17], "rtg_hotForest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotForest, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		RTGBiomes.baseHotDesert = new BaseBiomeHotDesert(ConfigRTG.biomeIDs[18], "rtg_hotDesert");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotDesert, Type.HOT, Type.DRY, Type.SANDY );
		
		RTGBiomes.basePlains = new BaseBiomePlains(ConfigRTG.biomeIDs[19], "rtg_plains");
		BiomeDictionary.registerBiomeType(RTGBiomes.basePlains, Type.PLAINS);
		
		RTGBiomes.baseTropicalIsland = new BaseBiomeTropicalIsland(ConfigRTG.biomeIDs[20], "rtg_tropical");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseTropicalIsland, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseRedwood = new BaseBiomeRedwood(ConfigRTG.biomeIDs[21], "rtg_redwood");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRedwood, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseJungle = new BaseBiomeJungle(ConfigRTG.biomeIDs[22], "rtg_jungle");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseJungle, Type.HOT, Type.WET, Type.JUNGLE);		
		
		RTGBiomes.baseOasis = new BaseBiomeHotDesert(ConfigRTG.biomeIDs[23], "rtg_oasis");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOasis, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseTemperateForest = new BaseBiomeTemperateForest(ConfigRTG.biomeIDs[24], "rtg_temperateForest");
		BiomeDictionary.registerBiomeType(RTGBiomes.baseTemperateForest, Type.FOREST, Type.WET);
	}
}
