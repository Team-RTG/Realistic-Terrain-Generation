package rtg.world.biome.realistic.enhancedbiomes;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
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
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainMountainRiver(),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(230f, 120f, 50f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainMountainRiver(),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainMountainRiver(),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(230f, 120f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(230f, 120f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainPolar(),
								new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainPolar(),
								new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainMesa(),
								new SurfaceMesa(EBBiome.topBlock, EBBiome.fillerBlock, (byte)1)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(160f, 80f, 60f),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(160f, 80f, 60f),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(160f, 80f, 60f),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(160f, 80f, 60f),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainDunes(),
								new SurfaceDesert(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.sand, Blocks.sand, Blocks.sand)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(160f, 80f, 60f),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainHilly(120f, 70f, 60f),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainDuneValley(40f),
								new SurfaceDuneValley(EBBiome.topBlock, EBBiome.fillerBlock, 20f, false, false)
							),
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
								new TerrainMountainSpikes(),
								new SurfaceMountainPolar(Blocks.ice, Blocks.packed_ice, true, Blocks.packed_ice, 20f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
								new TerrainPolar(),
								new SurfaceTundra(Blocks.ice, Blocks.packed_ice)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainPolar(),
								new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
								new TerrainMarsh(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainDunes(),
								new SurfaceDesert(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.sand, Blocks.sand, Blocks.sand)
							),
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainSwampRiver(),
								new SurfaceRiverOasis()
							),
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainDuneValley(40f),
								new SurfaceDuneValley(EBBiome.topBlock, EBBiome.fillerBlock, 20f, false, false)
							),
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainDuneValley(80f),
								new SurfaceDuneValley(EBBiome.topBlock, EBBiome.fillerBlock, 40f, false, false)
							),
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainPolar(),
								new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainCanyon(false, 35f, 160f, 40f, 30f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
								new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(100f, 70f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.SNOW
						);
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainPolar(),
								new SurfaceTundra(Blocks.snow, Blocks.snow)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainCanyon(false, 35f, 160f, 40f, 30f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainCanyon(false, 35f, 80f, 30f, 20f, 10),
								new SurfaceCanyon(EBBiome.topBlock, EBBiome.fillerBlock, (byte)0, 20)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainHilly(200f, 100f, 0f),
								new SurfaceMountainStone(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.95f)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainGrasslandFlats(),
								new SurfaceTundra(EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.COLD
						);
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainMountainSpikes(),
								new SurfaceMountainPolar(EBBiome.topBlock, EBBiome.fillerBlock, false, EBBiome.topBlock, 20f)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainMountainSpikes(),
								new SurfaceMountainPolar(EBBiome.topBlock, EBBiome.fillerBlock, false, EBBiome.topBlock, 20f)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfacePolar(EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, EBBiome.topBlock, EBBiome.fillerBlock)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainFlatLakes(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.grass, Blocks.grass)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
								new TerrainFlatLakes(),
								new SurfaceMarshFix(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.grass, Blocks.grass)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.WET
						);
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.HOT
						);
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
						BiomeBase.addBiome(
							new RealisticBiomeBase(
								EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
								new TerrainGrasslandFlats(),
								new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
							), 
							BiomeBase.BiomeCategory.HOT
						);
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
