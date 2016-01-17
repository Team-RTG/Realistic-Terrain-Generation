package rtg.world.biome.realistic.highlands;

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
		    
		    if (ConfigHL.generateHLBiomes) {
		        
    			BiomeBase.addBiome(hl_Alps);
    			BiomeBase.addBiome(hl_AutumnForest);
    			BiomeBase.addBiome(hl_Badlands);
    			BiomeBase.addBiome(hl_BaldHill);
    			BiomeBase.addBiome(hl_BirchHills);
    			BiomeBase.addBiome(hl_Bog);
    			BiomeBase.addBiome(hl_Canyon);
    			BiomeBase.addBiome(hl_Cliffs);
    			BiomeBase.addBiome(hl_DesertIsland);
    			BiomeBase.addBiome(hl_DesertMountains);
    			BiomeBase.addBiome(hl_Dunes);
    			BiomeBase.addBiome(hl_Estuary);
    			BiomeBase.addBiome(hl_FlyingMountains);
    			BiomeBase.addBiome(hl_ForestIsland);
    			BiomeBase.addBiome(hl_Glacier);
    			BiomeBase.addBiome(hl_HighlandsB);
    			BiomeBase.addBiome(hl_JungleIsland);
    			BiomeBase.addBiome(hl_Lake);
    			BiomeBase.addBiome(hl_Lowlands);
    			BiomeBase.addBiome(hl_Meadow);
    			BiomeBase.addBiome(hl_Mesa);
    			BiomeBase.addBiome(hl_Oasis);
    			BiomeBase.addBiome(hl_Outback);
    			BiomeBase.addBiome(hl_Pinelands);
    			BiomeBase.addBiome(hl_Rainforest);
    			BiomeBase.addBiome(hl_RedwoodForest);
    			BiomeBase.addBiome(hl_RockIsland);
    			BiomeBase.addBiome(hl_RockMountains);
    			BiomeBase.addBiome(hl_Sahel);
    			BiomeBase.addBiome(hl_Savannah);
    			BiomeBase.addBiome(hl_Shrubland);
    			BiomeBase.addBiome(hl_SnowIsland);
    			BiomeBase.addBiome(hl_SnowMountains);
    			BiomeBase.addBiome(hl_Steppe);
    			BiomeBase.addBiome(hl_TallPineForest);
    			BiomeBase.addBiome(hl_TropicalIslands);
    			BiomeBase.addBiome(hl_Tropics);
    			BiomeBase.addBiome(hl_Tundra);
    			BiomeBase.addBiome(hl_Valley);
    			BiomeBase.addBiome(hl_VolcanoIsland);
    			BiomeBase.addBiome(hl_WindyIsland);
    			BiomeBase.addBiome(hl_Woodlands);
    			BiomeBase.addBiome(hl_WoodsMountains);
		    }
		}
	}
}
