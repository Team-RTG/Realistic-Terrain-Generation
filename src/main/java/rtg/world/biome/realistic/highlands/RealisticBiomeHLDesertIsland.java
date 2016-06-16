package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLDesertIsland;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.highlands.HLPalmTreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.world.gen.surface.highlands.SurfaceHLDesertIsland;
import rtg.world.gen.terrain.highlands.TerrainHLDesertIsland;

public class RealisticBiomeHLDesertIsland extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.desertIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLDesertIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLDesertIsland(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLDesertIsland(config, topBlock, fillerBlock)
        );
        this.disallowStoneBeaches = true;
        this.waterSurfaceLakeChance = 0;
		
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
		highlandsPalmTrees.treeConditionNoise = 0f;
		highlandsPalmTrees.treeConditionChance = 4;
		highlandsPalmTrees.maxY = 75;
		this.addDeco(highlandsPalmTrees);
		
		TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
		nuciferaTree.logBlock = Blocks.log;
		nuciferaTree.logMeta = (byte)3;
		nuciferaTree.leavesBlock = Blocks.leaves;
		nuciferaTree.leavesMeta = (byte)3;
		nuciferaTree.minTrunkSize = 7;
		nuciferaTree.maxTrunkSize = 8;
		nuciferaTree.minCrownSize = 8;
		nuciferaTree.maxCrownSize = 12;
		nuciferaTree.noLeaves = false;
		this.addTree(nuciferaTree);
		
		DecoTree vanillaPalmTrees = new DecoTree(nuciferaTree);
		vanillaPalmTrees.treeType = DecoTree.TreeType.RTG_TREE;
		vanillaPalmTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		vanillaPalmTrees.distribution = new DecoTree.Distribution(80f, 60f, -15f);
		vanillaPalmTrees.treeConditionNoise = 0f;
		vanillaPalmTrees.treeConditionChance = 8;
		vanillaPalmTrees.maxY = 75;
		this.addDeco(vanillaPalmTrees); 
		
		// Jungle logs.
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution = new DecoFallenTree.Distribution(80f, 60f, -15f);
		decoFallenTree.logCondition = LogCondition.NOISE_LESS_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 24;
		decoFallenTree.maxY = 70;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)3;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLDesertIsland.decorationLogsId));
        noLakes = true;
        noWaterFeatures = true;
    }
}
