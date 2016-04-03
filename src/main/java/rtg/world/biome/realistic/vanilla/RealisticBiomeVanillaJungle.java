package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungle;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoJungleCacti;
import rtg.world.biome.deco.DecoJungleGrassVines;
import rtg.world.biome.deco.DecoJungleLilypadVines;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungle;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungle;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.jungle.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungle.fillerBlock;
	
	public RealisticBiomeVanillaJungle(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.jungle,
			BiomeGenBase.river,
			new TerrainVanillaJungle(),
			new SurfaceVanillaJungle(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.09f)
		);
		
		this.waterSurfaceLakeChance = 3;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */

		// Blend of the WorldGenMegaJungle collection and some tall RTG Mangrove trees.
		DecoTree megaJungleMangrove = new DecoTree();
		megaJungleMangrove.loops = 3;
		megaJungleMangrove.treeType = TreeType.MEGA_JUNGLE_MANGROVE;
		megaJungleMangrove.treeCondition = TreeCondition.RANDOM_CHANCE;
		megaJungleMangrove.treeConditionChance = 2;
		megaJungleMangrove.maxY = 160;
		this.addDeco(megaJungleMangrove);
		
		// Add some palm trees for variety.
		DecoTree palmCustom = new DecoTree();
		palmCustom.loops = 1;
		palmCustom.treeType = TreeType.PALM_CUSTOM;
		palmCustom.treeCondition = TreeCondition.RANDOM_CHANCE;
		palmCustom.treeConditionChance = 3;
		palmCustom.maxY = 160;
		palmCustom.minSize = 10;
		palmCustom.maxSize = 20;
		this.addDeco(palmCustom);
		
		// Another pass of the WorldGenMegaJungle collection for extra jungleness.
		DecoTree megaJungle = new DecoTree();
		megaJungle.loops = 3;
		megaJungle.treeType = TreeType.MEGA_JUNGLE;
		megaJungle.treeCondition = TreeCondition.RANDOM_CHANCE;
		megaJungle.treeConditionChance = 3;
		megaJungle.maxY = 160;
		megaJungle.minSize = 20;
		megaJungle.maxSize = 40;
		this.addDeco(megaJungle);
		
		// Jungle logs.
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 5f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 3;
		decoFallenTree.maxY = 120;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)3;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 9;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaJungle.decorationLogsId));
		
		// At this point, let's hand over some of the decoration to the base biome, but only about 85% of the time.
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 6;
		decoBaseBiomeDecorations.loops = 1;
		this.addDeco(decoBaseBiomeDecorations);
		
		// A combo-deal of lilypads and vines. (This could probably be pulled out into individual decos.)
		DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
		this.addDeco(decoJungleLilypadVines);
		
		// A combo-deal of grass and vines. (This could probably be pulled out into individual decos.)
		DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
		this.addDeco(decoJungleLilypadVines);
		
		// Flowers.
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{5}; // Only orange tulips fit in with the colour scheme.
        decoFlowersRTG.chance = 4;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);
        
        // Tall cacti on red sand - matches the colour scheme nicely.
        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
		decoJungleCacti.strengthFactor = 8f;
		decoJungleCacti.maxY = 120;
		decoJungleCacti.sandOnly = false;
		decoJungleCacti.extraHeight = 7;
		decoJungleCacti.sandMeta = (byte)1;
		this.addDeco(decoJungleCacti, this.config._boolean(BiomeConfigVanillaJungle.decorationCactusId));
		
        // Mossy boulders for the green.
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		// Grass filler.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
	}
}
