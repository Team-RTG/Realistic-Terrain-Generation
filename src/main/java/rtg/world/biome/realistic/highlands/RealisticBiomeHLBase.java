package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeHLBase extends RealisticBiomeBase
{	
	//Main biomes
	public static RealisticBiomeBase hl_alps;
	public static RealisticBiomeBase hl_autumnForest;
	public static RealisticBiomeBase hl_badlands;
	public static RealisticBiomeBase hl_birchHills;
	public static RealisticBiomeBase hl_bog;
	public static RealisticBiomeBase hl_cliffs;
	public static RealisticBiomeBase hl_desertMountains;
	public static RealisticBiomeBase hl_dunes;
	public static RealisticBiomeBase hl_estuary;
	public static RealisticBiomeBase hl_flyingMountains;
	public static RealisticBiomeBase hl_glacier;
	public static RealisticBiomeBase hl_highlandsb;
	public static RealisticBiomeBase hl_lowlands;
	public static RealisticBiomeBase hl_meadow;
	public static RealisticBiomeBase hl_outback;
	public static RealisticBiomeBase hl_pinelands;
	public static RealisticBiomeBase hl_rainforest;
	public static RealisticBiomeBase hl_redwoodForest;
	public static RealisticBiomeBase hl_rockMountains;
	public static RealisticBiomeBase hl_sahel;
	public static RealisticBiomeBase hl_savannah;
	public static RealisticBiomeBase hl_snowMountains;
	public static RealisticBiomeBase hl_steppe;
	public static RealisticBiomeBase hl_tallPineForest;
	public static RealisticBiomeBase hl_tropicalIslands;
	public static RealisticBiomeBase hl_tropics;
	public static RealisticBiomeBase hl_tundra;
	public static RealisticBiomeBase hl_woodlands;
	public static RealisticBiomeBase hl_woodsMountains;
    
    //Sub biomes
	public static RealisticBiomeBase hl_baldHill;
	public static RealisticBiomeBase hl_canyon;
	public static RealisticBiomeBase hl_desertIsland;
	public static RealisticBiomeBase hl_forestIsland;
	public static RealisticBiomeBase hl_jungleIsland;
	public static RealisticBiomeBase hl_lake;
	public static RealisticBiomeBase hl_mesa;
	public static RealisticBiomeBase hl_oasis;
	public static RealisticBiomeBase hl_rockIsland;
	public static RealisticBiomeBase hl_snowIsland;
	public static RealisticBiomeBase hl_valley;
	public static RealisticBiomeBase hl_volcanoIsland;
	public static RealisticBiomeBase hl_windyIsland;
    
    //Border biomes
	public static RealisticBiomeBase hl_shrubland;
	
	public RealisticBiomeHLBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("Highlands") && ConfigHL.generateHLBiomes)
		{
			if (ConfigHL.generateHLAlps) {
				hl_alps = new RealisticBiomeHLAlps();
				BiomeBase.addBiome(hl_alps);
				BiomeBase.addVillageBiome(hl_alps);
			}
			
			if (ConfigHL.generateHLAutumnForest) {
				hl_autumnForest = new RealisticBiomeHLAutumnForest();
				BiomeBase.addBiome(hl_autumnForest);
				BiomeBase.addVillageBiome(hl_autumnForest);
			}
			
			if (ConfigHL.generateHLBadlands) {
				hl_badlands = new RealisticBiomeHLBadlands();
				BiomeBase.addBiome(hl_badlands);
				BiomeBase.addVillageBiome(hl_badlands);
			}
			
			if (ConfigHL.generateHLBaldHill) {
				hl_baldHill = new RealisticBiomeHLBaldHill();
				BiomeBase.addBiome(hl_baldHill);
				BiomeBase.addVillageBiome(hl_baldHill);
			}
			
			if (ConfigHL.generateHLBirchHills) {
				hl_birchHills = new RealisticBiomeHLBirchHills();
				BiomeBase.addBiome(hl_birchHills);
				BiomeBase.addVillageBiome(hl_birchHills);
			}
			
			if (ConfigHL.generateHLBog) {
				hl_bog = new RealisticBiomeHLBog();
				BiomeBase.addBiome(hl_bog);
				BiomeBase.addVillageBiome(hl_bog);
			}
			
			if (ConfigHL.generateHLCanyon) {
				hl_canyon = new RealisticBiomeHLCanyon();
				BiomeBase.addBiome(hl_canyon);
				BiomeBase.addVillageBiome(hl_canyon);
			}
			
			if (ConfigHL.generateHLCliffs) {
				hl_cliffs = new RealisticBiomeHLCliffs();
				BiomeBase.addBiome(hl_cliffs);
				BiomeBase.addVillageBiome(hl_cliffs);
			}
			
			if (ConfigHL.generateHLDesertIsland) {
				hl_desertIsland = new RealisticBiomeHLDesertIsland();
				BiomeBase.addBiome(hl_desertIsland);
				BiomeBase.addVillageBiome(hl_desertIsland);
			}			
			
			if (ConfigHL.generateHLDesertMountains) {
				hl_desertMountains = new RealisticBiomeHLDesertMountains();
				BiomeBase.addBiome(hl_desertMountains);
				BiomeBase.addVillageBiome(hl_desertMountains);
			}
			
			if (ConfigHL.generateHLDunes) {
				hl_dunes = new RealisticBiomeHLDunes();
				BiomeBase.addBiome(hl_dunes);
				BiomeBase.addVillageBiome(hl_dunes);
			}
			
			if (ConfigHL.generateHLEstuary) {
				hl_estuary = new RealisticBiomeHLEstuary();
				BiomeBase.addBiome(hl_estuary);
				BiomeBase.addVillageBiome(hl_estuary);
			}
			
			if (ConfigHL.generateHLFlyingMountains) {
				hl_flyingMountains = new RealisticBiomeHLFlyingMountains();
				BiomeBase.addBiome(hl_flyingMountains);
				BiomeBase.addVillageBiome(hl_flyingMountains);
			}
			
			if (ConfigHL.generateHLForestIsland) {
				hl_forestIsland = new RealisticBiomeHLForestIsland();
				BiomeBase.addBiome(hl_forestIsland);
				BiomeBase.addVillageBiome(hl_forestIsland);
			}
			
			if (ConfigHL.generateHLGlacier) {
				hl_glacier = new RealisticBiomeHLGlacier();
				BiomeBase.addBiome(hl_glacier);
				BiomeBase.addVillageBiome(hl_glacier);
			}
			
			if (ConfigHL.generateHLHighlandsB) {
				hl_highlandsb = new RealisticBiomeHLHighlandsB();
				BiomeBase.addBiome(hl_highlandsb);
				BiomeBase.addVillageBiome(hl_highlandsb);
			}
			
			if (ConfigHL.generateHLJungleIsland) {
				hl_jungleIsland = new RealisticBiomeHLJungleIsland();
				BiomeBase.addBiome(hl_jungleIsland);
				BiomeBase.addVillageBiome(hl_jungleIsland);
			}
			
			if (ConfigHL.generateHLLake) {
				hl_lake = new RealisticBiomeHLLake();
				BiomeBase.addBiome(hl_lake);
				BiomeBase.addVillageBiome(hl_lake);
			}
			
			if (ConfigHL.generateHLLowlands) {
				hl_lowlands = new RealisticBiomeHLLowlands();
				BiomeBase.addBiome(hl_lowlands);
				BiomeBase.addVillageBiome(hl_lowlands);
			}
			
			if (ConfigHL.generateHLMeadow) {
				hl_meadow = new RealisticBiomeHLMeadow();
				BiomeBase.addBiome(hl_meadow);
				BiomeBase.addVillageBiome(hl_meadow);
			}
			
			if (ConfigHL.generateHLMesa) {
				hl_mesa = new RealisticBiomeHLMesa();
				BiomeBase.addBiome(hl_mesa);
				BiomeBase.addVillageBiome(hl_mesa);
			}
			
			if (ConfigHL.generateHLOasis) {
				hl_oasis = new RealisticBiomeHLOasis();
				BiomeBase.addBiome(hl_oasis);
				BiomeBase.addVillageBiome(hl_oasis);
			}
			
			if (ConfigHL.generateHLOutback) {
				hl_outback = new RealisticBiomeHLOutback();
				BiomeBase.addBiome(hl_outback);
				BiomeBase.addVillageBiome(hl_outback);
			}
			
			if (ConfigHL.generateHLPinelands) {
				hl_pinelands = new RealisticBiomeHLPinelands();
				BiomeBase.addBiome(hl_pinelands);
				BiomeBase.addVillageBiome(hl_pinelands);
			}
			
			if (ConfigHL.generateHLRainforest) {
				hl_rainforest = new RealisticBiomeHLRainforest();
				BiomeBase.addBiome(hl_rainforest);
				BiomeBase.addVillageBiome(hl_rainforest);
			}
			
			if (ConfigHL.generateHLRedwoodForest) {
				hl_redwoodForest = new RealisticBiomeHLRedwoodForest();
				BiomeBase.addBiome(hl_redwoodForest);
				BiomeBase.addVillageBiome(hl_redwoodForest);
			}
			
			if (ConfigHL.generateHLRockIsland) {
				hl_rockIsland = new RealisticBiomeHLRockIsland();
				BiomeBase.addBiome(hl_rockIsland);
				BiomeBase.addVillageBiome(hl_rockIsland);
			}
			
			if (ConfigHL.generateHLRockMountains) {
				hl_rockMountains = new RealisticBiomeHLRockMountains();
				BiomeBase.addBiome(hl_rockMountains);
				BiomeBase.addVillageBiome(hl_rockMountains);
			}
			
			if (ConfigHL.generateHLSahel) {
				hl_sahel = new RealisticBiomeHLSahel();
				BiomeBase.addBiome(hl_sahel);
				BiomeBase.addVillageBiome(hl_sahel);
			}
			
			if (ConfigHL.generateHLSavannah) {
				hl_savannah = new RealisticBiomeHLSavannah();
				BiomeBase.addBiome(hl_savannah);
				BiomeBase.addVillageBiome(hl_savannah);
			}
			
			if (ConfigHL.generateHLShrubland) {
				hl_shrubland = new RealisticBiomeHLShrubland();
				BiomeBase.addBiome(hl_shrubland);
				BiomeBase.addVillageBiome(hl_shrubland);
			}
			
			if (ConfigHL.generateHLSnowIsland) {
				hl_snowIsland = new RealisticBiomeHLSnowIsland();
				BiomeBase.addBiome(hl_snowIsland);
				BiomeBase.addVillageBiome(hl_snowIsland);
			}
			
			if (ConfigHL.generateHLSnowMountains) {
				hl_snowMountains = new RealisticBiomeHLSnowMountains();
				BiomeBase.addBiome(hl_snowMountains);
				BiomeBase.addVillageBiome(hl_snowMountains);
			}
			
			if (ConfigHL.generateHLSteppe) {
				hl_steppe = new RealisticBiomeHLSteppe();
				BiomeBase.addBiome(hl_steppe);
				BiomeBase.addVillageBiome(hl_steppe);
			}
			
			if (ConfigHL.generateHLTallPineForest) {
				hl_tallPineForest = new RealisticBiomeHLTallPineForest();
				BiomeBase.addBiome(hl_tallPineForest);
				BiomeBase.addVillageBiome(hl_tallPineForest);
			}
			
			if (ConfigHL.generateHLTropicalIslands) {
				hl_tropicalIslands = new RealisticBiomeHLTropicalIslands();
				BiomeBase.addBiome(hl_tropicalIslands);
				BiomeBase.addVillageBiome(hl_tropicalIslands);
			}
			
			if (ConfigHL.generateHLTropics) {
				hl_tropics = new RealisticBiomeHLTropics();
				BiomeBase.addBiome(hl_tropics);
				BiomeBase.addVillageBiome(hl_tropics);
			}
			
			if (ConfigHL.generateHLTundra) {
				hl_tundra = new RealisticBiomeHLTundra();
				BiomeBase.addBiome(hl_tundra);
				BiomeBase.addVillageBiome(hl_tundra);
			}
			
			if (ConfigHL.generateHLValley) {
				hl_valley = new RealisticBiomeHLValley();
				BiomeBase.addBiome(hl_valley);
				BiomeBase.addVillageBiome(hl_valley);
			}
			
			if (ConfigHL.generateHLVolcanoIsland) {
				hl_volcanoIsland = new RealisticBiomeHLVolcanoIsland();
				BiomeBase.addBiome(hl_volcanoIsland);
				BiomeBase.addVillageBiome(hl_volcanoIsland);
			}
			
			if (ConfigHL.generateHLWindyIsland) {
				hl_windyIsland = new RealisticBiomeHLWindyIsland();
				BiomeBase.addBiome(hl_windyIsland);
				BiomeBase.addVillageBiome(hl_windyIsland);
			}
			
			if (ConfigHL.generateHLWoodlands) {
				hl_woodlands = new RealisticBiomeHLWoodlands();
				BiomeBase.addBiome(hl_woodlands);
				BiomeBase.addVillageBiome(hl_woodlands);
			}
			
			if (ConfigHL.generateHLWoodsMountains) {
				hl_woodsMountains = new RealisticBiomeHLWoodsMountains();
				BiomeBase.addBiome(hl_woodsMountains);
				BiomeBase.addVillageBiome(hl_woodsMountains);
			}
		}
	}
}
