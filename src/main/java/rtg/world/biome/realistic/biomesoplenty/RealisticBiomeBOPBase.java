package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceCanyon;
import rtg.world.gen.surface.SurfaceDuneValley;
import rtg.world.gen.surface.SurfaceGrassland;
import rtg.world.gen.surface.SurfaceGrasslandMix1;
import rtg.world.gen.surface.SurfaceMountainSnow;
import rtg.world.gen.surface.SurfaceMountainStone;
import rtg.world.gen.surface.SurfaceTundra;
import rtg.world.gen.surface.river.SurfaceRiverOasis;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainCanyon;
import rtg.world.gen.terrain.TerrainDuneValley;
import rtg.world.gen.terrain.TerrainGrasslandFlats;
import rtg.world.gen.terrain.TerrainGrasslandHills;
import rtg.world.gen.terrain.TerrainHighland;
import rtg.world.gen.terrain.TerrainHilly;
import rtg.world.gen.terrain.TerrainMarsh;
import rtg.world.gen.terrain.TerrainMountain;
import rtg.world.gen.terrain.TerrainMountainRiver;
import rtg.world.gen.terrain.TerrainMountainSpikes;
import rtg.world.gen.terrain.TerrainSwampMountain;
import rtg.world.gen.terrain.TerrainSwampRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

public class RealisticBiomeBOPBase extends RealisticBiomeBase
{	
	public RealisticBiomeBOPBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public RealisticBiomeBOPBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void init()
	{
		//ALPS
		if (ConfigBOP.generateBOPalps)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.alps, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.alps.topBlock, BOPCBiomes.alps.fillerBlock, false, null, 0.45f)
			),
			BiomeBase.BiomeCategory.SNOW
		);
		
		//ALPS FOREST
		if (ConfigBOP.generateBOPalpsForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.alpsForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.alpsForest.topBlock, BOPCBiomes.alpsForest.fillerBlock, false, null, 0.45f)
			),
			BiomeBase.BiomeCategory.SNOW
		);
		
		//ARCTIC
		if (ConfigBOP.generateBOParctic)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.arctic, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainGrasslandFlats(),
				new SurfaceTundra(BOPCBiomes.arctic.topBlock, BOPCBiomes.arctic.fillerBlock)
			),
			BiomeBase.BiomeCategory.SNOW
		);
		
		//BAMBOO FOREST
		if (ConfigBOP.generateBOPbambooForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.bambooForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainSwampMountain(135f, 300f),
				new SurfaceMountainStone(BOPCBiomes.bambooForest.topBlock, BOPCBiomes.bambooForest.fillerBlock, false, null, 0.95f)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//BAYOU
		if (ConfigBOP.generateBOPbayou)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.bayou, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.bayou.topBlock, BOPCBiomes.bayou.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//BOG
		if (ConfigBOP.generateBOPbog)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.bog, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.bog.topBlock, BOPCBiomes.bog.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//BOREAL FOREST
		if (ConfigBOP.generateBOPborealForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.borealForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.borealForest.topBlock, BOPCBiomes.borealForest.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//BRUSHLAND
		if (ConfigBOP.generateBOPbrushland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.brushland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrasslandMix1(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 13f, 0.27f)
			),
			BiomeBase.BiomeCategory.HOT
		);
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.brushland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, 300f, false, true),
					new SurfaceRiverOasis(),
				}
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//CANYON
		if (ConfigBOP.generateBOPcanyon)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.canyon, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainCanyon(true, 35f, 160f, 60f, 40f, 69f),
				new SurfaceCanyon(BOPCBiomes.canyon.topBlock, BOPCBiomes.canyon.fillerBlock, (byte)0, 0)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//CANYON RAVINE
		if (ConfigBOP.generateBOPcanyonRavine)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.canyonRavine, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainCanyon(true, 35f, 160f, 60f, 40f, 69f),
				new SurfaceCanyon(BOPCBiomes.canyonRavine.topBlock, BOPCBiomes.canyonRavine.fillerBlock, (byte)0, 0)
			),
			BiomeBase.BiomeCategory.HOT
		); 
		
		//CHAPARRAL
		if (ConfigBOP.generateBOPchaparral)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.chaparral, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrasslandMix1(BOPCBiomes.chaparral.topBlock, BOPCBiomes.chaparral.fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 26f, 0.35f)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//CHERRYBLOSSOM GROVE
		if (ConfigBOP.generateBOPcherryBlossomGrove)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.cherryBlossomGrove, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(6f, 120f, 65f, 200f),
				new SurfaceMountainStone(BOPCBiomes.borealForest.topBlock, BOPCBiomes.borealForest.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 1.5f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//CONIFEROUS FOREST
		if (ConfigBOP.generateBOPconiferousForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.coniferousForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.snowyConiferousForest.topBlock, BOPCBiomes.snowyConiferousForest.fillerBlock, false, null, 0.45f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		if (ConfigBOP.generateBOPsnowyConiferousForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.snowyConiferousForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(BOPCBiomes.snowyConiferousForest.topBlock, BOPCBiomes.snowyConiferousForest.fillerBlock, false, null, 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
			),
			BiomeBase.BiomeCategory.SNOW
		);
		
		//CRAG
		if (ConfigBOP.generateBOPcrag)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.crag, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainMountain(),
				new SurfaceGrassland(BOPCBiomes.crag.topBlock, BOPCBiomes.crag.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//DEAD FOREST
		if (ConfigBOP.generateBOPdeadForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.deadForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainGrasslandHills(50f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.deadForest.topBlock, BOPCBiomes.deadForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//DEAD SWAMP
		if (ConfigBOP.generateBOPdeadSwamp)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.deadSwamp, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.deadSwamp.topBlock, BOPCBiomes.deadSwamp.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//DECIDUOUS FOREST
		if (ConfigBOP.generateBOPdeciduousForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.deciduousForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.deciduousForest.topBlock, BOPCBiomes.deciduousForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//DENSE FOREST
		if (ConfigBOP.generateBOPdenseForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.denseForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.denseForest.topBlock, BOPCBiomes.denseForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
	    	),
			BiomeBase.BiomeCategory.COLD
		);
		
		//EUCALYPTUS FOREST
		if (ConfigBOP.generateBOPeucalyptusForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.eucalyptusForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainHighland(0f, 180f, 68f, 120f),
				new SurfaceGrassland(BOPCBiomes.eucalyptusForest.topBlock, BOPCBiomes.eucalyptusForest.fillerBlock, Blocks.stone, Blocks.cobblestone)						
			),
			BiomeBase.BiomeCategory.WET
		); 
		
		//FEN
		if (ConfigBOP.generateBOPfen)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.fen, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.fen.topBlock, BOPCBiomes.fen.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//FLOWER FIELD
		if (ConfigBOP.generateBOPflowerField)
		BiomeBase.biomes_hot.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.flowerField, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainGrasslandHills(40f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.flowerField.topBlock, BOPCBiomes.flowerField.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//FROST FOREST
		if (ConfigBOP.generateBOPfrostForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.frostForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.frostForest.topBlock, BOPCBiomes.frostForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.SNOW
		);
		
		//FUNGI FOREST
		if (ConfigBOP.generateBOPfungiForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.fungiForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainSwampMountain(135f, 300f),
				new SurfaceMountainStone(BOPCBiomes.fungiForest.topBlock, BOPCBiomes.fungiForest.fillerBlock, false, null, 0.95f)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//GARDEN
		if (ConfigBOP.generateBOPgarden)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.garden, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.garden.topBlock, BOPCBiomes.garden.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//GLACIER
		if (ConfigBOP.generateBOPglacier)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.glacier, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.glacier.topBlock, BOPCBiomes.glacier.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			),
			BiomeBase.BiomeCategory.SNOW
		);
		
		//GRASSLAND
		if (ConfigBOP.generateBOPgrassland)
		BiomeBase.biomes_cold.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.grassland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainGrasslandHills(47f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.grassland.topBlock, BOPCBiomes.grassland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//GROVE
		if (ConfigBOP.generateBOPgrove)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.grove, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.grove.topBlock, BOPCBiomes.grove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//HEATHLAND
		if (ConfigBOP.generateBOPheathland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.heathland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, 300f, false, true) ,
					new SurfaceRiverOasis(),
				}
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//HIGHLAND
		if (ConfigBOP.generateBOPhighland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.highland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 150f),
				new SurfaceMountainStone(BOPCBiomes.highland.topBlock, BOPCBiomes.highland.fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//JADE CLIFFS
		if (ConfigBOP.generateBOPjadeCliffs)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.jadeCliffs, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainHilly(230f, 120f, 0f),
				new SurfaceMountainStone(BOPCBiomes.jadeCliffs.topBlock, BOPCBiomes.jadeCliffs.fillerBlock, false, null, 0.95f)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//LAND OF LAKES
		if (ConfigBOP.generateBOPlandOfLakes)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.landOfLakes, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainGrasslandFlats(),
				new SurfaceGrassland(BOPCBiomes.landOfLakes.topBlock, BOPCBiomes.landOfLakes.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);		
		
        //LAND OF LAKES MARCH
		if (ConfigBOP.generateBOPlandOfLakesMarsh)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.landOfLakesMarsh, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.landOfLakes.topBlock, BOPCBiomes.landOfLakes.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);		
		
		//LAVENDER FIELDS
		if (ConfigBOP.generateBOPlavenderFields)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.lavenderFields, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMountainSpikes(),
				new SurfaceMountainStone(BOPCBiomes.lavenderFields.topBlock, BOPCBiomes.lavenderFields.fillerBlock, false, null, 1.2f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//LUSH DESERT
		if (ConfigBOP.generateBOPlushDesert)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.lushDesert, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.lushDesert.topBlock, BOPCBiomes.lushDesert.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//LUSH SWAMP
		if (ConfigBOP.generateBOPlushSwamp)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.lushSwamp, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.lushSwamp.topBlock, BOPCBiomes.lushSwamp.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//MANGROVE
		if (ConfigBOP.generateBOPmangrove)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.mangrove, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.mangrove.topBlock, BOPCBiomes.mangrove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//MAPLE WOODS
		if (ConfigBOP.generateBOPmapleWoods)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.mapleWoods, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.mapleWoods.topBlock, BOPCBiomes.mapleWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//MARSH
		if (ConfigBOP.generateBOPmarsh)
		BiomeBase.biomes_cold.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.marsh, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.marsh.topBlock, BOPCBiomes.marsh.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//MEADOW
		if (ConfigBOP.generateBOPmeadow)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.meadow, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMountainSpikes(),
				new SurfaceMountainStone(BOPCBiomes.meadow.topBlock, BOPCBiomes.meadow.fillerBlock, false, null, 1.2f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//MEADOW FOREST
		if (ConfigBOP.generateBOPmeadowForest)
		BiomeBase.addBiome(
				new RealisticBiomeBOPBase(
					BOPCBiomes.meadowForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
					new TerrainMountainSpikes(),
					new SurfaceMountainStone(BOPCBiomes.meadowForest.topBlock, BOPCBiomes.meadowForest.fillerBlock, false, null, 1.2f)
				),
				BiomeBase.BiomeCategory.COLD
			);
		
		//MOOR
		if (ConfigBOP.generateBOPmoor)
		BiomeBase.biomes_wet.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.moor, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 70f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.moor.topBlock, BOPCBiomes.moor.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//MOUNTAIN
		if (ConfigBOP.generateBOPmountain)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.mountain, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainMountainRiver(),
				new SurfaceMountainStone(BOPCBiomes.mountain.topBlock, BOPCBiomes.mountain.fillerBlock, true, Blocks.sand, 0.75f)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//MYSTIC GROVE
		if (ConfigBOP.generateBOPmysticGrove)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.mysticGrove, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.mysticGrove.topBlock, BOPCBiomes.mysticGrove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//OASIS
		if (ConfigBOP.generateBOPoasis)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.oasis, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
				new TerrainGrasslandFlats(),
				new SurfaceGrassland(BOPCBiomes.oasis.topBlock, BOPCBiomes.oasis.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);		
		
		//OMINOUS WOODS
		if (ConfigBOP.generateBOPominousWoods)
		BiomeBase.biomes_cold.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.ominousWoods, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.ominousWoods.topBlock, BOPCBiomes.ominousWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//ORCHARD
		if (ConfigBOP.generateBOPoriginValley)
		BiomeBase.biomes_cold.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.orchard, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainVanillaPlains(),
				new SurfaceGrassland(BOPCBiomes.orchard.topBlock, BOPCBiomes.orchard.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//ORIGIN VALLEY
		if (ConfigBOP.generateBOPoriginValley)
		BiomeBase.biomes_cold.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.originValley, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.originValley.topBlock, BOPCBiomes.originValley.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//OUTBACK
		if (ConfigBOP.generateBOPoutback)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.outback, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.outback.topBlock, BOPCBiomes.outback.fillerBlock, 300f, false, false),
					new SurfaceRiverOasis(),
				}
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//PRAIRIE
		if (ConfigBOP.generateBOPprairie)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.prairie, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.prairie.topBlock, BOPCBiomes.prairie.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//QUAGMIRE
		if (ConfigBOP.generateBOPquagmire)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.quagmire, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMountain(),
				new SurfaceGrassland(BOPCBiomes.quagmire.topBlock, BOPCBiomes.quagmire.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
	
		//RAINFOREST
		if (ConfigBOP.generateBOPrainforest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.rainforest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainSwampMountain(120f, 300f),
				new SurfaceMountainStone(BOPCBiomes.rainforest.topBlock, BOPCBiomes.rainforest.fillerBlock, false, null, 1.3f)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//REDWOOD FOREST
		if (ConfigBOP.generateBOPredwoodForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.redwoodForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainGrasslandHills(80f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceMountainStone(BOPCBiomes.redwoodForest.topBlock, BOPCBiomes.redwoodForest.fillerBlock, false, null, 0.4f)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//SACRED SPRINGS
		if (ConfigBOP.generateBOPsacredSprings)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.sacredSprings, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainHighland(0f, 120f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.sacredSprings.topBlock, BOPCBiomes.sacredSprings.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);

		//SCRUBLAND
		if (ConfigBOP.generateBOPsacredSprings)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.scrubland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainVanillaPlains(),
				new SurfaceGrassland(BOPCBiomes.scrubland.topBlock, BOPCBiomes.scrubland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//SEASONAL FOREST
		if (ConfigBOP.generateBOPseasonalForest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.seasonalForest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.seasonalForest.topBlock, BOPCBiomes.seasonalForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//SEASONAL FOREST CLEARING
		if (ConfigBOP.generateBOPseasonalForestClearing)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.seasonalForestClearing, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 170f),
				new SurfaceGrassland(BOPCBiomes.seasonalForestClearing.topBlock, BOPCBiomes.seasonalForestClearing.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
				
		//SHIELD
		if (ConfigBOP.generateBOPshield)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.shield, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.shield.topBlock, BOPCBiomes.shield.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//SHRUBLAND
		if (ConfigBOP.generateBOPshrubland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.shrubland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainGrasslandHills(70f, 150f, 13f, 90f, 38f, 200f, 71f),
				new SurfaceGrassland(BOPCBiomes.shrubland.topBlock, BOPCBiomes.shrubland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
			
		//SILKGLADES
		if (ConfigBOP.generateBOPsilkglades)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.silkglades, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.silkglades.topBlock, BOPCBiomes.silkglades.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//SLUDGEPIT
		if (ConfigBOP.generateBOPsludgepit)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.sludgepit, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.sludgepit.topBlock, BOPCBiomes.sludgepit.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);	
		
		//SPURCEWOODS
		if (ConfigBOP.generateBOPsludgepit)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.spruceWoods, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainVanillaForest(),
				new SurfaceGrassland(BOPCBiomes.spruceWoods.topBlock, BOPCBiomes.spruceWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);		
		
		//STEPPE
		if (ConfigBOP.generateBOPsteppe)
		BiomeBase.biomes_hot.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.steppe, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainGrasslandHills(70f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.steppe.topBlock, BOPCBiomes.steppe.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//TEMPERATE RAINFOREST
		if (ConfigBOP.generateBOPtemperateRainforest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.temperateRainforest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainMountainRiver(),
				new SurfaceMountainStone(BOPCBiomes.temperateRainforest.topBlock, BOPCBiomes.temperateRainforest.fillerBlock, false, null, 0.45f)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//THICKET
		if (ConfigBOP.generateBOPthicket)
		BiomeBase.biomes_hot.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.thicket, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
				new TerrainGrasslandHills(70f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.thicket.topBlock, BOPCBiomes.thicket.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//TROPICAL RAINFOREST
		if (ConfigBOP.generateBOPtropicalRainforest)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.tropicalRainforest, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.tropicalRainforest.topBlock, BOPCBiomes.tropicalRainforest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//TROPICS
		if (ConfigBOP.generateBOPtropics)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.tropics, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.tropics.topBlock, BOPCBiomes.tropics.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//TUNDRA
		if (ConfigBOP.generateBOPtundra)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.tundra, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.tundra.topBlock, BOPCBiomes.tundra.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.SNOW
		);

		//VOLCANO
		if (ConfigBOP.generateBOPvolcano)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.volcano, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.volcano.topBlock, BOPCBiomes.volcano.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		//WASTELAND
		if (ConfigBOP.generateBOPwasteland)
		BiomeBase.biomes_hot.add(
			new RealisticBiomeBOPBase(
				BOPCBiomes.wasteland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.wasteland.topBlock, BOPCBiomes.wasteland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//WETLAND
		if (ConfigBOP.generateBOPwetland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.wetland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.wetland.topBlock, BOPCBiomes.wetland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET
		);
		
		//WOODLAND
		if (ConfigBOP.generateBOPwoodland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.woodland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.woodland.topBlock, BOPCBiomes.woodland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD
		);
		
		//XERIC SHRUBLAND
		if (ConfigBOP.generateBOPxericShrubland)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.xericShrubland, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.xericShrubland.topBlock, BOPCBiomes.xericShrubland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.HOT
		);
		
		/** WATER BIOMES */
		//CORALREEF
		if (ConfigBOP.generateBOPcoralReef)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.coralReef, BiomeBase.climatizedBiome(BiomeGenBase.ocean, BiomeBase.Climate.WET),
				new TerrainCanyon(false, -25f, 0f, 0f, 0f, 30f),
				new SurfaceGrassland(BOPCBiomes.coralReef.topBlock, BOPCBiomes.coralReef.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.WET	
		);

		//KELPFOREST
		if (ConfigBOP.generateBOPkelpForest)
 		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.kelpForest, BiomeBase.climatizedBiome(BiomeGenBase.ocean, BiomeBase.Climate.TEMPERATE),
				new TerrainCanyon(false, -25f, 0f, 0f, 0f, 30f),
				new SurfaceGrassland(BOPCBiomes.kelpForest.topBlock, BOPCBiomes.kelpForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeBase.BiomeCategory.COLD	
		);	
		
		//DRY RIVER
/*		if (ConfigBOP.generateBOPcoralReef)
		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.dryRiver, VanillaBiomes.climatizedBiome(BiomeGenBase.ocean, Climate.WET),
				new TerrainCanyon(false, -25f, 0f, 0f, 0f, 30f),
				new SurfaceGrassland(BOPCBiomes.dryRiver.topBlock, BOPCBiomes.dryRiver.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.WET	
		);
*/
		//LUSH RIVER
/*		if (ConfigBOP.generateBOPkelpForest)
 		BiomeBase.addBiome(
			new RealisticBiomeBOPBase(
				BOPCBiomes.lushRiver, VanillaBiomes.climatizedBiome(BiomeGenBase.ocean, Climate.TEMPERATE),
				new TerrainCanyon(false, -25f, 0f, 0f, 0f, 30f),
				new SurfaceGrassland(BOPCBiomes.lushRiver.topBlock, BOPCBiomes.lushRiver.fillerBlock, Blocks.stone, Blocks.cobblestone)
			),
			BiomeCategory.COLD	
		);	
*/		
		
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
