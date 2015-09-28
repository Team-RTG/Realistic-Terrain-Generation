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
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
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
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
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
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeClass.contains("enhancedbiomes.world.biome"))
					{
						FMLLog.log(Level.INFO, "EB biome (%s) could not be generated realistically.", biomeName);
					}
				}
			}
		}
	}
	
	public static void addPlaceholderBiome(BiomeGenBase EBBiome)
	{
		BiomeBase.addBiome(
			new RealisticBiomeBase(
				EBBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
				new TerrainGrasslandFlats(),
				new SurfaceGrassland(EBBiome.topBlock, EBBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
	}
}
