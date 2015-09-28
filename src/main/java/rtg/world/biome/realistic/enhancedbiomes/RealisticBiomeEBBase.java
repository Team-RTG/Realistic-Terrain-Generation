package rtg.world.biome.realistic.enhancedbiomes;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.*;
import rtg.world.gen.surface.river.SurfaceRiverOasis;
import rtg.world.gen.terrain.*;

public class RealisticBiomeEBBase extends RealisticBiomeBase
{	
	public RealisticBiomeEBBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("enhancedbiomes"))
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for (int i = 0; i < 256; i++)
			{
				if (b[i] != null)
				{
					BiomeGenBase EBBiome = b[i];
					String biomeName = b[i].biomeName;
					String biomeClass = b[i].getBiomeClass().getName();
					
					/**
					 * Enhanced Biomes sometimes uses the same biome class to generate different biomes.
					 * As such, we have to check both the biome name and the biome class to make sure we've got the right biome.
					 */
					
					if (biomeName == "Alpine Mountains" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpine")
					{
						if (ConfigEB.generateEBAlpineMountains) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainMountainRiver(),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
						if (ConfigEB.generateEBAlpineMountainsEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(230f, 120f, 50f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
						if (ConfigEB.generateEBAlpineMountainsM) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainMountainRiver(),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
						if (ConfigEB.generateEBAlpineTundra) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainMountainRiver(),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(230f, 120f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(230f, 120f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
						if (ConfigEB.generateEBBadlands) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainPolar(),
									new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
						if (ConfigEB.generateEBBasin) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainPolar(),
									new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomWoods) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
						if (ConfigEB.generateEBBorealArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateau) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateauM) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
						if (ConfigEB.generateEBCarr) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
						if (ConfigEB.generateEBClayHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainMesa(),
									new SurfaceMesa(EBBiome.topBlock, EBBiome.fillerBlock, (byte)1)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
						if (ConfigEB.generateEBClearing) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBColdBorealForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(160f, 80f, 60f),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBColdCypressForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(160f, 80f, 60f),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBColdFirForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(160f, 80f, 60f),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBColdPineForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(160f, 80f, 60f),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
					{
						if (ConfigEB.generateEBCreekBed) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBCypressForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
						if (ConfigEB.generateEBDesertArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainDunes(),
									new SurfaceDesert(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.sand, Blocks.sand, Blocks.sand)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLake) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLakeEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
						if (ConfigEB.generateEBFens) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBFirForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(160f, 80f, 60f),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
						if (ConfigEB.generateEBFloweryArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
						if (ConfigEB.generateEBForestedArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedMountains) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainHilly(120f, 70f, 60f),
									new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
								),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedValley) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainDuneValley(40f),
									new SurfaceDuneValley(EBBiome.topBlock, EBBiome.fillerBlock, 20f, false, false)
								),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
						if (ConfigEB.generateEBFrozenArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
						if (ConfigEB.generateEBGlacier) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
									new TerrainMountainSpikes(),
									new SurfaceMountainPolar(Blocks.ice, Blocks.packed_ice, true, Blocks.packed_ice, 20f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
						if (ConfigEB.generateEBGrassyArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
						if (ConfigEB.generateEBIceSheet) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
									new TerrainPolar(),
									new SurfaceTundra(Blocks.ice, Blocks.packed_ice)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
						if (ConfigEB.generateEBKakadu) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
						if (ConfigEB.generateEBLake) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
						if (ConfigEB.generateEBLowHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
						if (ConfigEB.generateEBMangroves) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
						if (ConfigEB.generateEBMarsh) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
						if (ConfigEB.generateEBMeadow) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
						if (ConfigEB.generateEBMeadowM) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
						if (ConfigEB.generateEBMountainousArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountains) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountainsEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
						if (ConfigEB.generateEBOakForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
						if (ConfigEB.generateEBOasis) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBPineForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
						if (ConfigEB.generateEBPineForestArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
						if (ConfigEB.generateEBPlateau) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						if (ConfigEB.generateEBPolarDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainPolar(),
									new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
						if (ConfigEB.generateEBPrairie) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforestValley) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
									new TerrainMarsh(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
					{
						if (ConfigEB.generateEBRedDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainDunes(),
									new SurfaceDesert(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.sand, Blocks.sand, Blocks.sand)
								),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
					{
						if (ConfigEB.generateEBRiparianZone) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainSwampRiver(),
									new SurfaceRiverOasis()
								),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
						if (ConfigEB.generateEBRockyDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainDuneValley(40f),
									new SurfaceDuneValley(EBBiome.topBlock, EBBiome.fillerBlock, 20f, false, false)
								),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
						if (ConfigEB.generateEBRockyHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainDuneValley(80f),
									new SurfaceDuneValley(EBBiome.topBlock, EBBiome.fillerBlock, 40f, false, false)
								),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
						if (ConfigEB.generateEBRoofedShrublands) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
						if (ConfigEB.generateEBSahara) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainPolar(),
									new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
						if (ConfigEB.generateEBSandstoneCanyon) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainCanyon(false, 35f, 160f, 40f, 30f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
						if (ConfigEB.generateEBSandstoneCanyons) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRanges) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRangesM) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBScree) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
						if (ConfigEB.generateEBScrub) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
									new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
						if (ConfigEB.generateEBShield) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
						if (ConfigEB.generateEBShrublands) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(100f, 70f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
						if (ConfigEB.generateEBSnowyDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
						if (ConfigEB.generateEBSnowyPlateau) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
						if (ConfigEB.generateEBSnowyRanges) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBSnowyWastelands) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainPolar(),
									new SurfaceTundra(Blocks.snow, Blocks.snow)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
						if (ConfigEB.generateEBSteppe) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyon) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainCanyon(false, 35f, 160f, 40f, 30f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyons) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
									new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
						if (ConfigEB.generateEBTropicalArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainHilly(200f, 100f, 0f),
									new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
						if (ConfigEB.generateEBTundra) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainGrasslandFlats(),
									new SurfaceTundra(EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcano) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainMountainSpikes(),
									new SurfaceMountainPolar(EBBiome.topBlock, EBBiome.fillerBlock, false, EBBiome.topBlock, 20f)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcanoM) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainMountainSpikes(),
									new SurfaceMountainPolar(EBBiome.topBlock, EBBiome.fillerBlock, false, EBBiome.topBlock, 20f)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBWastelands) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandField) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandHills) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLake) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainFlatLakes(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.grass, Blocks.grass)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLakeEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
									new TerrainFlatLakes(),
									new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.grass, Blocks.grass)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlands) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
						if (ConfigEB.generateEBXericSavannah) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
						if (ConfigEB.generateEBXericShrubland) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
									new TerrainGrasslandFlats(),
									new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								), 
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeClass.contains("enhancedbiomes.world.biome"))
					{
						FMLLog.log(Level.INFO, "EB biome (%s) could not be generated realistically.", biomeName);
					}
				}
			}
		}
	}
}
