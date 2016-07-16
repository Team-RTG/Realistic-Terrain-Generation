package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
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
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaBirchForest extends RTGBiomeVanilla {

    public RTGBiomeVanillaBirchForest() {
        super(
            Biomes.BIRCH_FOREST,
            Biomes.RIVER
        );
    }

    @Override
    public void initConfig() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.DIRT.getStateFromMeta(2));
    }

    @Override
    public void initDecos() {
        TreeRTG birchSmall = new TreeRTGBetulaPapyrifera();
		birchSmall.logBlock = Blocks.LOG.getStateFromMeta(2);
		birchSmall.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
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
		birchTree.logBlock = Blocks.LOG.getStateFromMeta(2);
		birchTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
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
		decoHelperRandomSplit.chances = new int[]{10, 4};
		this.addDeco(decoHelperRandomSplit);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(2);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
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
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTopAndFill()
                .add(this.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
            .add(PARTS.selectTop()
                .add(PARTS.STONE_OR_COBBLE)))
            .add(PARTS.selectFill()
                .add(PARTS.STONE));
        surface.add(PARTS.surfaceMix(PARTS.MIX_NOISE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            private GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return riverized(65f + groundEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y), river);
            }
        };
    }
}
