package rtg.support;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.support.edit.*;
import rtg.surface.*;
import rtg.surface.river.SurfaceRiverOasis;
import rtg.terrain.*;
import biomesoplenty.api.content.BOPCBiomes;
import rtg.support.Support.BiomeCategory;

public class SupportBOP 
{
	public static void init()
	{
		//ALPS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.alps, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.alps.topBlock, BOPCBiomes.alps.fillerBlock, false, null, 0.45f)
			),
			BiomeCategory.SNOW
		);
		
		//ALPS FOREST
/*		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.alpsForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f)
			),
			BiomeCategory.SNOW
		);*/
		
		//ARCTIC
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.arctic, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.ICE),
				new TerrainGrasslandFlats(),
				new SurfaceTundra(BOPCBiomes.arctic.topBlock, BOPCBiomes.arctic.fillerBlock)
			),
			BiomeCategory.SNOW
		);
		
		//BAMBOO FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.bambooForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainSwampMountain(135f, 300f),
				new SurfaceMountainStone(BOPCBiomes.bambooForest.topBlock, BOPCBiomes.bambooForest.fillerBlock, false, null, 0.95f)
			),
			BiomeCategory.WET
		);
		
		//BAYOU
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.bayou, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.bayou.topBlock, BOPCBiomes.bayou.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//BOG
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.bog, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.bog.topBlock, BOPCBiomes.bog.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//BOREAL FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.borealForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.borealForest.topBlock, BOPCBiomes.borealForest.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			),
			BiomeCategory.COLD
		);
		
		//BRUSHLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.brushland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrasslandMix1(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 13f, 0.27f)
			),
			BiomeCategory.HOT
		);
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.brushland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, 300f, false, true),
					new SurfaceRiverOasis(),
				},
				new EditBase[]{
					new EditRiverOasis()
				}
			),
			BiomeCategory.HOT
		);
		
		//CANYON
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.canyon, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainCanyon(true, 35f, 160f, 60f, 40f, 69f),
				new SurfaceCanyon(BOPCBiomes.canyon.topBlock, BOPCBiomes.canyon.fillerBlock, (byte)0, 0)
			),
			BiomeCategory.HOT
		);
		
		//CANYON RAVINE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.canyonRavine, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainCanyon(true, 35f, 160f, 60f, 40f, 69f),
				new SurfaceCanyon(BOPCBiomes.canyonRavine.topBlock, BOPCBiomes.canyonRavine.fillerBlock, (byte)0, 0)
			),
			BiomeCategory.HOT
		); 
		
		//CHAPARRAL
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.chaparral, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrasslandMix1(BOPCBiomes.chaparral.topBlock, BOPCBiomes.chaparral.fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 26f, 0.35f)
			),
			BiomeCategory.HOT
		);
		
		//CHERRYBLOSSOM GROVE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.cherryBlossomGrove, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(6f, 120f, 65f, 200f),
				new SurfaceMountainStone(BOPCBiomes.borealForest.topBlock, BOPCBiomes.borealForest.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 1.5f)
			),
			BiomeCategory.COLD
		);
		
		//CONIFEROUS FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.coniferousForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.snowyConiferousForest.topBlock, BOPCBiomes.snowyConiferousForest.fillerBlock, false, null, 0.45f)
			),
			BiomeCategory.COLD
		);
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.snowyConiferousForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.ICE),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.snowyConiferousForest.topBlock, BOPCBiomes.snowyConiferousForest.fillerBlock, false, null, 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
			),
			BiomeCategory.SNOW
		);
		
		//CRAG
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.crag, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainMountain(),
				new SurfaceGrassland(BOPCBiomes.crag.topBlock, BOPCBiomes.crag.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//DEAD FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.deadForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainGrasslandHills(50f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.deadForest.topBlock, BOPCBiomes.deadForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//DEAD SWAMP
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.deadSwamp, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.deadSwamp.topBlock, BOPCBiomes.deadSwamp.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//DECIDUOUS FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.deciduousForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.deciduousForest.topBlock, BOPCBiomes.deciduousForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//DENSE FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.denseForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.denseForest.topBlock, BOPCBiomes.denseForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
	    	),
			BiomeCategory.COLD
		);
		
		//EUCALYPTUS FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.eucalyptusForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainHighland(0f, 180f, 68f, 120f),
				new SurfaceGrassland(BOPCBiomes.eucalyptusForest.topBlock, BOPCBiomes.eucalyptusForest.fillerBlock, Blocks.stone, Blocks.cobblestone)						
			),
			BiomeCategory.WET
		); 
		
		//FEN
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.fen, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.fen.topBlock, BOPCBiomes.fen.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//FLOWER FIELD
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.flowerField,
				new TerrainGrasslandHills(40f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.flowerField.topBlock, BOPCBiomes.flowerField.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//FROST FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.frostForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.ICE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.frostForest.topBlock, BOPCBiomes.frostForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.SNOW
		);
		
		//FUNGI FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.fungiForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainSwampMountain(135f, 300f),
				new SurfaceMountainStone(BOPCBiomes.fungiForest.topBlock, BOPCBiomes.fungiForest.fillerBlock, false, null, 0.95f)
			),
			BiomeCategory.WET
		);
		
		//GARDEN
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.garden, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.garden.topBlock, BOPCBiomes.garden.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			),
			BiomeCategory.COLD
		);
		
		//GLACIER
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.glacier, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.glacier.topBlock, BOPCBiomes.glacier.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			),
			BiomeCategory.SNOW
		);
		
		//GRASSLAND
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.grassland,
				new TerrainGrasslandHills(47f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.grassland.topBlock, BOPCBiomes.grassland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//GROVE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.grove, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.grove.topBlock, BOPCBiomes.grove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//HEATHLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.heathland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, 300f, false, true) ,
					new SurfaceRiverOasis(),
				},
				new EditBase[]{
					new EditRiverOasis()
				}
			),
			BiomeCategory.HOT
		);
		
		//HIGHLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.highland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 150f),
				new SurfaceMountainStone(BOPCBiomes.highland.topBlock, BOPCBiomes.highland.fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
			),
			BiomeCategory.COLD
		);
		
		//JADE CLIFFS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.jadeCliffs, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainHilly(230f, 120f, 0f),
				new SurfaceMountainStone(BOPCBiomes.jadeCliffs.topBlock, BOPCBiomes.jadeCliffs.fillerBlock, false, null, 0.95f)
			),
			BiomeCategory.HOT
		);
		
		//LAND OF LAKES
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.landOfLakes, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainGrasslandFlats(),
				new SurfaceGrassland(BOPCBiomes.landOfLakes.topBlock, BOPCBiomes.landOfLakes.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);		
		
        //LAND OF LAKES MARCH
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.landOfLakesMarsh, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.landOfLakes.topBlock, BOPCBiomes.landOfLakes.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);		
		
		//LAVENDER FIELDS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.lavenderFields, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainMountainSpikes(),
				new SurfaceMountainStone(BOPCBiomes.lavenderFields.topBlock, BOPCBiomes.lavenderFields.fillerBlock, false, null, 1.2f)
			),
			BiomeCategory.COLD
		);
		
		//LUSH DESERT
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.lushDesert, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.lushDesert.topBlock, BOPCBiomes.lushDesert.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//LUSH SWAMP
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.lushSwamp, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.lushSwamp.topBlock, BOPCBiomes.lushSwamp.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//MANGROVE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.mangrove, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.mangrove.topBlock, BOPCBiomes.mangrove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//MAPLE WOODS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.mapleWoods, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.mapleWoods.topBlock, BOPCBiomes.mapleWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//MARSH
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.marsh,
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.marsh.topBlock, BOPCBiomes.marsh.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//MEADOW
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.meadow, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainMountainSpikes(),
				new SurfaceMountainStone(BOPCBiomes.meadow.topBlock, BOPCBiomes.meadow.fillerBlock, false, null, 1.2f)
			),
			BiomeCategory.COLD
		);
		
		//MEADOW FOREST
		Support.addBiome(
				new RealisticBiomeSupport(
					BOPCBiomes.meadowForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
					new TerrainMountainSpikes(),
					new SurfaceMountainStone(BOPCBiomes.meadowForest.topBlock, BOPCBiomes.meadowForest.fillerBlock, false, null, 1.2f)
				),
				BiomeCategory.COLD
			);
		
		//MOOR
		/*Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.moor,
				new TerrainHighland(0f, 70f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.moor.topBlock, BOPCBiomes.moor.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//MOUNTAIN
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.mountain, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainMountainRiver(),
				new SurfaceMountainStone(BOPCBiomes.mountain.topBlock, BOPCBiomes.mountain.fillerBlock, true, Blocks.sand, 0.75f)
			),
			BiomeCategory.HOT
		);
		
		//MYSTIC GROVE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.mysticGrove, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.mysticGrove.topBlock, BOPCBiomes.mysticGrove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//OASIS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.oasis, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
				new TerrainGrasslandFlats(),
				new SurfaceGrassland(BOPCBiomes.oasis.topBlock, BOPCBiomes.oasis.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);		
		
		//OMINOUS WOODS
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.ominousWoods,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.ominousWoods.topBlock, BOPCBiomes.ominousWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//ORIGIN VALLEY
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.originValley,
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.originValley.topBlock, BOPCBiomes.originValley.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//OUTBACK
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.outback, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.outback.topBlock, BOPCBiomes.outback.fillerBlock, 300f, false, false),
					new SurfaceRiverOasis(),
				},
				new EditBase[]{
					new EditRiverOasis()
				}
			),
			BiomeCategory.HOT
		);
		
		//PRAIRIE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.prairie, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.prairie.topBlock, BOPCBiomes.prairie.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//QUAGMIRE
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.quagmire, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainMountain(),
				new SurfaceGrassland(BOPCBiomes.quagmire.topBlock, BOPCBiomes.quagmire.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
	
		//RAINFOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.rainforest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainSwampMountain(120f, 300f),
				new SurfaceMountainStone(BOPCBiomes.rainforest.topBlock, BOPCBiomes.rainforest.fillerBlock, false, null, 1.3f)
			),
			BiomeCategory.WET
		);
		
		//REDWOOD FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.redwoodForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainGrasslandHills(80f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceMountainStone(BOPCBiomes.redwoodForest.topBlock, BOPCBiomes.redwoodForest.fillerBlock, false, null, 0.4f)
			),
			BiomeCategory.COLD
		);
		
		//SACRED SPRINGS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.sacredSprings, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainHighland(0f, 120f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.sacredSprings.topBlock, BOPCBiomes.sacredSprings.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//SEASONAL FOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.seasonalForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.seasonalForest.topBlock, BOPCBiomes.seasonalForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//SEASONAL FOREST CLEARING
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.seasonalForestClearing, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 170f),
				new SurfaceGrassland(BOPCBiomes.seasonalForestClearing.topBlock, BOPCBiomes.seasonalForestClearing.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
				
		//SHIELD
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.shield, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.shield.topBlock, BOPCBiomes.shield.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//SHRUBLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.shrubland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.shrubland.topBlock, BOPCBiomes.shrubland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
			
		//SILKGLADES
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.silkglades, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.silkglades.topBlock, BOPCBiomes.silkglades.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//SLUDGEPIT
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.sludgepit, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.sludgepit.topBlock, BOPCBiomes.sludgepit.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//STEPPE
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.steppe,
				new TerrainGrasslandHills(70f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.steppe.topBlock, BOPCBiomes.steppe.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//TEMPERATE RAINFOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.temperateRainforest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainMountainRiver(),
				new SurfaceMountainStone(BOPCBiomes.temperateRainforest.topBlock, BOPCBiomes.temperateRainforest.fillerBlock, false, null, 0.45f)
			),
			BiomeCategory.WET
		);
		
		//THICKET
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.thicket,
				new TerrainGrasslandHills(70f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.thicket.topBlock, BOPCBiomes.thicket.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//TROPICAL RAINFOREST
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.tropicalRainforest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.tropicalRainforest.topBlock, BOPCBiomes.tropicalRainforest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//TROPICS
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.tropics, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.tropics.topBlock, BOPCBiomes.tropics.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//TUNDRA
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.tundra, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.ICE),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.tundra.topBlock, BOPCBiomes.tundra.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.SNOW
		);

		//VOLCANO
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.volcano, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.volcano.topBlock, BOPCBiomes.volcano.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//WASTELAND
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.wasteland,
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.wasteland.topBlock, BOPCBiomes.wasteland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//WETLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.wetland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.wetland.topBlock, BOPCBiomes.wetland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET
		);
		
		//WOODLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.woodland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.woodland.topBlock, BOPCBiomes.woodland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD
		);
		
		//XERIC SHRUBLAND
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.xericShrubland, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.HOT),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.xericShrubland.topBlock, BOPCBiomes.xericShrubland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.HOT
		);
		
		//CORALREEF WET
		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.coralReef, VanillaBiomes.climatizedBiome(BiomeGenBase.ocean, Climate.WET),
				new TerrainCanyon(false, -25f, 0f, 0f, 0f, 30f),
				new SurfaceGrassland(BOPCBiomes.coralReef.topBlock, BOPCBiomes.coralReef.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET	
		);

		//KELPFOREST COLD
 		Support.addBiome(
			new RealisticBiomeSupport(
				BOPCBiomes.kelpForest, VanillaBiomes.climatizedBiome(BiomeGenBase.ocean, Climate.TEMPERATE),
				new TerrainCanyon(false, -25f, 0f, 0f, 0f, 30f),
				new SurfaceGrassland(BOPCBiomes.kelpForest.topBlock, BOPCBiomes.kelpForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD	
		);		
		
	}
	//Biomes 'O Plenty Type support
	{
        BiomeDictionary.registerBiomeType(BOPCBiomes.alps, Type.SNOWY, Type.MOUNTAIN, Type.COLD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.alpsForest, Type.SNOWY, Type.MOUNTAIN, Type.FOREST, Type.COLD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.arctic, Type.SNOWY, Type.WASTELAND, Type.COLD, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bambooForest, Type.JUNGLE, Type.FOREST, Type.DENSE, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bayou, Type.SWAMP, Type.WATER, Type.LUSH, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bog, Type.SWAMP, Type.FOREST, Type.WET, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.boneyard, Type.NETHER, Type.WASTELAND, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.borealForest, Type.FOREST, Type.DENSE, Type.CONIFEROUS, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.brushland, Type.PLAINS, Type.DRY, Type.HOT, Type.SAVANNA);
        BiomeDictionary.registerBiomeType(BOPCBiomes.canyon, Type.SANDY, Type.MOUNTAIN, Type.HILLS, Type.SPARSE, Type.DRY, Type.HOT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.canyonRavine, Type.SANDY, Type.HILLS, Type.DRY, Type.HOT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.chaparral, Type.PLAINS, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.cherryBlossomGrove, Type.MAGICAL, Type.FOREST, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.coniferousForest, Type.FOREST, Type.HILLS, Type.CONIFEROUS, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.snowyConiferousForest, Type.SNOWY, Type.FOREST, Type.HILLS, Type.COLD, Type.CONIFEROUS, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.corruptedSands, Type.NETHER, Type.SANDY, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.crag, Type.WASTELAND, Type.MOUNTAIN, Type.SPOOKY, Type.DEAD, Type.DRY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deadForest, Type.FOREST, Type.DEAD, Type.SPARSE, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deadSwamp, Type.SWAMP, Type.DEAD, Type.SPARSE, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deciduousForest, Type.FOREST, Type.DENSE, Type.DRY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.denseForest, Type.FOREST, Type.DENSE, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.eucalyptusForest, Type.JUNGLE, Type.HOT, Type.DRY, Type.LUSH, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.fen, Type.FOREST, Type.SWAMP, Type.DEAD, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.flowerField, Type.PLAINS, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.frostForest, Type.SNOWY, Type.FOREST, Type.COLD, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.fungiForest, Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP, Type.LUSH, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.garden, Type.MAGICAL, Type.PLAINS, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.glacier, Type.SNOWY, Type.HILLS, Type.COLD, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.grassland, Type.PLAINS, Type.SWAMP, Type.HILLS, Type.SPARSE, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.grove, Type.FOREST, Type.PLAINS, Type.DENSE, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.heathland, Type.PLAINS, Type.DRY, Type.SAVANNA);
        BiomeDictionary.registerBiomeType(BOPCBiomes.highland, Type.HILLS, Type.MOUNTAIN, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.jadeCliffs, Type.FOREST, Type.MOUNTAIN, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.landOfLakes, Type.FOREST, Type.WATER, Type.DENSE, Type.WET, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.landOfLakesMarsh, Type.SWAMP, Type.WATER, Type.SPARSE, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lavenderFields, Type.PLAINS, Type.LUSH, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lushDesert, Type.SANDY, Type.SAVANNA, Type.DRY, Type.LUSH, Type.HOT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lushSwamp, Type.SWAMP, Type.WATER, Type.LUSH, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mangrove, Type.WATER, Type.FOREST, Type.WET, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mapleWoods, Type.FOREST, Type.COLD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.marsh, Type.SWAMP, Type.WATER, Type.WET, Type.SPARSE, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.meadow, Type.FOREST, Type.PLAINS, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.meadowForest, Type.FOREST, Type.PLAINS, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.moor, Type.HILLS, Type.SWAMP, Type.SPARSE, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mountain, Type.MOUNTAIN, Type.FOREST, Type.DRY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mysticGrove, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.oasis, Type.SANDY, Type.JUNGLE, Type.LUSH, Type.DRY, Type.HOT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.coralReef, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.kelpForest, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.ominousWoods, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.SWAMP, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.orchard, Type.FOREST, Type.PLAINS, Type.LUSH, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.outback, Type.SANDY, Type.PLAINS, Type.SAVANNA, Type.DRY, Type.HOT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.phantasmagoricInferno, Type.NETHER, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.polarChasm, Type.NETHER, Type.COLD, Type.SNOWY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.prairie, Type.PLAINS, Type.DRY, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.quagmire, Type.SWAMP, Type.WASTELAND, Type.SPOOKY, Type.WET, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.rainforest, Type.JUNGLE, Type.HILLS, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.redwoodForest, Type.FOREST, Type.CONIFEROUS, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.sacredSprings, Type.MOUNTAIN, Type.FOREST, Type.MAGICAL, Type.WET, Type.DENSE, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.scrubland, Type.SAVANNA, Type.PLAINS, Type.DRY, Type.HOT, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.seasonalForest, Type.FOREST, Type.LUSH, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.seasonalForestClearing, Type.FOREST, Type.LUSH, Type.SPARSE, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.shield, Type.FOREST, Type.WATER, Type.CONIFEROUS, Type.WET);
        BiomeDictionary.registerBiomeType(BOPCBiomes.shrubland, Type.PLAINS, Type.SPARSE, Type.DRY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.silkglades, Type.SWAMP, Type.FOREST, Type.SPOOKY, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.sludgepit, Type.SWAMP, Type.FOREST, Type.WASTELAND, Type.WET, Type.DEAD, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.spectralGarden, Type.END, Type.FOREST, Type.LUSH, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.spruceWoods, Type.FOREST, Type.CONIFEROUS, Type.LUSH, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.steppe, Type.PLAINS, Type.SANDY, Type.DRY, Type.HOT, Type.SAVANNA, Type.SPARSE, Type.DEAD);
        BiomeDictionary.registerBiomeType(BOPCBiomes.temperateRainforest, Type.FOREST, Type.HILLS, Type.WET, Type.CONIFEROUS, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.thicket, Type.PLAINS, Type.FOREST, Type.DRY, Type.DEAD, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.tropicalRainforest, Type.JUNGLE, Type.HOT, Type.WET, Type.LUSH, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.tropics, Type.JUNGLE, Type.BEACH, Type.WATER, Type.WET, Type.LUSH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.tundra, Type.COLD, Type.WASTELAND, Type.DRY, Type.DEAD, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.undergarden, Type.NETHER, Type.JUNGLE, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.visceralHeap, Type.NETHER, Type.SPOOKY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.volcano, Type.MOUNTAIN, Type.WASTELAND, Type.HOT, Type.DRY);
        BiomeDictionary.registerBiomeType(BOPCBiomes.wasteland, Type.WASTELAND, Type.SPOOKY, Type.DEAD, Type.SPARSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.wetland, Type.SWAMP, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.woodland, Type.FOREST, Type.DRY, Type.DENSE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.xericShrubland, Type.SANDY, Type.LUSH, Type.DRY, Type.HOT);
	}
}
