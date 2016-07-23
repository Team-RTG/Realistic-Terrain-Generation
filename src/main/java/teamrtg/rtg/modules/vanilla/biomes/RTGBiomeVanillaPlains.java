package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoFlowersRTG;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperThisOrThat;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperThisOrThat.ChanceType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGQuercusRobur;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaPlains extends RTGBiomeVanilla {

    public RTGBiomeVanillaPlains() {

        super(Biomes.PLAINS, Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            private final GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return riverized(65f + groundEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y), river);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {
        // Very sparse shrubs.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.logBlock = Blocks.LOG.getDefaultState();
        decoShrubOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoShrubOak.maxY = 110;
        decoShrubOak.loops = 1;
        decoShrubOak.chance = 36;
		this.addDeco(decoShrubOak);
        
		// The occasional flower.
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);
        
        // Lots of grass, but not as much as vanilla.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.minY = 60;
		decoGrass.maxY = 128;
		decoGrass.loops = 6;
        this.addDeco(decoGrass);
        
        // Very rare fat oak/birch trees.
        	
		TreeRTG roburTree1 = new TreeRTGQuercusRobur();
		roburTree1.logBlock = Blocks.LOG.getDefaultState();
		roburTree1.leavesBlock = Blocks.LEAVES.getDefaultState();
		roburTree1.minTrunkSize = 3;
		roburTree1.maxTrunkSize = 5;
		roburTree1.minCrownSize = 7;
		roburTree1.maxCrownSize = 9;
		this.addTree(roburTree1);
        	
        DecoTree oakTrees = new DecoTree(roburTree1);
        oakTrees.treeType = DecoTree.TreeType.RTG_TREE;
        oakTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        oakTrees.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);
        oakTrees.treeConditionNoise = 0.4f;
        oakTrees.treeConditionChance = 48;
        
		TreeRTG roburTree2 = new TreeRTGQuercusRobur();
		roburTree2.logBlock = Blocks.LOG.getStateFromMeta(2);
		roburTree2.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
		roburTree2.minTrunkSize = 3;
		roburTree2.maxTrunkSize = 5;
		roburTree2.minCrownSize = 7;
		roburTree2.maxCrownSize = 9;
		this.addTree(roburTree2);
        
        DecoTree birchTrees = new DecoTree(roburTree2);
        birchTrees.treeType = DecoTree.TreeType.RTG_TREE;
        birchTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        birchTrees.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);
        birchTrees.treeConditionNoise = 0.4f;
        birchTrees.treeConditionChance = 48;

        this.addDeco(new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, oakTrees, birchTrees));
		
        // Vanilla trees look awful in this biome, so let's make sure they don't generate.
        
//		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
//		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {
        config.FILL_LAYERS.setDefault(3);
    }
}
