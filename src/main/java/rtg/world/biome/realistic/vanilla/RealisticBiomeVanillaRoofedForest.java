package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaRosea;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRoofedForest;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.roofedForest.topBlock;
    public static Block fillerBlock = BiomeGenBase.roofedForest.fillerBlock;
    
    public RealisticBiomeVanillaRoofedForest(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.roofedForest,
            BiomeGenBase.river,
            new TerrainVanillaRoofedForest(),
            new SurfaceVanillaRoofedForest(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.08f)
        );
        this.waterSurfaceLakeChance = 3;

		DecoMushrooms decoMushrooms = new DecoMushrooms();
		decoMushrooms.chance = 4;
		decoMushrooms.maxY = 90;
		decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
		this.addDeco(decoMushrooms);
		
        DecoTree mangroveTree = new DecoTree(new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f));
        mangroveTree.treeType = DecoTree.TreeType.RTG_TREE;
        mangroveTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        mangroveTree.treeConditionChance = 1;
        mangroveTree.strengthFactorForLoops = 8f;
        mangroveTree.logBlock = Blocks.log2;
        mangroveTree.logMeta = (byte)1;
        mangroveTree.leavesBlock = Blocks.leaves2;
        mangroveTree.leavesMeta = (byte)1;
        mangroveTree.minTrunkSize = 2;
        mangroveTree.maxTrunkSize = 3;
        mangroveTree.minCrownSize = 10;
        mangroveTree.maxCrownSize = 18;
        mangroveTree.noLeaves = false;
        mangroveTree.maxY = 110;
        this.addDeco(mangroveTree);
        
        DecoTree ceibaPentandraTree = new DecoTree(new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f));
        ceibaPentandraTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaPentandraTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaPentandraTree.treeConditionChance = 1;
        ceibaPentandraTree.strengthFactorForLoops = 8f;
        ceibaPentandraTree.logBlock = Blocks.log2;
        ceibaPentandraTree.logMeta = (byte)1;
        ceibaPentandraTree.leavesBlock = Blocks.leaves2;
        ceibaPentandraTree.leavesMeta = (byte)1;
        ceibaPentandraTree.minTrunkSize = 2;
        ceibaPentandraTree.maxTrunkSize = 3;
        ceibaPentandraTree.minCrownSize = 10;
        ceibaPentandraTree.maxCrownSize = 18;
        ceibaPentandraTree.noLeaves = false;
        ceibaPentandraTree.maxY = 110;
        this.addDeco(ceibaPentandraTree);
        
        DecoTree ceibaRoseaTree = new DecoTree(new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f));
        ceibaRoseaTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaRoseaTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaRoseaTree.treeConditionChance = 1;
        ceibaRoseaTree.strengthFactorForLoops = 8f;
        ceibaRoseaTree.logBlock = Blocks.log2;
        ceibaRoseaTree.logMeta = (byte)1;
        ceibaRoseaTree.leavesBlock = Blocks.leaves2;
        ceibaRoseaTree.leavesMeta = (byte)1;
        ceibaRoseaTree.minTrunkSize = 2;
        ceibaRoseaTree.maxTrunkSize = 3;
        ceibaRoseaTree.minCrownSize = 10;
        ceibaRoseaTree.maxCrownSize = 18;
        ceibaRoseaTree.noLeaves = false;
        ceibaRoseaTree.maxY = 110;
        this.addDeco(ceibaRoseaTree);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionChance = 16;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 9;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaRoofedForest.decorationLogsId));
        
		DecoShrub darkOakShrub = new DecoShrub();
		darkOakShrub.logBlock = Blocks.log2;
		darkOakShrub.logMeta = (byte)1;
		darkOakShrub.leavesBlock = Blocks.leaves2;
		darkOakShrub.leavesMeta = (byte)1;
		darkOakShrub.maxY = 100;
		darkOakShrub.strengthFactor = 10f;
		
		DecoShrub oakShrub = new DecoShrub();
		oakShrub.logBlock = Blocks.log;
		oakShrub.logMeta = (byte)0;
		oakShrub.leavesBlock = Blocks.leaves;
		oakShrub.leavesMeta = (byte)0;
		oakShrub.maxY = 100;
		oakShrub.strengthFactor = 10f;		
		
		this.addDeco(new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, darkOakShrub, oakShrub));
		
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 80;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoBoulder decoCobwebBoulder = new DecoBoulder();
		decoCobwebBoulder.boulderBlock = Blocks.web;
		decoCobwebBoulder.chance = 48;
		decoCobwebBoulder.minY = 63;
		decoCobwebBoulder.maxY = 70;
		decoCobwebBoulder.strengthFactor = 2f;
		decoCobwebBoulder.water = false;
		this.addDeco(decoCobwebBoulder, this.config._boolean(BiomeConfigVanillaRoofedForest.decorationCobwebsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 2;
		decoBaseBiomeDecorations.maxY = 100;
		this.addDeco(decoBaseBiomeDecorations);

		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 100;
		decoGrass.strengthFactor = 20f;
		this.addDeco(decoGrass);
		
		DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 100;
		decoDeadBush.chance = 2;
		decoDeadBush.strengthFactor = 2f;
		this.addDeco(decoDeadBush);
    }
}
