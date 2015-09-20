package rtg.biomes.base;

import cpw.mods.fml.common.registry.LanguageRegistry;
import rtg.api.RTGBiomes;
import rtg.config.ConfigRTG;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BaseBiomes 
{
	private static final LanguageRegistry langReg = LanguageRegistry.instance();
	
	public static void load()
	{
		//RIVER
		RTGBiomes.baseRiverIce = new BaseBiomeRiver(ConfigRTG.biomeIDs[0], 0, langReg.getStringLocalization("rtg.biomes.base.baseRiverIce"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverIce, Type.RIVER, Type.COLD, Type.SNOWY);
		
		RTGBiomes.baseRiverCold = new BaseBiomeRiver(ConfigRTG.biomeIDs[1], 1, langReg.getStringLocalization("rtg.biomes.base.baseRiverCold"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverCold, Type.RIVER, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseRiverTemperate = new BaseBiomeRiver(ConfigRTG.biomeIDs[2], 2, langReg.getStringLocalization("rtg.biomes.base.baseRiverTemperate"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverTemperate,	Type.RIVER, Type.COLD, Type.FOREST);
		
		RTGBiomes.baseRiverHot = new BaseBiomeRiver(ConfigRTG.biomeIDs[3], 3, langReg.getStringLocalization("rtg.biomes.base.baseRiverHot"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverHot, Type.RIVER, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseRiverWet = new BaseBiomeRiver(ConfigRTG.biomeIDs[4], 4, langReg.getStringLocalization("rtg.biomes.base.baseRiverWet"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverWet, Type.RIVER, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseRiverOasis = new BaseBiomeRiver(ConfigRTG.biomeIDs[5], 5, langReg.getStringLocalization("rtg.biomes.base.baseRiverOasis"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRiverOasis, Type.RIVER, Type.HOT, Type.WET, Type.JUNGLE);
		
		
		//OCEAN
		RTGBiomes.baseOceanIce = new BaseBiomeOcean(ConfigRTG.biomeIDs[6], 0, langReg.getStringLocalization("rtg.biomes.base.baseOceanIce"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanIce, Type.OCEAN, Type.BEACH, Type.COLD, Type.SNOWY);
		
		RTGBiomes.baseOceanCold = new BaseBiomeOcean(ConfigRTG.biomeIDs[7], 1, langReg.getStringLocalization("rtg.biomes.base.baseOceanCold"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanCold, Type.OCEAN, Type.BEACH, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseOceanTemperate = new BaseBiomeOcean(ConfigRTG.biomeIDs[8], 2, langReg.getStringLocalization("rtg.biomes.base.baseOceanTemperate"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanTemperate,	Type.OCEAN, Type.BEACH, Type.COLD, Type.FOREST);
		
		RTGBiomes.baseOceanHot = new BaseBiomeOcean(ConfigRTG.biomeIDs[9], 3, langReg.getStringLocalization("rtg.biomes.base.baseOceanHot"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanHot, Type.OCEAN, Type.BEACH, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseOceanWet = new BaseBiomeOcean(ConfigRTG.biomeIDs[10], 4, langReg.getStringLocalization("rtg.biomes.base.baseOceanWet"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanWet, Type.OCEAN, Type.BEACH, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseOceanOasis = new BaseBiomeOcean(ConfigRTG.biomeIDs[11], 5, langReg.getStringLocalization("rtg.biomes.base.baseOceanOasis"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOceanOasis, Type.OCEAN, Type.BEACH, Type.HOT, Type.WET, Type.JUNGLE);
		
		
		//LAND
		RTGBiomes.baseSnowDesert = new BaseBiomeSnowDesert(ConfigRTG.biomeIDs[12], langReg.getStringLocalization("rtg.biomes.base.baseSnowDesert"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseSnowDesert, Type.COLD, Type.SNOWY, Type.WASTELAND);
		
		RTGBiomes.baseSnowForest = new BaseBiomeSnowForest(ConfigRTG.biomeIDs[13], langReg.getStringLocalization("rtg.biomes.base.baseSnowForest"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseSnowForest, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseColdPlains = new BaseBiomeColdPlains(ConfigRTG.biomeIDs[14], langReg.getStringLocalization("rtg.biomes.base.baseColdPlains"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseColdPlains, Type.COLD, Type.WASTELAND);
		
		RTGBiomes.baseColdForest = new BaseBiomeColdForest(ConfigRTG.biomeIDs[15], langReg.getStringLocalization("rtg.biomes.base.baseColdForest"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseColdForest, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseHotPlains = new BaseBiomeHotPlains(ConfigRTG.biomeIDs[16], langReg.getStringLocalization("rtg.biomes.base.baseHotPlains"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotPlains, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		RTGBiomes.baseHotForest = new BaseBiomeHotForest(ConfigRTG.biomeIDs[17], langReg.getStringLocalization("rtg.biomes.base.baseHotForest"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotForest, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		RTGBiomes.baseHotDesert = new BaseBiomeHotDesert(ConfigRTG.biomeIDs[18], langReg.getStringLocalization("rtg.biomes.base.baseHotDesert"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseHotDesert, Type.HOT, Type.DRY, Type.SANDY );
		
		RTGBiomes.basePlains = new BaseBiomePlains(ConfigRTG.biomeIDs[19], langReg.getStringLocalization("rtg.biomes.base.basePlains"));
		BiomeDictionary.registerBiomeType(RTGBiomes.basePlains, Type.PLAINS);
		
		RTGBiomes.baseTropicalIsland = new BaseBiomeTropicalIsland(ConfigRTG.biomeIDs[20], langReg.getStringLocalization("rtg.biomes.base.baseTropicalIsland"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseTropicalIsland, Type.HOT, Type.WET, Type.JUNGLE);
		
		RTGBiomes.baseRedwood = new BaseBiomeRedwood(ConfigRTG.biomeIDs[21], langReg.getStringLocalization("rtg.biomes.base.baseRedwood"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseRedwood, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		
		RTGBiomes.baseJungle = new BaseBiomeJungle(ConfigRTG.biomeIDs[22], langReg.getStringLocalization("rtg.biomes.base.baseJungle"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseJungle, Type.HOT, Type.WET, Type.JUNGLE);		
		
		RTGBiomes.baseOasis = new BaseBiomeHotDesert(ConfigRTG.biomeIDs[23], langReg.getStringLocalization("rtg.biomes.base.baseOasis"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseOasis, Type.HOT, Type.DRY, Type.SANDY);
		
		RTGBiomes.baseTemperateForest = new BaseBiomeTemperateForest(ConfigRTG.biomeIDs[24], langReg.getStringLocalization("rtg.biomes.base.baseTemperateForest"));
		BiomeDictionary.registerBiomeType(RTGBiomes.baseTemperateForest, Type.FOREST, Type.WET);
	}
}
