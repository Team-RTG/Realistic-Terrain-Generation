package rtg.world.biome.realistic.enhancedbiomes;

import cpw.mods.fml.common.Loader;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceMountainSnow;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainMountainRiver;

public class RealisticBiomeEBBase extends RealisticBiomeBase
{	
	public RealisticBiomeEBBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	/*
	EB BIOMES
	
	Alpine Mountains	enhancedbiomes.world.biome.snow.BiomeGenAlpine
	Alpine Mountains Edge	enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge
	Alpine Mountains M	enhancedbiomes.world.biome.snow.BiomeGenAlpineM
	Alpine Tundra	enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra
	Aspen Forest	enhancedbiomes.world.biome.woodland.BiomeGenAspenForest
	Aspen Hills	enhancedbiomes.world.biome.woodland.BiomeGenAspenForest
	Badlands	enhancedbiomes.world.biome.grass.BiomeGenBadlands
	Basin	enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin
	Beach	net.minecraft.world.biome.BiomeGenBeach
	Birch Forest	net.minecraft.world.biome.BiomeGenForest
	Birch Forest Hills	net.minecraft.world.biome.BiomeGenForest
	Birch Forest Hills M	null
	Birch Forest M	null
	Blossom Hills	enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom
	Blossom Woods	enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom
	Boreal Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago
	Boreal Forest	enhancedbiomes.world.biome.woodland.BiomeGenBorealForest
	Boreal Plateau	enhancedbiomes.world.biome.woodland.BiomeGenBorealForest
	Boreal Plateau M	enhancedbiomes.world.biome.woodland.BiomeGenBorealForest
	Carr	enhancedbiomes.world.biome.wetland.BiomeGenCarr
	Clay Hills	enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills
	Clearing	enhancedbiomes.world.biome.base.BiomeGenGrassBase
	Cold Beach	net.minecraft.world.biome.BiomeGenBeach
	Cold Boreal Forest	enhancedbiomes.world.biome.woodland.BiomeGenBorealForest
	Cold Cypress Forest	enhancedbiomes.world.biome.woodland.BiomeGenCypressForest
	Cold Fir Forest	enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest
	Cold Pine Forest	enhancedbiomes.world.biome.woodland.BiomeGenPineForest
	Cold Taiga	net.minecraft.world.biome.BiomeGenTaiga
	Cold Taiga Hills	net.minecraft.world.biome.BiomeGenTaiga
	Cold Taiga M	net.minecraft.world.biome.BiomeGenMutated
	Creek Bed	enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed
	Cypress Forest	enhancedbiomes.world.biome.woodland.BiomeGenCypressForest
	Deep Ocean	net.minecraft.world.biome.BiomeGenOcean
	Desert	net.minecraft.world.biome.BiomeGenDesert
	Desert Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago
	Desert M	net.minecraft.world.biome.BiomeGenMutated
	DesertHills	net.minecraft.world.biome.BiomeGenDesert
	Ephemeral Lake	enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake
	Ephemeral Lake Edge	enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake
	Extreme Hills	net.minecraft.world.biome.BiomeGenHills
	Extreme Hills Edge	net.minecraft.world.biome.BiomeGenHills
	Extreme Hills M	net.minecraft.world.biome.BiomeGenHills
	Extreme Hills+	net.minecraft.world.biome.BiomeGenHills
	Extreme Hills+ M	net.minecraft.world.biome.BiomeGenHills
	Fens	enhancedbiomes.world.biome.wetland.BiomeGenFen
	Fir Forest	enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest
	Flower Forest	net.minecraft.world.biome.BiomeGenForest
	Flowery Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago
	Forest	net.minecraft.world.biome.BiomeGenForest
	Forested Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago
	Forested Mountains	enhancedbiomes.world.biome.woodland.BiomeGenWoodlands
	Forested Valley	enhancedbiomes.world.biome.woodland.BiomeGenWoodlands
	ForestHills	net.minecraft.world.biome.BiomeGenForest
	Frozen Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago
	FrozenOcean	net.minecraft.world.biome.BiomeGenOcean
	FrozenRiver	net.minecraft.world.biome.BiomeGenRiver
	Glacier	enhancedbiomes.world.biome.snow.BiomeGenGlacier
	Grassy Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago
	Hell	net.minecraft.world.biome.BiomeGenHell
	Ice Mountains	net.minecraft.world.biome.BiomeGenSnow
	Ice Plains	net.minecraft.world.biome.BiomeGenSnow
	Ice Plains Spikes	net.minecraft.world.biome.BiomeGenSnow
	Ice Sheet	enhancedbiomes.world.biome.snow.BiomeGenIceSheet
	Jungle	net.minecraft.world.biome.BiomeGenJungle
	Jungle M	net.minecraft.world.biome.BiomeGenMutated
	JungleEdge	net.minecraft.world.biome.BiomeGenJungle
	JungleEdge M	net.minecraft.world.biome.BiomeGenMutated
	JungleHills	net.minecraft.world.biome.BiomeGenJungle
	Kakadu	enhancedbiomes.world.biome.woodland.BiomeGenKakadu
	Lake	enhancedbiomes.world.biome.wetland.BiomeGenLake
	Low Hills	enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills
	Mangroves	enhancedbiomes.world.biome.wetland.BiomeGenMangrove
	Marsh	enhancedbiomes.world.biome.wetland.BiomeGenMarsh
	Meadow	enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow
	Meadow M	enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM
	Mega Spruce Taiga	net.minecraft.world.biome.BiomeGenTaiga
	Mega Spruce Taiga	net.minecraft.world.biome.BiomeGenTaiga
	Mega Taiga	net.minecraft.world.biome.BiomeGenTaiga
	Mega Taiga Hills	net.minecraft.world.biome.BiomeGenTaiga
	Mesa	net.minecraft.world.biome.BiomeGenMesa
	Mesa (Bryce)	net.minecraft.world.biome.BiomeGenMesa
	Mesa Plateau	net.minecraft.world.biome.BiomeGenMesa
	Mesa Plateau F	net.minecraft.world.biome.BiomeGenMesa
	Mesa Plateau F M	net.minecraft.world.biome.BiomeGenMesa
	Mesa Plateau M	net.minecraft.world.biome.BiomeGenMesa
	Mountainous Archipelago	enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago
	Mountains	enhancedbiomes.world.biome.grass.BiomeGenMountains
	Mountains Edge	enhancedbiomes.world.biome.grass.BiomeGenMountains
	MushroomIsland	net.minecraft.world.biome.BiomeGenMushroomIsland
	MushroomIslandShore	net.minecraft.world.biome.BiomeGenMushroomIsland

	*/
	
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
						//ALPINE MOUNTAINS
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
						//ALPINE MOUNTAINS EDGE
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
						//ALPINE MOUNTAINS M
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
			}
		}
	}
}
