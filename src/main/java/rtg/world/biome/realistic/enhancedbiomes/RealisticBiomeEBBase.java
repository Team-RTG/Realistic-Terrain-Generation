package rtg.world.biome.realistic.enhancedbiomes;

import cpw.mods.fml.common.Loader;
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
								new TerrainMountainRiver(),
								new SurfaceMountainSnow(EBBiome.topBlock, EBBiome.fillerBlock, false, null, 0.45f)
							),
							BiomeBase.BiomeCategory.SNOW
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
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
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
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
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
						RealisticBiomeEBBase.addPlaceholderBiome(EBBiome);
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
