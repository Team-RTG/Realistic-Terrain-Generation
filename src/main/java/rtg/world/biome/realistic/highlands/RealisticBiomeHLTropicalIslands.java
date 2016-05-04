package rtg.world.biome.realistic.highlands;

import highlands.Highlands;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLTropicalIslands;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoJungleLilypadVines;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.highlands.HLPalmTreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.world.gen.surface.highlands.SurfaceHLTropicalIslands;
import rtg.world.gen.terrain.highlands.TerrainHLTropicalIslands;

public class RealisticBiomeHLTropicalIslands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tropicalIslands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTropicalIslands(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLTropicalIslands(90f, 180f, 13f, 100f, 1f, 260f, 59f),
            new SurfaceHLTropicalIslands(config, topBlock, fillerBlock)
        );
        this.waterSurfaceLakeChance = 4;
		
        /**
         * We can't let the base biome decorate itself because of a console spam bug with the HL Cocoa plants.
         * 
         * DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
         * this.addDeco(decoBaseBiomeDecorations);
         */

		DecoTree highlandsPalmTrees = new DecoTree(new HLPalmTreeRTG(8, 7, false));
		highlandsPalmTrees.treeType = TreeType.WORLDGEN;
		highlandsPalmTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		highlandsPalmTrees.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);
		highlandsPalmTrees.treeConditionNoise = -0.4f;
		highlandsPalmTrees.treeConditionChance = 1;
		highlandsPalmTrees.maxY = 160;
		this.addDeco(highlandsPalmTrees);
		
		DecoTree vanillaPalmTrees = new DecoTree(new TreeRTGCocosNucifera());
		vanillaPalmTrees.treeType = DecoTree.TreeType.RTG_TREE;
		vanillaPalmTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		vanillaPalmTrees.distribution = new DecoTree.Distribution(80f, 60f, -15f);
		vanillaPalmTrees.treeConditionNoise = 0f;
		vanillaPalmTrees.treeConditionChance = 4;
		vanillaPalmTrees.logBlock = Blocks.log;
		vanillaPalmTrees.logMeta = (byte)3;
		vanillaPalmTrees.leavesBlock = Blocks.leaves;
		vanillaPalmTrees.leavesMeta = (byte)3;
		vanillaPalmTrees.minTrunkSize = 7;
		vanillaPalmTrees.maxTrunkSize = 8;
		vanillaPalmTrees.minCrownSize = 8;
		vanillaPalmTrees.maxCrownSize = 12;
		vanillaPalmTrees.noLeaves = false;
		this.addDeco(vanillaPalmTrees); 
		
        DecoShrub decoShrubVanilla = new DecoShrub();
        decoShrubVanilla.logBlock = Blocks.log;
        decoShrubVanilla.logMeta = (byte)3;
        decoShrubVanilla.leavesBlock = Blocks.leaves;
        decoShrubVanilla.leavesMeta = (byte)3;
        decoShrubVanilla.maxY = 100;
        decoShrubVanilla.strengthFactor = 4f;
        decoShrubVanilla.chance = 3;
        
        DecoShrub decoShrubHL = new DecoShrub();
        decoShrubHL.logBlock = Highlands.vanillaBlocksFlag ? Blocks.log : HighlandsBlocks.palmWood;
        decoShrubHL.logMeta = Highlands.vanillaBlocksFlag ? (byte)3 : (byte)0;
        decoShrubHL.leavesBlock = Highlands.vanillaBlocksFlag ? Blocks.leaves : HighlandsBlocks.palmLeaves;
        decoShrubHL.leavesMeta = Highlands.vanillaBlocksFlag ? (byte)3 : (byte)0;
        decoShrubHL.maxY = 100;
        decoShrubHL.strengthFactor = 4f;
        decoShrubHL.chance = 3;
        
		this.addDeco(new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, decoShrubVanilla, decoShrubHL));
		
		// Jungle logs.
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 24;
		decoFallenTree.maxY = 120;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)3;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 8;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLTropicalIslands.decorationLogsId));
		
		// A combo-deal of lilypads and vines. (This could probably be pulled out into individual decos.)
		DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
		this.addDeco(decoJungleLilypadVines);
		
		// Flowers.
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}; // Only orange tulips fit in with the colour scheme.
        decoFlowersRTG.chance = 3;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 4f;
        this.addDeco(decoFlowersRTG);
		
        // Mossy boulders for the green.
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 36;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);
		
		DecoGrass decoFern = new DecoGrass(2);
		decoFern.maxY = 128;
		decoFern.strengthFactor = 10f;
        this.addDeco(decoFern);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 4f;
        this.addDeco(decoGrass);
    }

    @Override
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // no rivers or lakes
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }

}
