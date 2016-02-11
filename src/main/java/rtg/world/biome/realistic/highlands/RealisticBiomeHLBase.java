package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.highlands.config.BiomeConfigHL;
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
		    if (HighlandsBiomes.alps!=null) hl_Alps = new RealisticBiomeHLAlps(BiomeConfigHL.biomeConfigHLAlps);
		    if (HighlandsBiomes.autumnForest!=null) hl_AutumnForest = new RealisticBiomeHLAutumnForest(BiomeConfigHL.biomeConfigHLAutumnForest);
		    if (HighlandsBiomes.badlands!=null) hl_Badlands = new RealisticBiomeHLBadlands(BiomeConfigHL.biomeConfigHLBadlands);
		    if (HighlandsBiomes.birchHills!=null) hl_BirchHills = new RealisticBiomeHLBirchHills(BiomeConfigHL.biomeConfigHLBirchHills);
		    if (HighlandsBiomes.bog!=null) hl_Bog = new RealisticBiomeHLBog(BiomeConfigHL.biomeConfigHLBog);
		    if (HighlandsBiomes.cliffs!=null) hl_Cliffs = new RealisticBiomeHLCliffs(BiomeConfigHL.biomeConfigHLCliffs);
		    if (HighlandsBiomes.desertMountains!=null) hl_DesertMountains = new RealisticBiomeHLDesertMountains(BiomeConfigHL.biomeConfigHLDesertMountains);
		    if (HighlandsBiomes.dunes!=null) hl_Dunes = new RealisticBiomeHLDunes(BiomeConfigHL.biomeConfigHLDunes);
		    if (HighlandsBiomes.estuary!=null) hl_Estuary = new RealisticBiomeHLEstuary(BiomeConfigHL.biomeConfigHLEstuary);
		    if (HighlandsBiomes.flyingMountains!=null) hl_FlyingMountains = new RealisticBiomeHLFlyingMountains(BiomeConfigHL.biomeConfigHLFlyingMountains);
		    if (HighlandsBiomes.glacier!=null) hl_Glacier = new RealisticBiomeHLGlacier(BiomeConfigHL.biomeConfigHLGlacier);
		    if (HighlandsBiomes.highlandsb!=null) hl_HighlandsB = new RealisticBiomeHLHighlandsB(BiomeConfigHL.biomeConfigHLHighlandsB);
		    if (HighlandsBiomes.lowlands!=null) hl_Lowlands = new RealisticBiomeHLLowlands(BiomeConfigHL.biomeConfigHLLowlands);
		    if (HighlandsBiomes.meadow!=null) hl_Meadow = new RealisticBiomeHLMeadow(BiomeConfigHL.biomeConfigHLMeadow);
		    if (HighlandsBiomes.outback!=null) hl_Outback = new RealisticBiomeHLOutback(BiomeConfigHL.biomeConfigHLOutback);
		    if (HighlandsBiomes.pinelands!=null) hl_Pinelands = new RealisticBiomeHLPinelands(BiomeConfigHL.biomeConfigHLPinelands);
		    if (HighlandsBiomes.rainforest!=null) hl_Rainforest = new RealisticBiomeHLRainforest(BiomeConfigHL.biomeConfigHLRainforest);
		    if (HighlandsBiomes.redwoodForest!=null) hl_RedwoodForest = new RealisticBiomeHLRedwoodForest(BiomeConfigHL.biomeConfigHLRedwoodForest);
		    if (HighlandsBiomes.rockMountains!=null) hl_RockMountains = new RealisticBiomeHLRockMountains(BiomeConfigHL.biomeConfigHLRockMountains);
		    if (HighlandsBiomes.sahel!=null) hl_Sahel = new RealisticBiomeHLSahel(BiomeConfigHL.biomeConfigHLSahel);
		    if (HighlandsBiomes.savannah!=null) hl_Savannah = new RealisticBiomeHLSavannah(BiomeConfigHL.biomeConfigHLSavannah);
		    if (HighlandsBiomes.snowMountains!=null) hl_SnowMountains = new RealisticBiomeHLSnowMountains(BiomeConfigHL.biomeConfigHLSnowMountains);
		    if (HighlandsBiomes.steppe!=null) hl_Steppe = new RealisticBiomeHLSteppe(BiomeConfigHL.biomeConfigHLSteppe);
		    if (HighlandsBiomes.tallPineForest!=null) hl_TallPineForest = new RealisticBiomeHLTallPineForest(BiomeConfigHL.biomeConfigHLTallPineForest);
		    if (HighlandsBiomes.tropicalIslands!=null) hl_TropicalIslands = new RealisticBiomeHLTropicalIslands(BiomeConfigHL.biomeConfigHLTropicalIslands);
		    if (HighlandsBiomes.tropics!=null) hl_Tropics = new RealisticBiomeHLTropics(BiomeConfigHL.biomeConfigHLTropics);
		    if (HighlandsBiomes.tundra!=null) hl_Tundra = new RealisticBiomeHLTundra(BiomeConfigHL.biomeConfigHLTundra);
		    if (HighlandsBiomes.woodlands!=null) hl_Woodlands = new RealisticBiomeHLWoodlands(BiomeConfigHL.biomeConfigHLWoodlands);
		    if (HighlandsBiomes.woodsMountains!=null) hl_WoodsMountains = new RealisticBiomeHLWoodsMountains(BiomeConfigHL.biomeConfigHLWoodsMountains);
		    if (HighlandsBiomes.baldHill!=null) hl_BaldHill = new RealisticBiomeHLBaldHill(BiomeConfigHL.biomeConfigHLBaldHill);
		    if (HighlandsBiomes.canyon!=null) hl_Canyon = new RealisticBiomeHLCanyon(BiomeConfigHL.biomeConfigHLCanyon);
		    if (HighlandsBiomes.desertIsland!=null) hl_DesertIsland = new RealisticBiomeHLDesertIsland(BiomeConfigHL.biomeConfigHLDesertIsland);
		    if (HighlandsBiomes.forestIsland!=null) hl_ForestIsland = new RealisticBiomeHLForestIsland(BiomeConfigHL.biomeConfigHLForestIsland);
		    if (HighlandsBiomes.jungleIsland!=null) hl_JungleIsland = new RealisticBiomeHLJungleIsland(BiomeConfigHL.biomeConfigHLJungleIsland);
		    if (HighlandsBiomes.lake!=null) hl_Lake = new RealisticBiomeHLLake(BiomeConfigHL.biomeConfigHLLake);
		    if (HighlandsBiomes.mesa!=null) hl_Mesa = new RealisticBiomeHLMesa(BiomeConfigHL.biomeConfigHLMesa);
		    if (HighlandsBiomes.oasis!=null) hl_Oasis = new RealisticBiomeHLOasis(BiomeConfigHL.biomeConfigHLOasis);
		    if (HighlandsBiomes.rockIsland!=null) hl_RockIsland = new RealisticBiomeHLRockIsland(BiomeConfigHL.biomeConfigHLRockIsland);
		    if (HighlandsBiomes.snowIsland!=null) hl_SnowIsland = new RealisticBiomeHLSnowIsland(BiomeConfigHL.biomeConfigHLSnowIsland);
		    if (HighlandsBiomes.valley!=null) hl_Valley = new RealisticBiomeHLValley(BiomeConfigHL.biomeConfigHLValley);
		    if (HighlandsBiomes.volcanoIsland!=null) hl_VolcanoIsland = new RealisticBiomeHLVolcanoIsland(BiomeConfigHL.biomeConfigHLVolcanoIsland);
		    if (HighlandsBiomes.windyIsland!=null) hl_WindyIsland = new RealisticBiomeHLWindyIsland(BiomeConfigHL.biomeConfigHLWindyIsland);
		    if (HighlandsBiomes.shrubland!=null) hl_Shrubland = new RealisticBiomeHLShrubland(BiomeConfigHL.biomeConfigHLShrubland);
		}
	}
}
