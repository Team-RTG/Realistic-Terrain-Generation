package rtg.world.biome.realistic.highlands;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHL;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBase extends RealisticBiomeBase
{	
	//Main biomes
	public static RealisticBiomeBase hl_Alps;
	public static RealisticBiomeBase hl_AutumnForest;
	public static RealisticBiomeBase hl_Badlands;
	public static RealisticBiomeBase hl_BirchHills;
	public static RealisticBiomeBase hl_Bog;
	public static RealisticBiomeBase hl_Cliffs;
	public static RealisticBiomeBase hl_DesertMountains;
	public static RealisticBiomeBase hl_Dunes;
	public static RealisticBiomeBase hl_Estuary;
	public static RealisticBiomeBase hl_FlyingMountains;
	public static RealisticBiomeBase hl_Glacier;
	public static RealisticBiomeBase hl_HighlandsB;
	public static RealisticBiomeBase hl_Lowlands;
	public static RealisticBiomeBase hl_Meadow;
	public static RealisticBiomeBase hl_Outback;
	public static RealisticBiomeBase hl_Pinelands;
	public static RealisticBiomeBase hl_Rainforest;
	public static RealisticBiomeBase hl_RedwoodForest;
	public static RealisticBiomeBase hl_RockMountains;
	public static RealisticBiomeBase hl_Sahel;
	public static RealisticBiomeBase hl_Savannah;
	public static RealisticBiomeBase hl_SnowMountains;
	public static RealisticBiomeBase hl_Steppe;
	public static RealisticBiomeBase hl_TallPineForest;
	public static RealisticBiomeBase hl_TropicalIslands;
	public static RealisticBiomeBase hl_Tropics;
	public static RealisticBiomeBase hl_Tundra;
	public static RealisticBiomeBase hl_Woodlands;
	public static RealisticBiomeBase hl_WoodsMountains;
    
    //Sub biomes
	public static RealisticBiomeBase hl_BaldHill;
	public static RealisticBiomeBase hl_Canyon;
	public static RealisticBiomeBase hl_DesertIsland;
	public static RealisticBiomeBase hl_ForestIsland;
	public static RealisticBiomeBase hl_JungleIsland;
	public static RealisticBiomeBase hl_Lake;
	public static RealisticBiomeBase hl_Mesa;
	public static RealisticBiomeBase hl_Oasis;
	public static RealisticBiomeBase hl_RockIsland;
	public static RealisticBiomeBase hl_SnowIsland;
	public static RealisticBiomeBase hl_Valley;
	public static RealisticBiomeBase hl_VolcanoIsland;
	public static RealisticBiomeBase hl_WindyIsland;
    
    //Border biomes
	public static RealisticBiomeBase hl_Shrubland;
	
	public RealisticBiomeHLBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("Highlands"))
		{
		    hl_Alps = new RealisticBiomeHLAlps(BiomeConfigHL.biomeConfigHLAlps);
		    hl_AutumnForest = new RealisticBiomeHLAutumnForest(BiomeConfigHL.biomeConfigHLAutumnForest);
		    hl_Badlands = new RealisticBiomeHLBadlands(BiomeConfigHL.biomeConfigHLBadlands);
		    hl_BirchHills = new RealisticBiomeHLBirchHills(BiomeConfigHL.biomeConfigHLBirchHills);
		    hl_Bog = new RealisticBiomeHLBog(BiomeConfigHL.biomeConfigHLBog);
		    hl_Cliffs = new RealisticBiomeHLCliffs(BiomeConfigHL.biomeConfigHLCliffs);
		    hl_DesertMountains = new RealisticBiomeHLDesertMountains(BiomeConfigHL.biomeConfigHLDesertMountains);
		    hl_Dunes = new RealisticBiomeHLDunes(BiomeConfigHL.biomeConfigHLDunes);
		    hl_Estuary = new RealisticBiomeHLEstuary(BiomeConfigHL.biomeConfigHLEstuary);
		    hl_FlyingMountains = new RealisticBiomeHLFlyingMountains(BiomeConfigHL.biomeConfigHLFlyingMountains);
		    hl_Glacier = new RealisticBiomeHLGlacier(BiomeConfigHL.biomeConfigHLGlacier);
		    hl_HighlandsB = new RealisticBiomeHLHighlandsB(BiomeConfigHL.biomeConfigHLHighlandsB);
		    hl_Lowlands = new RealisticBiomeHLLowlands(BiomeConfigHL.biomeConfigHLLowlands);
		    hl_Meadow = new RealisticBiomeHLMeadow(BiomeConfigHL.biomeConfigHLMeadow);
		    hl_Outback = new RealisticBiomeHLOutback(BiomeConfigHL.biomeConfigHLOutback);
		    hl_Pinelands = new RealisticBiomeHLPinelands(BiomeConfigHL.biomeConfigHLPinelands);
		    hl_Rainforest = new RealisticBiomeHLRainforest(BiomeConfigHL.biomeConfigHLRainforest);
		    hl_RedwoodForest = new RealisticBiomeHLRedwoodForest(BiomeConfigHL.biomeConfigHLRedwoodForest);
		    hl_RockMountains = new RealisticBiomeHLRockMountains(BiomeConfigHL.biomeConfigHLRockMountains);
		    hl_Sahel = new RealisticBiomeHLSahel(BiomeConfigHL.biomeConfigHLSahel);
		    hl_Savannah = new RealisticBiomeHLSavannah(BiomeConfigHL.biomeConfigHLSavannah);
		    hl_SnowMountains = new RealisticBiomeHLSnowMountains(BiomeConfigHL.biomeConfigHLSnowMountains);
		    hl_Steppe = new RealisticBiomeHLSteppe(BiomeConfigHL.biomeConfigHLSteppe);
		    hl_TallPineForest = new RealisticBiomeHLTallPineForest(BiomeConfigHL.biomeConfigHLTallPineForest);
		    hl_TropicalIslands = new RealisticBiomeHLTropicalIslands(BiomeConfigHL.biomeConfigHLTropicalIslands);
		    hl_Tropics = new RealisticBiomeHLTropics(BiomeConfigHL.biomeConfigHLTropics);
		    hl_Tundra = new RealisticBiomeHLTundra(BiomeConfigHL.biomeConfigHLTundra);
		    hl_Woodlands = new RealisticBiomeHLWoodlands(BiomeConfigHL.biomeConfigHLWoodlands);
		    hl_WoodsMountains = new RealisticBiomeHLWoodsMountains(BiomeConfigHL.biomeConfigHLWoodsMountains);
		    hl_BaldHill = new RealisticBiomeHLBaldHill(BiomeConfigHL.biomeConfigHLBaldHill);
		    hl_Canyon = new RealisticBiomeHLCanyon(BiomeConfigHL.biomeConfigHLCanyon);
		    hl_DesertIsland = new RealisticBiomeHLDesertIsland(BiomeConfigHL.biomeConfigHLDesertIsland);
		    hl_ForestIsland = new RealisticBiomeHLForestIsland(BiomeConfigHL.biomeConfigHLForestIsland);
		    hl_JungleIsland = new RealisticBiomeHLJungleIsland(BiomeConfigHL.biomeConfigHLJungleIsland);
		    hl_Lake = new RealisticBiomeHLLake(BiomeConfigHL.biomeConfigHLLake);
		    hl_Mesa = new RealisticBiomeHLMesa(BiomeConfigHL.biomeConfigHLMesa);
		    hl_Oasis = new RealisticBiomeHLOasis(BiomeConfigHL.biomeConfigHLOasis);
		    hl_RockIsland = new RealisticBiomeHLRockIsland(BiomeConfigHL.biomeConfigHLRockIsland);
		    hl_SnowIsland = new RealisticBiomeHLSnowIsland(BiomeConfigHL.biomeConfigHLSnowIsland);
		    hl_Valley = new RealisticBiomeHLValley(BiomeConfigHL.biomeConfigHLValley);
		    hl_VolcanoIsland = new RealisticBiomeHLVolcanoIsland(BiomeConfigHL.biomeConfigHLVolcanoIsland);
		    hl_WindyIsland = new RealisticBiomeHLWindyIsland(BiomeConfigHL.biomeConfigHLWindyIsland);
		    hl_Shrubland = new RealisticBiomeHLShrubland(BiomeConfigHL.biomeConfigHLShrubland);
		    
			if (ConfigHL.generateHLBiomes && hl_Alps.config._boolean(BiomeConfig.enableBiomeId)) {
				
				BiomeBase.addBiome(hl_Alps);
				BiomeBase.addVillageBiome(hl_Alps);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_AutumnForest.config._boolean(BiomeConfig.enableBiomeId)) {
				BiomeBase.addBiome(hl_AutumnForest);
				BiomeBase.addVillageBiome(hl_AutumnForest);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Badlands.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Badlands);
				BiomeBase.addVillageBiome(hl_Badlands);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_BaldHill.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_BaldHill);
				BiomeBase.addVillageBiome(hl_BaldHill);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_BirchHills.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_BirchHills);
				BiomeBase.addVillageBiome(hl_BirchHills);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Bog.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Bog);
				BiomeBase.addVillageBiome(hl_Bog);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Canyon.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Canyon);
				BiomeBase.addVillageBiome(hl_Canyon);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Cliffs.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Cliffs);
				BiomeBase.addVillageBiome(hl_Cliffs);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_DesertIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_DesertIsland);
				BiomeBase.addVillageBiome(hl_DesertIsland);
			}			
					    
			if (ConfigHL.generateHLBiomes && hl_DesertMountains.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_DesertMountains);
				BiomeBase.addVillageBiome(hl_DesertMountains);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Dunes.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Dunes);
				BiomeBase.addVillageBiome(hl_Dunes);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Estuary.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Estuary);
				BiomeBase.addVillageBiome(hl_Estuary);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_FlyingMountains.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_FlyingMountains);
				BiomeBase.addVillageBiome(hl_FlyingMountains);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_ForestIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_ForestIsland);
				BiomeBase.addVillageBiome(hl_ForestIsland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Glacier.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Glacier);
				BiomeBase.addVillageBiome(hl_Glacier);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_HighlandsB.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_HighlandsB);
				BiomeBase.addVillageBiome(hl_HighlandsB);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_JungleIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_JungleIsland);
				BiomeBase.addVillageBiome(hl_JungleIsland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Lake.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Lake);
				BiomeBase.addVillageBiome(hl_Lake);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Lowlands.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Lowlands);
				BiomeBase.addVillageBiome(hl_Lowlands);
			}

			if (ConfigHL.generateHLBiomes && hl_Meadow.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Meadow);
				BiomeBase.addVillageBiome(hl_Meadow);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Mesa.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Mesa);
				BiomeBase.addVillageBiome(hl_Mesa);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Oasis.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Oasis);
				BiomeBase.addVillageBiome(hl_Oasis);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Outback.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Outback);
				BiomeBase.addVillageBiome(hl_Outback);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Pinelands.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Pinelands);
				BiomeBase.addVillageBiome(hl_Pinelands);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Rainforest.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Rainforest);
				BiomeBase.addVillageBiome(hl_Rainforest);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_RedwoodForest.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_RedwoodForest);
				BiomeBase.addVillageBiome(hl_RedwoodForest);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_RockIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_RockIsland);
				BiomeBase.addVillageBiome(hl_RockIsland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_RockMountains.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_RockMountains);
				BiomeBase.addVillageBiome(hl_RockMountains);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Sahel.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Sahel);
				BiomeBase.addVillageBiome(hl_Sahel);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Savannah.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Savannah);
				BiomeBase.addVillageBiome(hl_Savannah);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Shrubland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Shrubland);
				BiomeBase.addVillageBiome(hl_Shrubland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_SnowIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_SnowIsland);
				BiomeBase.addVillageBiome(hl_SnowIsland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_SnowMountains.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_SnowMountains);
				BiomeBase.addVillageBiome(hl_SnowMountains);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Steppe.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Steppe);
				BiomeBase.addVillageBiome(hl_Steppe);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_TallPineForest.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_TallPineForest);
				BiomeBase.addVillageBiome(hl_TallPineForest);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_TropicalIslands.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_TropicalIslands);
				BiomeBase.addVillageBiome(hl_TropicalIslands);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Tropics.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Tropics);
				BiomeBase.addVillageBiome(hl_Tropics);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Tundra.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Tundra);
				BiomeBase.addVillageBiome(hl_Tundra);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Valley.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Valley);
				BiomeBase.addVillageBiome(hl_Valley);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_VolcanoIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_VolcanoIsland);
				BiomeBase.addVillageBiome(hl_VolcanoIsland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_WindyIsland.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_WindyIsland);
				BiomeBase.addVillageBiome(hl_WindyIsland);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_Woodlands.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_Woodlands);
				BiomeBase.addVillageBiome(hl_Woodlands);
			}
					    
			if (ConfigHL.generateHLBiomes && hl_WoodsMountains.config._boolean(BiomeConfig.enableBiomeId)) {

				BiomeBase.addBiome(hl_WoodsMountains);
				BiomeBase.addVillageBiome(hl_WoodsMountains);
			}
		}
	}
}
