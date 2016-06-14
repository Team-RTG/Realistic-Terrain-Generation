package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLFlyingMountains;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoFlowersRTG.HeightType;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.biome.deco.helper.DecoHelperBorder;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGCupressusSempervirens;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;
import rtg.world.gen.surface.highlands.SurfaceHLFlyingMountains;
import rtg.world.gen.terrain.highlands.TerrainHLFlyingMountains;

public class RealisticBiomeHLFlyingMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.flyingMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLFlyingMountains(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLFlyingMountains(350f, 100f, 63f,70f),
            //new SurfaceVanillaForest(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f));
            new SurfaceHLFlyingMountains(config, topBlock, fillerBlock, false, null, 0f, 2.5f, 80f, 65f, 2.5f)
        );
        
        {
		TreeRTG sempervirensTree = new TreeRTGCupressusSempervirens();
		sempervirensTree.logBlock = Blocks.log;
		sempervirensTree.logMeta = (byte)1;
		sempervirensTree.leavesBlock = Blocks.leaves;
		sempervirensTree.leavesMeta = (byte)1;
		sempervirensTree.minTrunkSize = 3;
		sempervirensTree.maxTrunkSize = 7;
		sempervirensTree.minCrownSize = 5;
		sempervirensTree.maxCrownSize = 10;
		this.addTree(sempervirensTree);
        	
		DecoTree bigPines = new DecoTree(sempervirensTree);
		bigPines.loops = 1;
		bigPines.treeType = TreeType.RTG_TREE;
		bigPines.distribution.noiseDivisor = 80f;
		bigPines.distribution.noiseFactor = 60f;
		bigPines.distribution.noiseAddend = -15f;
		bigPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		bigPines.treeConditionNoise = 0f;
		bigPines.treeConditionChance = 1;
		bigPines.minY = 63;
		bigPines.maxY = 90;
		
		DecoTree bigPinesOak = new DecoTree(bigPines);
		bigPinesOak.leavesMeta = (byte)0;
		
        DecoHelperBorder borderedBigPines = new DecoHelperBorder(bigPines,.55f,.45f);
		this.addDeco(borderedBigPines);
		
        DecoHelperBorder borderedBigPinesOak = new DecoHelperBorder(bigPinesOak,.55f,.45f);
		this.addDeco(borderedBigPinesOak);

        DecoShrub decoShrub2 = new DecoShrub();
        decoShrub2.minY = 90;
        decoShrub2.maxY = 150;
        decoShrub2.strengthFactor = 3f;
        decoShrub2.minSize = 11;
        decoShrub2.maxSize = 21;
		this.addDeco(decoShrub2);
        }

        {
        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
		birchTree.logBlock = Blocks.log;
		birchTree.logMeta = (byte)2;
		birchTree.leavesBlock = Blocks.leaves;
		birchTree.leavesMeta = (byte)2;
		birchTree.minTrunkSize = 4;
		birchTree.maxTrunkSize = 10;
		birchTree.minCrownSize = 8;
		birchTree.maxCrownSize = 19;
		this.addTree(birchTree);
    		
		DecoTree birchTrees = new DecoTree(birchTree);
		birchTrees.loops = 2;
		birchTrees.distribution.noiseDivisor = 80f;
		birchTrees.distribution.noiseFactor = 60f;
		birchTrees.distribution.noiseAddend = -15f;
		birchTrees.treeType = TreeType.RTG_TREE;
		birchTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		birchTrees.minY = 63;
		birchTrees.maxY = 90;
        DecoHelperBorder borderedBirchTrees = new DecoHelperBorder(birchTrees,.55f,.45f);
        this.addDeco(borderedBirchTrees);
		
		DecoTree rtgTrees = new DecoTree(new WorldGenTreesRTG(false));
		rtgTrees.treeType = TreeType.WORLDGEN;
		rtgTrees.loops = 2;
		rtgTrees.distribution.noiseDivisor = 80f;
		rtgTrees.distribution.noiseFactor = 60f;
		rtgTrees.distribution.noiseAddend = -15f;
		rtgTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		rtgTrees.minY = 63;
		rtgTrees.maxY = 90;
        DecoHelperBorder borderedRTGTrees = new DecoHelperBorder(rtgTrees,.55f,.45f);
        this.addDeco(borderedRTGTrees);
		
		DecoTree vanillaTrees = new DecoTree(new WorldGenForest(false, false));
		vanillaTrees.treeType = TreeType.WORLDGEN;
		vanillaTrees.loops = 2;
		vanillaTrees.distribution.noiseDivisor = 80f;
		vanillaTrees.distribution.noiseFactor = 60f;
		vanillaTrees.distribution.noiseAddend = -15f;
		vanillaTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		vanillaTrees.minY = 63;
		vanillaTrees.maxY = 90;
        DecoHelperBorder borderedVanillaTrees = new DecoHelperBorder(vanillaTrees,.55f,.45f);
        this.addDeco(borderedVanillaTrees);
        }
        
        DecoFallenTree decoFallenTreeBirch = new DecoFallenTree();
        decoFallenTreeBirch.distribution.noiseDivisor = 80f;
        decoFallenTreeBirch.distribution.noiseFactor = 60f;
        decoFallenTreeBirch.distribution.noiseAddend = -15f;
        decoFallenTreeBirch.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTreeBirch.logConditionNoise = 8f;
        decoFallenTreeBirch.maxY = 100;
        decoFallenTreeBirch.logBlock = Blocks.log;
        decoFallenTreeBirch.logMeta = (byte)2;
        decoFallenTreeBirch.leavesBlock = Blocks.leaves;
        decoFallenTreeBirch.leavesMeta = (byte)-1;
        decoFallenTreeBirch.minSize = 3;
        decoFallenTreeBirch.maxSize = 6;
        
        DecoFallenTree decoFallenTreeSpruce = new DecoFallenTree();
        decoFallenTreeSpruce.distribution.noiseDivisor = 80f;
        decoFallenTreeSpruce.distribution.noiseFactor = 60f;
        decoFallenTreeSpruce.distribution.noiseAddend = -15f;
        decoFallenTreeSpruce.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTreeSpruce.logConditionNoise = 8f;
        decoFallenTreeSpruce.maxY = 100;
        decoFallenTreeSpruce.logBlock = Blocks.log;
        decoFallenTreeSpruce.logMeta = (byte)1;
        decoFallenTreeSpruce.leavesBlock = Blocks.leaves;
        decoFallenTreeSpruce.leavesMeta = (byte)-1;
        decoFallenTreeSpruce.minSize = 3;
        decoFallenTreeSpruce.maxSize = 6;
        
        DecoHelper5050 DecoHelper5050 = new DecoHelper5050(decoFallenTreeBirch, decoFallenTreeSpruce);
		this.addDeco(DecoHelper5050, this.config._boolean(BiomeConfigHLFlyingMountains.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 180;
        decoShrub.strengthFactor = 8f;
		this.addDeco(decoShrub);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.heightType = HeightType.NEXT_INT;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 164;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
