package rtg.world.biome.realistic.mithwoodforest;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mithwoodforest.config.BiomeConfigMFMithwoodForest;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.surface.mithwoodforest.SurfaceMFMithwoodForest;
import rtg.world.gen.terrain.mithwoodforest.TerrainMFMithwoodForest;

public class RealisticBiomeMFMithwoodForest extends RealisticBiomeMFBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState mithwoodLogBlock = Block.getBlockFromName("mithwoodforest:mithwood_log").getDefaultState();
    private static IBlockState mithwoodLeavesBlock = Block.getBlockFromName("mithwoodforest:mithwood_leaves").getDefaultState();

    public RealisticBiomeMFMithwoodForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMFMithwoodForest(),
            new SurfaceMFMithwoodForest(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getStateFromMeta(2), 0.10f)
        );
   
        TreeRTG megaMithwood = new TreeRTGPinusNigra();
        megaMithwood.logBlock = mithwoodLogBlock;
        megaMithwood.leavesBlock = mithwoodLeavesBlock;
        megaMithwood.minTrunkSize = 18;
        megaMithwood.maxTrunkSize = 27;
        megaMithwood.minCrownSize = 7;
        megaMithwood.maxCrownSize = 10;
        this.addTree(megaMithwood);

        DecoTree RTGMithwood = new DecoTree(megaMithwood);
        RTGMithwood.strengthFactorForLoops = 1f;
        RTGMithwood.strengthNoiseFactorXForLoops = true;
        RTGMithwood.distribution.noiseDivisor = 100f;
        RTGMithwood.distribution.noiseFactor = 6f;
        RTGMithwood.distribution.noiseAddend = 0.8f;
        RTGMithwood.treeType = TreeType.RTG_TREE;
        RTGMithwood.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGMithwood.treeConditionChance = 12;
        RTGMithwood.maxY = 100;
        this.addDeco(RTGMithwood);
        
        TreeRTG megaOak = new TreeRTGPinusNigra();
        megaOak.logBlock = Blocks.LOG.getDefaultState();
        megaOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        megaOak.minTrunkSize = 18;
        megaOak.maxTrunkSize = 27;
        megaOak.minCrownSize = 7;
        megaOak.maxCrownSize = 10;
        this.addTree(megaOak);

        DecoTree RTGOak = new DecoTree(megaOak);
        RTGOak.strengthFactorForLoops = 4f;
        RTGOak.strengthNoiseFactorXForLoops = true;
        RTGOak.distribution.noiseDivisor = 100f;
        RTGOak.distribution.noiseFactor = 6f;
        RTGOak.distribution.noiseAddend = 0.8f;
        RTGOak.treeType = TreeType.RTG_TREE;
        RTGOak.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGOak.treeConditionChance = 12;
        RTGOak.maxY = 100;
        this.addDeco(RTGOak);
        
        TreeRTG megaBirch = new TreeRTGBetulaPapyrifera();
        megaBirch.logBlock = Blocks.LOG.getStateFromMeta(2);
        megaBirch.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        megaBirch.minTrunkSize = 4;
        megaBirch.maxTrunkSize = 10;
        megaBirch.minCrownSize = 8;
        megaBirch.maxCrownSize = 19;
        this.addTree(megaBirch);

        DecoTree RTGBirch = new DecoTree(megaBirch);
        RTGBirch.strengthFactorForLoops = 2f;
        RTGBirch.treeType = DecoTree.TreeType.RTG_TREE;
        RTGBirch.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGBirch.treeConditionChance = 24;
        RTGBirch.maxY = 100;
        this.addDeco(RTGBirch);
        
        TreeRTG megaSpruce = new TreeRTGPinusPonderosa();
        megaSpruce.logBlock = Blocks.LOG.getStateFromMeta(1);
        megaSpruce.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        megaSpruce.minTrunkSize = 11;
        megaSpruce.maxTrunkSize = 21;
        megaSpruce.minCrownSize = 15;
        megaSpruce.maxCrownSize = 29;
        this.addTree(megaSpruce);

        DecoTree RTGSpruce = new DecoTree(megaSpruce);
        RTGSpruce.strengthFactorForLoops = 2f;
        RTGSpruce.strengthNoiseFactorForLoops = true;
        RTGSpruce.treeType = DecoTree.TreeType.RTG_TREE;
        RTGSpruce.distribution.noiseDivisor = 80f;
        RTGSpruce.distribution.noiseFactor = 60f;
        RTGSpruce.distribution.noiseAddend = -15f;
        RTGSpruce.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGSpruce.treeConditionNoise = 0f;
        RTGSpruce.treeConditionChance = 24;
        RTGSpruce.maxY = 140;
        this.addDeco(RTGSpruce);
           
        DecoShrub decoShrubMithwood = new DecoShrub();
        decoShrubMithwood.logBlock = mithwoodLogBlock;
        decoShrubMithwood.leavesBlock = mithwoodLeavesBlock;
        decoShrubMithwood.maxY = 100;
        decoShrubMithwood.strengthFactor = 1f;
        decoShrubMithwood.chance = 2;
        this.addDeco(decoShrubMithwood);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 4;
        this.addDeco(decoShrub);

        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
        decoFlowers1.flowers = new int[]{1, 2, 3, 6, 7, 8}; 
        decoFlowers1.strengthFactor = 2f;
        decoFlowers1.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; 
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.flowers = new int[]{11, 12, 13, 14};
        decoFlowers2.strengthFactor = 1f; 
        decoFlowers2.chance = 1;
        decoFlowers2.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; 
        this.addDeco(decoFlowers2);
     
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 2;
        decoBoulder.maxY = 100;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);
    
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
        decoBaseBiomeDecorations.notEqualsZeroChance = 8;
        this.addDeco(decoBaseBiomeDecorations);
         
        DecoFallenTree decoFallenMithwoodTree = new DecoFallenTree();
        decoFallenMithwoodTree.distribution.noiseDivisor = 100f;
        decoFallenMithwoodTree.distribution.noiseFactor = 6f;
        decoFallenMithwoodTree.distribution.noiseAddend = 0.8f;
        decoFallenMithwoodTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenMithwoodTree.logConditionNoise = 0f;
        decoFallenMithwoodTree.logConditionChance = 24;
        decoFallenMithwoodTree.logBlock = mithwoodLogBlock;
        decoFallenMithwoodTree.leavesBlock = mithwoodLeavesBlock;
        decoFallenMithwoodTree.minSize = 3;
        decoFallenMithwoodTree.maxSize = 6;
        this.addDeco(decoFallenMithwoodTree, this.config._boolean(BiomeConfigMFMithwoodForest.decorationLogsId));
 
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 8;
        decoFallenOak.maxY = 100;
        decoFallenOak.logBlock = Blocks.LOG.getDefaultState();
        decoFallenOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;
        this.addDeco(decoFallenOak, this.config._boolean(BiomeConfigMFMithwoodForest.decorationLogsId));

        DecoFallenTree decoFallenBirch = new DecoFallenTree();
        decoFallenBirch.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenBirch.logConditionChance = 8;
        decoFallenBirch.maxY = 100;
        decoFallenBirch.logBlock = Blocks.LOG.getStateFromMeta(2);
        decoFallenBirch.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        decoFallenBirch.minSize = 3;
        decoFallenBirch.maxSize = 6;
        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 8;
        decoFallenSpruce.maxY = 100;
        decoFallenSpruce.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoFallenSpruce.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenBirch, decoFallenSpruce);
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigMFMithwoodForest.decorationLogsId));

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
        
    }
}
