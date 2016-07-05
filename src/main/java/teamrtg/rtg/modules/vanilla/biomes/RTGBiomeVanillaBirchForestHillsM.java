package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoFlowersRTG;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperRandomSplit;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import teamrtg.rtg.api.tools.feature.tree.vanilla.WorldGenTreesRTG;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaBirchForestHillsM extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.BIRCH_FOREST_HILLS;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaBirchForestHillsM() {
        super(
            mutationBiome,
            Biomes.RIVER
        );
        this.noLakes = true;
    }

    @Override
    public void initConfig() {

    }

    @Override
    public void initDecos() {
        TreeRTG birchSmall = new TreeRTGBetulaPapyrifera();
		birchSmall.logBlock = Blocks.LOG.getStateFromMeta(2);
		birchSmall.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);;
		birchSmall.minTrunkSize = 4;
		birchSmall.maxTrunkSize = 10;
		birchSmall.minCrownSize = 8;
		birchSmall.maxCrownSize = 19;
		this.addTree(birchSmall);
        
		DecoTree smallBirch = new DecoTree(birchSmall);
		smallBirch.strengthNoiseFactorForLoops = true;
		smallBirch.treeType = TreeType.RTG_TREE;
		smallBirch.distribution.noiseDivisor = 80f;
		smallBirch.distribution.noiseFactor = 60f;
		smallBirch.distribution.noiseAddend = -15f;
		smallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallBirch.maxY = 120;
		this.addDeco(smallBirch);
        
        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
		birchTree.logBlock = Blocks.LOG.getStateFromMeta(2);;
		birchTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);;
		birchTree.minTrunkSize = 4;
		birchTree.maxTrunkSize = 10;
		birchTree.minCrownSize = 8;
		birchTree.maxCrownSize = 19;
		this.addTree(birchTree);
		
		DecoTree birchTrees = new DecoTree(birchTree);
		birchTrees.strengthFactorForLoops = 3f;
		birchTrees.treeType = TreeType.RTG_TREE;
		birchTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		birchTrees.maxY = 100;
		
		DecoTree rtgTrees = new DecoTree(new WorldGenTreesRTG(false));
		rtgTrees.treeType = TreeType.WORLDGEN;
		rtgTrees.strengthFactorForLoops = 3f;
		rtgTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		rtgTrees.maxY = 100;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{birchTrees, rtgTrees};
		decoHelperRandomSplit.chances = new int[]{10, 4, 1};
		this.addDeco(decoHelperRandomSplit);
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte)2;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;        
		this.addDeco(decoFallenTree);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 120;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {3, 6};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 12f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
    }

    @Override
    public SurfacePart initSurface() {
        return new DepthSelector(0, 10)
            .add(new CliffSelector(1.4f)
                .add(new DepthSelector(0, 1)
                    .add(PARTS.rand(3)
                        .add(PARTS.COBBLE)))
                .add(PARTS.STONE))
            .add(PARTS.surfaceGeneric());
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 68f, 65f, 10f);
            }
        };
    }
}
