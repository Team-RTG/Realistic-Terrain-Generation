package rtg.world.biome.realistic.extrabiomes;

import cpw.mods.fml.common.Loader;
import extrabiomes.api.BiomeManager;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceDesertMountain;
import rtg.world.gen.surface.SurfaceGrassland;
import rtg.world.gen.surface.SurfaceGrasslandMix1;
import rtg.world.gen.surface.SurfaceMarshFix;
import rtg.world.gen.surface.SurfaceMountainSnow;
import rtg.world.gen.surface.SurfaceMountainStone;
import rtg.world.gen.surface.SurfacePolar;
import rtg.world.gen.surface.river.SurfaceRiverOasis;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainGrasslandFlats;
import rtg.world.gen.terrain.TerrainGrasslandHills;
import rtg.world.gen.terrain.TerrainHighland;
import rtg.world.gen.terrain.TerrainHilly;
import rtg.world.gen.terrain.TerrainMarsh;
import rtg.world.gen.terrain.TerrainMountainRiver;
import rtg.world.gen.terrain.TerrainMountainSpikes;
import rtg.world.gen.terrain.TerrainPolar;
import rtg.world.gen.terrain.TerrainSwampMountain;
import rtg.world.gen.terrain.TerrainSwampRiver;

public class RealisticBiomeEBXLBase extends RealisticBiomeBase
{	
	public RealisticBiomeEBXLBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ExtrabiomesXL"))
		{
		    //ALPINE
			if(BiomeManager.alpine.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.alpine.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
						new TerrainMountainRiver(),
						new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f)
					), 
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
		    //AUTUMNWOODS
			if(BiomeManager.autumnwoods.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.autumnwoods.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainHighland(0f, 140f, 68f, 200f),
						new SurfaceGrassland(BiomeManager.autumnwoods.get().topBlock, BiomeManager.autumnwoods.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//BIRCHFOREST
			if(BiomeManager.birchforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.birchforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceMountainStone(BiomeManager.birchforest.get().topBlock, BiomeManager.birchforest.get().fillerBlock, false, null, 0.95f)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
		    //EXTREME JUNGLE
			if(BiomeManager.extremejungle.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.extremejungle.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainSwampMountain(135f, 300f),
						new SurfaceMountainStone(BiomeManager.extremejungle.get().topBlock, BiomeManager.extremejungle.get().fillerBlock, false, null, 0.95f)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//FORESTED ISLAND
			if(BiomeManager.forestedisland.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.forestedisland.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainGrasslandHills(90f, 180f, 13f, 100f, 1f, 260f, 59f),
						new SurfaceGrassland(BiomeManager.forestedisland.get().topBlock, BiomeManager.forestedisland.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
		    //FORESTED HILLS
			if(BiomeManager.forestedhills.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.forestedhills.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceMountainStone(BiomeManager.forestedhills.get().topBlock, BiomeManager.forestedhills.get().fillerBlock, false, null, 0.95f)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//GLACIER
			if(BiomeManager.glacier.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.glacier.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
						new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
						new SurfaceMountainStone(BiomeManager.glacier.get().topBlock, BiomeManager.glacier.get().fillerBlock, false, null, 0.95f)
					), 
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//GREENHILLS
			if(BiomeManager.greenhills.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.greenhills.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceMountainStone(BiomeManager.greenhills.get().topBlock, BiomeManager.greenhills.get().fillerBlock, false, null, 0.95f)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
		    //ICEWASTELAND
			if(BiomeManager.icewasteland.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.icewasteland.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
						new TerrainPolar(),
						new SurfacePolar(BiomeManager.icewasteland.get().topBlock, BiomeManager.icewasteland.get().fillerBlock)
					), 
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//GREENSWAMP
			if(BiomeManager.greenswamp.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.greenswamp.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainSwampRiver(),
						new SurfaceGrassland(BiomeManager.greenswamp.get().topBlock, BiomeManager.greenswamp.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
		    //MARSH
			if(BiomeManager.marsh.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.marsh.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainMarsh(),
						new SurfaceMarshFix(BiomeManager.marsh.get().topBlock, BiomeManager.marsh.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
		    //MEADOW
			if(BiomeManager.meadow.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.meadow.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
						new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
						new SurfaceGrassland(BiomeManager.meadow.get().topBlock, BiomeManager.meadow.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//MINI JUNGLE
			if(BiomeManager.minijungle.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.minijungle.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainHighland(0f, 140f, 68f, 200f),
						new SurfaceGrassland(BiomeManager.minijungle.get().topBlock, BiomeManager.minijungle.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
		    //MOUNTAIN DESERT
			if(BiomeManager.mountaindesert.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.mountaindesert.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
						new TerrainHilly(230f, 100f, 0f),
						new SurfaceBase[]{
							new SurfaceDesertMountain(BiomeManager.mountaindesert.get().topBlock, BiomeManager.mountaindesert.get().fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f),
							new SurfaceRiverOasis()
						}
					), 
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//MOUNTAIN RIDGE
			if(BiomeManager.mountainridge.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.mountainridge.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
						new TerrainHilly(230f, 110f, 0f),
						new SurfaceBase[]{
							new SurfaceDesertMountain(BiomeManager.mountainridge.get().topBlock, BiomeManager.mountainridge.get().fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f),
							new SurfaceRiverOasis()
						}
					), 
					BiomeBase.BiomeCategory.HOT
				);
			}
			
		    //MOUNTAIN TAIGA
			if(BiomeManager.mountaintaiga.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.mountaintaiga.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
						new TerrainMountainSpikes(),
						new SurfaceMountainStone(BiomeManager.mountaintaiga.get().topBlock, BiomeManager.mountaintaiga.get().fillerBlock, false, null, 1.2f)
					), 
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
		    //PINE FOREST
			if(BiomeManager.pineforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.pineforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
						new TerrainMountainSpikes(),
						new SurfaceMountainStone(BiomeManager.pineforest.get().topBlock, BiomeManager.pineforest.get().fillerBlock, false, null, 1.2f)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
		    //RAINFOREST
			if(BiomeManager.rainforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.rainforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainHilly(230f, 100f, 0f),
						new SurfaceGrassland(BiomeManager.rainforest.get().topBlock, BiomeManager.rainforest.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//REDWOOD FOREST
			if(BiomeManager.redwoodforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.redwoodforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceMountainStone(BiomeManager.redwoodforest.get().topBlock, BiomeManager.redwoodforest.get().fillerBlock, true, Blocks.sand, 0.2f)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
		    //REDWOOD LUSH
			if(BiomeManager.redwoodlush.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.redwoodlush.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceMountainStone(BiomeManager.redwoodlush.get().topBlock, BiomeManager.redwoodlush.get().fillerBlock, true, Blocks.sand, 0.2f)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
		    //SAVANNA
			if(BiomeManager.savanna.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.savanna.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
						new TerrainGrasslandFlats(),
						new SurfaceGrasslandMix1(BiomeManager.savanna.get().topBlock, BiomeManager.savanna.get().fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 13f, 0.27f)
					), 
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//SHRUBLAND
			if(BiomeManager.shrubland.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.shrubland.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
						new SurfaceGrassland(BiomeManager.shrubland.get().topBlock, BiomeManager.shrubland.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//SNOWY FOREST
			if(BiomeManager.snowforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.snowforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
						new TerrainHighland(0f, 140f, 68f, 200f),
						new SurfaceGrassland(BiomeManager.snowforest.get().topBlock, BiomeManager.snowforest.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
		    //SNOWY RAIN FOREST
			if(BiomeManager.snowyrainforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.snowyrainforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceGrassland(BiomeManager.snowforest.get().topBlock, BiomeManager.snowforest.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
		    //TEMPERATE RAINFOREST
			if(BiomeManager.temperaterainforest.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.temperaterainforest.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
						new TerrainHilly(230f, 120f, 0f),
						new SurfaceMountainStone(BiomeManager.temperaterainforest.get().topBlock, BiomeManager.temperaterainforest.get().fillerBlock, true, Blocks.sand, 0.2f)
					), 
					BiomeBase.BiomeCategory.WET
				);
			}
			
		    //TUNDRA
			if(BiomeManager.tundra.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.tundra.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
						new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
						new SurfaceGrassland(BiomeManager.tundra.get().topBlock, BiomeManager.tundra.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//WASTELAND
			if(BiomeManager.wasteland.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.wasteland.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
						new TerrainGrasslandHills(30f, 180f, 13f, 100f, 28f, 260f, 70f),
						new SurfaceBase[]{
							new SurfaceGrassland(BiomeManager.wasteland.get().topBlock, BiomeManager.wasteland.get().fillerBlock, Blocks.stone, Blocks.cobblestone),
							new SurfaceRiverOasis()
						}
					), 
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//WOODLANDS
			if(BiomeManager.woodlands.isPresent())
			{
				BiomeBase.addBiome(
					new RealisticBiomeBase(
						BiomeManager.woodlands.get(), BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
						new TerrainHighland(0f, 140f, 68f, 200f),
						new SurfaceGrassland(BiomeManager.woodlands.get().topBlock, BiomeManager.woodlands.get().fillerBlock, Blocks.stone, Blocks.cobblestone)
					), 
					BiomeBase.BiomeCategory.COLD
				);
			}
		}		
	}
}
