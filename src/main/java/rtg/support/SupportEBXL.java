package rtg.support;

import com.google.common.base.Optional;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.support.edit.*;
import rtg.surface.*;
import rtg.surface.river.SurfaceRiverOasis;
import rtg.terrain.*;
import rtg.support.Support.BiomeCategory;

public class SupportEBXL 
{
	public static void init()
	{
	    //ALPINE
		if(BiomeManager.alpine.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.alpine.get(), VanillaBiomes.vanillaRiverIce,
					new TerrainMountainRiver(),
					new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f)
				), 
				BiomeCategory.SNOW
			);
		}
		
	    //AUTUMNWOODS
		if(BiomeManager.autumnwoods.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.autumnwoods.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainHighland(0f, 140f, 68f, 200f),
					new SurfaceGrassland(BiomeManager.autumnwoods.get().topBlock, BiomeManager.autumnwoods.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.COLD
			);
		}
		
		//BIRCHFOREST
		if(BiomeManager.birchforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.birchforest.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceMountainStone(BiomeManager.birchforest.get().topBlock, BiomeManager.birchforest.get().fillerBlock, false, null, 0.95f)
				), 
				BiomeCategory.COLD
			);
		}
		
	    //EXTREME JUNGLE
		if(BiomeManager.extremejungle.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.extremejungle.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainSwampMountain(135f, 300f),
					new SurfaceMountainStone(BiomeManager.extremejungle.get().topBlock, BiomeManager.extremejungle.get().fillerBlock, false, null, 0.95f)
				), 
				BiomeCategory.WET
			);
		}
		
		//FORESTED ISLAND
		if(BiomeManager.forestedisland.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.forestedisland.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainGrasslandHills(90f, 180f, 13f, 100f, 1f, 260f, 59f),
					new SurfaceGrassland(BiomeManager.forestedisland.get().topBlock, BiomeManager.forestedisland.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.COLD
			);
		}
		
	    //FORESTED HILLS
		if(BiomeManager.forestedhills.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.forestedhills.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceMountainStone(BiomeManager.forestedhills.get().topBlock, BiomeManager.forestedhills.get().fillerBlock, false, null, 0.95f)
				), 
				BiomeCategory.COLD
			);
		}
		
		//GLACIER
		if(BiomeManager.glacier.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.glacier.get(), VanillaBiomes.vanillaRiverIce,
					new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
					new SurfaceMountainStone(BiomeManager.glacier.get().topBlock, BiomeManager.glacier.get().fillerBlock, false, null, 0.95f)
				), 
				BiomeCategory.SNOW
			);
		}
		
		//GREENHILLS
		if(BiomeManager.greenhills.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.greenhills.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceMountainStone(BiomeManager.greenhills.get().topBlock, BiomeManager.greenhills.get().fillerBlock, false, null, 0.95f)
				), 
				BiomeCategory.COLD
			);
		}
		
	    //ICEWASTELAND
		if(BiomeManager.icewasteland.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.icewasteland.get(), VanillaBiomes.vanillaRiverIce,
					new TerrainPolar(),
					new SurfacePolar(BiomeManager.icewasteland.get().topBlock, BiomeManager.icewasteland.get().fillerBlock)
				), 
				BiomeCategory.SNOW
			);
		}
		
		//GREENSWAMP
		if(BiomeManager.greenswamp.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.greenswamp.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainSwampRiver(),
					new SurfaceGrassland(BiomeManager.greenswamp.get().topBlock, BiomeManager.greenswamp.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.WET
			);
		}
		
	    //MARSH
		if(BiomeManager.marsh.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.marsh.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainMarsh(),
					new SurfaceMarshFix(BiomeManager.marsh.get().topBlock, BiomeManager.marsh.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.WET
			);
		}
		
	    //MEADOW
		if(BiomeManager.meadow.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.meadow.get(), VanillaBiomes.vanillaRiverCold,
					new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
					new SurfaceGrassland(BiomeManager.meadow.get().topBlock, BiomeManager.meadow.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.COLD
			);
		}
		
		//MINI JUNGLE
		if(BiomeManager.minijungle.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.minijungle.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainHighland(0f, 140f, 68f, 200f),
					new SurfaceGrassland(BiomeManager.minijungle.get().topBlock, BiomeManager.minijungle.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.WET
			);
		}
		
	    //MOUNTAIN DESERT
		if(BiomeManager.mountaindesert.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.mountaindesert.get(), VanillaBiomes.vanillaRiverOasis,
					RealisticBiomeBase.coastOasis,
					new TerrainHilly(230f, 100f, 0f),
					new SurfaceBase[]{
						new SurfaceDesertMountain(BiomeManager.mountaindesert.get().topBlock, BiomeManager.mountaindesert.get().fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f),
						new SurfaceRiverOasis()
					},
					new EditBase[]{
						new EditRiverOasis()
					}
				), 
				BiomeCategory.HOT
			);
		}
		
		//MOUNTAIN RIDGE
		if(BiomeManager.mountainridge.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.mountainridge.get(), VanillaBiomes.vanillaRiverOasis,
					RealisticBiomeBase.coastOasis,
					new TerrainHilly(230f, 110f, 0f),
					new SurfaceBase[]{
						new SurfaceDesertMountain(BiomeManager.mountainridge.get().topBlock, BiomeManager.mountainridge.get().fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f),
						new SurfaceRiverOasis()
					},
					new EditBase[]{
						new EditRiverOasis()
					}
				), 
				BiomeCategory.HOT
			);
		}
		
	    //MOUNTAIN TAIGA
		if(BiomeManager.mountaintaiga.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.mountaintaiga.get(), VanillaBiomes.vanillaRiverIce,
					new TerrainMountainSpikes(),
					new SurfaceMountainStone(BiomeManager.mountaintaiga.get().topBlock, BiomeManager.mountaintaiga.get().fillerBlock, false, null, 1.2f)
				), 
				BiomeCategory.SNOW
			);
		}
		
	    //PINE FOREST
		if(BiomeManager.pineforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.pineforest.get(), VanillaBiomes.vanillaRiverCold,
					new TerrainMountainSpikes(),
					new SurfaceMountainStone(BiomeManager.pineforest.get().topBlock, BiomeManager.pineforest.get().fillerBlock, false, null, 1.2f)
				), 
				BiomeCategory.COLD
			);
		}
		
	    //RAINFOREST
		if(BiomeManager.rainforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.rainforest.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainHilly(230f, 100f, 0f),
					new SurfaceGrassland(BiomeManager.rainforest.get().topBlock, BiomeManager.rainforest.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.WET
			);
		}
		
		//REDWOOD FOREST
		if(BiomeManager.redwoodforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.redwoodforest.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceMountainStone(BiomeManager.redwoodforest.get().topBlock, BiomeManager.redwoodforest.get().fillerBlock, true, Blocks.sand, 0.2f)
				), 
				BiomeCategory.COLD
			);
		}
		
	    //REDWOOD LUSH
		if(BiomeManager.redwoodlush.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.redwoodlush.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceMountainStone(BiomeManager.redwoodlush.get().topBlock, BiomeManager.redwoodlush.get().fillerBlock, true, Blocks.sand, 0.2f)
				), 
				BiomeCategory.WET
			);
		}
		
	    //SAVANNA
		if(BiomeManager.savanna.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.savanna.get(), VanillaBiomes.vanillaRiverHot,
					new TerrainGrasslandFlats(),
					new SurfaceGrasslandMix1(BiomeManager.savanna.get().topBlock, BiomeManager.savanna.get().fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 13f, 0.27f)
				), 
				BiomeCategory.HOT
			);
		}
		
		//SHRUBLAND
		if(BiomeManager.shrubland.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.shrubland.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
					new SurfaceGrassland(BiomeManager.shrubland.get().topBlock, BiomeManager.shrubland.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.COLD
			);
		}
		
		//SNOWY FOREST
		if(BiomeManager.snowforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.snowforest.get(), VanillaBiomes.vanillaRiverIce,
					new TerrainHighland(0f, 140f, 68f, 200f),
					new SurfaceGrassland(BiomeManager.snowforest.get().topBlock, BiomeManager.snowforest.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.SNOW
			);
		}
		
	    //SNOWY RAIN FOREST
		if(BiomeManager.snowyrainforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.snowyrainforest.get(), VanillaBiomes.vanillaRiverIce,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceGrassland(BiomeManager.snowforest.get().topBlock, BiomeManager.snowforest.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.SNOW
			);
		}
		
	    //TEMPERATE RAINFOREST
		if(BiomeManager.temperaterainforest.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.temperaterainforest.get(), VanillaBiomes.vanillaRiverWet,
					new TerrainHilly(230f, 120f, 0f),
					new SurfaceMountainStone(BiomeManager.temperaterainforest.get().topBlock, BiomeManager.temperaterainforest.get().fillerBlock, true, Blocks.sand, 0.2f)
				), 
				BiomeCategory.WET
			);
		}
		
	    //TUNDRA
		if(BiomeManager.tundra.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.tundra.get(), VanillaBiomes.vanillaRiverCold,
					new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
					new SurfaceGrassland(BiomeManager.tundra.get().topBlock, BiomeManager.tundra.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.COLD
			);
		}
		
		//WASTELAND
		if(BiomeManager.wasteland.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.wasteland.get(), VanillaBiomes.vanillaRiverOasis,
					RealisticBiomeBase.coastOasis,
					new TerrainGrasslandHills(30f, 180f, 13f, 100f, 28f, 260f, 70f),
					new SurfaceBase[]{
						new SurfaceGrassland(BiomeManager.wasteland.get().topBlock, BiomeManager.wasteland.get().fillerBlock, Blocks.stone, Blocks.cobblestone),
						new SurfaceRiverOasis()
					},
					new EditBase[]{
						new EditRiverOasis()
					}
				), 
				BiomeCategory.HOT
			);
		}
		
		//WOODLANDS
		if(BiomeManager.woodlands.isPresent())
		{
			Support.addBiome(
				new RealisticBiomeSupport(
					BiomeManager.woodlands.get(), VanillaBiomes.vanillaRiverTemperate,
					new TerrainHighland(0f, 140f, 68f, 200f),
					new SurfaceGrassland(BiomeManager.woodlands.get().topBlock, BiomeManager.woodlands.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
				), 
				BiomeCategory.COLD
			);
		}
		
	}
}
