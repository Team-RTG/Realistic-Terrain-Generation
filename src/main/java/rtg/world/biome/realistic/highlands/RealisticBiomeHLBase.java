package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigHL;
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
	//public static RealisticBiomeBase hl_everglades; // Not sure why, but this was already commented out. - Pink
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
    
    //Improved ocean biome
	public static RealisticBiomeBase hl_ocean2;
    
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
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("Highlands") && ConfigHL.generateHLBiomes)
		{
			if (ConfigHL.generateHL_alps) {
				hl_alps = new RealisticBiomeHLAlps();
				BiomeBase.addBiome(hl_alps);
			}
			
			if (ConfigHL.generateHL_autumnForest) {
				hl_autumnForest = new RealisticBiomeHLAutumnForest();
				BiomeBase.addBiome(hl_autumnForest);
			}
			
			if (ConfigHL.generateHL_badlands) {
				hl_badlands = new RealisticBiomeHLBadlands();
				BiomeBase.addBiome(hl_badlands);
			}
			
			if (ConfigHL.generateHL_baldHill) {
				hl_baldHill = new RealisticBiomeHLBaldHill();
				BiomeBase.addBiome(hl_baldHill);
			}
			
			if (ConfigHL.generateHL_birchHills) {
				hl_birchHills = new RealisticBiomeHLBirchHills();
				BiomeBase.addBiome(hl_birchHills);
			}
			
			if (ConfigHL.generateHL_bog) {
				hl_bog = new RealisticBiomeHLBog();
				BiomeBase.addBiome(hl_bog);
			}
			
			if (ConfigHL.generateHL_canyon) {
				hl_canyon = new RealisticBiomeHLCanyon();
				BiomeBase.addBiome(hl_canyon);
			}
			
			if (ConfigHL.generateHL_cliffs) {
				hl_cliffs = new RealisticBiomeHLCliffs();
				BiomeBase.addBiome(hl_cliffs);
			}
			
			if (ConfigHL.generateHL_desertIsland) {
				hl_desertIsland = new RealisticBiomeHLDesertIsland();
				BiomeBase.addBiome(hl_desertIsland);
			}			
			
			if (ConfigHL.generateHL_desertMountains) {
				hl_desertMountains = new RealisticBiomeHLDesertMountains();
				BiomeBase.addBiome(hl_desertMountains);
			}
			
			if (ConfigHL.generateHL_dunes) {
				hl_dunes = new RealisticBiomeHLDunes();
				BiomeBase.addBiome(hl_dunes);
			}
			
			if (ConfigHL.generateHL_estuary) {
				hl_estuary = new RealisticBiomeHLEstuary();
				BiomeBase.addBiome(hl_estuary);
			}
			
			if (ConfigHL.generateHL_flyingMountains) {
				hl_flyingMountains = new RealisticBiomeHLFlyingMountains();
				BiomeBase.addBiome(hl_flyingMountains);
			}
			
			if (ConfigHL.generateHL_forestIsland) {
				hl_forestIsland = new RealisticBiomeHLForestIsland();
				BiomeBase.addBiome(hl_forestIsland);
			}
			
			if (ConfigHL.generateHL_glacier) {
				hl_glacier = new RealisticBiomeHLGlacier();
				BiomeBase.addBiome(hl_glacier);
			}
			
			if (ConfigHL.generateHL_highlandsb) {
				hl_highlandsb = new RealisticBiomeHLHighlandsB();
				BiomeBase.addBiome(hl_highlandsb);
			}
			
			if (ConfigHL.generateHL_jungleIsland) {
				hl_jungleIsland = new RealisticBiomeHLJungleIsland();
				BiomeBase.addBiome(hl_jungleIsland);
			}
			
			if (ConfigHL.generateHL_lake) {
				hl_lake = new RealisticBiomeHLLake();
				BiomeBase.addBiome(hl_lake);
			}
			
			if (ConfigHL.generateHL_lowlands) {
				hl_lowlands = new RealisticBiomeHLLowlands();
				BiomeBase.addBiome(hl_lowlands);
			}
			
			if (ConfigHL.generateHL_meadow) {
				hl_meadow = new RealisticBiomeHLMeadow();
				BiomeBase.addBiome(hl_meadow);
			}
			
			if (ConfigHL.generateHL_mesa) {
				hl_mesa = new RealisticBiomeHLMesa();
				BiomeBase.addBiome(hl_mesa);
			}
			
			if (ConfigHL.generateHL_oasis) {
				hl_oasis = new RealisticBiomeHLOasis();
				BiomeBase.addBiome(hl_oasis);
			}
			
			if (ConfigHL.generateHL_ocean2) {
				hl_ocean2 = new RealisticBiomeHLOcean2();
				BiomeBase.addBiome(hl_ocean2);
			}
			
			if (ConfigHL.generateHL_outback) {
				hl_outback = new RealisticBiomeHLOutback();
				BiomeBase.addBiome(hl_outback);
			}
			
			if (ConfigHL.generateHL_pinelands) {
				hl_pinelands = new RealisticBiomeHLPinelands();
				BiomeBase.addBiome(hl_pinelands);
			}
			
			if (ConfigHL.generateHL_rainforest) {
				hl_rainforest = new RealisticBiomeHLRainforest();
				BiomeBase.addBiome(hl_rainforest);
			}
			
			if (ConfigHL.generateHL_redwoodForest) {
				hl_redwoodForest = new RealisticBiomeHLRedwoodForest();
				BiomeBase.addBiome(hl_redwoodForest);
			}
			
			if (ConfigHL.generateHL_rockIsland) {
				hl_rockIsland = new RealisticBiomeHLRockIsland();
				BiomeBase.addBiome(hl_rockIsland);
			}
			
			if (ConfigHL.generateHL_rockMountains) {
				hl_rockMountains = new RealisticBiomeHLRockMountains();
				BiomeBase.addBiome(hl_rockMountains);
			}
			
			if (ConfigHL.generateHL_sahel) {
				hl_sahel = new RealisticBiomeHLSahel();
				BiomeBase.addBiome(hl_sahel);
			}
			
			if (ConfigHL.generateHL_savannah) {
				hl_savannah = new RealisticBiomeHLSavannah();
				BiomeBase.addBiome(hl_savannah);
			}
			
			if (ConfigHL.generateHL_shrubland) {
				hl_shrubland = new RealisticBiomeHLShrubland();
				BiomeBase.addBiome(hl_shrubland);
			}
			
			if (ConfigHL.generateHL_snowIsland) {
				hl_snowIsland = new RealisticBiomeHLSnowIsland();
				BiomeBase.addBiome(hl_snowIsland);
			}
			
			if (ConfigHL.generateHL_snowMountains) {
				hl_snowMountains = new RealisticBiomeHLSnowMountains();
				BiomeBase.addBiome(hl_snowMountains);
			}
			
			if (ConfigHL.generateHL_steppe) {
				hl_steppe = new RealisticBiomeHLSteppe();
				BiomeBase.addBiome(hl_steppe);
			}
			
			if (ConfigHL.generateHL_tallPineForest) {
				hl_tallPineForest = new RealisticBiomeHLTallPineForest();
				BiomeBase.addBiome(hl_tallPineForest);
			}
			
			if (ConfigHL.generateHL_tropicalIslands) {
				hl_tropicalIslands = new RealisticBiomeHLTropicalIslands();
				BiomeBase.addBiome(hl_tropicalIslands);
			}
			
			if (ConfigHL.generateHL_tropics) {
				hl_tropics = new RealisticBiomeHLTropics();
				BiomeBase.addBiome(hl_tropics);
			}
			
			if (ConfigHL.generateHL_tundra) {
				hl_tundra = new RealisticBiomeHLTundra();
				BiomeBase.addBiome(hl_tundra);
			}
			
			if (ConfigHL.generateHL_valley) {
				hl_valley = new RealisticBiomeHLValley();
				BiomeBase.addBiome(hl_valley);
			}
			
			if (ConfigHL.generateHL_volcanoIsland) {
				hl_volcanoIsland = new RealisticBiomeHLVolcanoIsland();
				BiomeBase.addBiome(hl_volcanoIsland);
			}
			
			if (ConfigHL.generateHL_windyIsland) {
				hl_windyIsland = new RealisticBiomeHLWindyIsland();
				BiomeBase.addBiome(hl_windyIsland);
			}
			
			if (ConfigHL.generateHL_woodlands) {
				hl_woodlands = new RealisticBiomeHLWoodlands();
				BiomeBase.addBiome(hl_woodlands);
			}
			
			if (ConfigHL.generateHL_woodsMountains) {
				hl_woodsMountains = new RealisticBiomeHLWoodsMountains();
				BiomeBase.addBiome(hl_woodsMountains);
			}
		}
	}
}
