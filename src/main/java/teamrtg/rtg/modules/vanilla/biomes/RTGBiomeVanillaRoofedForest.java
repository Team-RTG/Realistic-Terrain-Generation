package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoCobwebs;
import teamrtg.rtg.api.tools.deco.DecoDeadBush;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoMushrooms;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperThisOrThat;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperThisOrThat.ChanceType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGCeibaPentandra;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGCeibaRosea;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaRoofedForest extends RTGBiomeVanilla {

    public RTGBiomeVanillaRoofedForest() {

        super(
                Biomes.ROOFED_FOREST,
            Biomes.RIVER
        );
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
    public void initDecos() {
		DecoMushrooms decoMushrooms = new DecoMushrooms();
		decoMushrooms.chance = 4;
		decoMushrooms.maxY = 90;
		decoMushrooms.randomType = DecoMushrooms.RandomType.ALWAYS_GENERATE;
		this.addDeco(decoMushrooms);
		
		TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.logBlock = Blocks.LOG2.getStateFromMeta(1);
        mucronataTree.leavesBlock = Blocks.LEAVES2.getStateFromMeta(1);
        mucronataTree.minTrunkSize = 2;
        mucronataTree.maxTrunkSize = 3;
        mucronataTree.minCrownSize = 10;
        mucronataTree.maxCrownSize = 18;
        mucronataTree.noLeaves = false;
		this.addTree(mucronataTree);
		
        DecoTree mangroveTree = new DecoTree(mucronataTree);
        mangroveTree.treeType = DecoTree.TreeType.RTG_TREE;
        mangroveTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        mangroveTree.treeConditionChance = 1;
        mangroveTree.strengthFactorForLoops = 8f;
        mangroveTree.maxY = 110;
        this.addDeco(mangroveTree);
        
        TreeRTG pentandraTree = new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f);
        pentandraTree.logBlock = Blocks.LOG2.getStateFromMeta(1);
        pentandraTree.leavesBlock = Blocks.LEAVES2.getStateFromMeta(1);
        pentandraTree.minTrunkSize = 2;
        pentandraTree.maxTrunkSize = 3;
        pentandraTree.minCrownSize = 10;
        pentandraTree.maxCrownSize = 18;
        pentandraTree.noLeaves = false;
        this.addTree(pentandraTree);
        
        DecoTree ceibaPentandraTree = new DecoTree(pentandraTree);
        ceibaPentandraTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaPentandraTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaPentandraTree.treeConditionChance = 1;
        ceibaPentandraTree.strengthFactorForLoops = 8f;
        ceibaPentandraTree.maxY = 110;
        this.addDeco(ceibaPentandraTree);
        
        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
        roseaTree.logBlock = Blocks.LOG2.getStateFromMeta(1);
        roseaTree.leavesBlock = Blocks.LEAVES2.getStateFromMeta(1);
        roseaTree.minTrunkSize = 2;
        roseaTree.maxTrunkSize = 3;
        roseaTree.minCrownSize = 10;
        roseaTree.maxCrownSize = 18;
        roseaTree.noLeaves = false;
        this.addTree(roseaTree);
        
        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
        ceibaRoseaTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaRoseaTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaRoseaTree.treeConditionChance = 1;
        ceibaRoseaTree.strengthFactorForLoops = 8f;
        ceibaRoseaTree.maxY = 110;
        this.addDeco(ceibaRoseaTree);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionChance = 16;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logBlock = Blocks.LOG2;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.LEAVES2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 9;
		this.addDeco(decoFallenTree);
        
		DecoShrub darkOakShrub = new DecoShrub();
		darkOakShrub.logBlock = Blocks.LOG2.getStateFromMeta(1);
		darkOakShrub.leavesBlock = Blocks.LEAVES2.getStateFromMeta(1);
		darkOakShrub.maxY = 100;
		darkOakShrub.strengthFactor = 10f;
		
		DecoShrub oakShrub = new DecoShrub();
		oakShrub.logBlock = Blocks.LOG.getDefaultState();
		oakShrub.leavesBlock = Blocks.LEAVES.getDefaultState();
		oakShrub.maxY = 100;
		oakShrub.strengthFactor = 10f;		
		
		this.addDeco(new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, darkOakShrub, oakShrub));
		
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
		decoBoulder.chance = 16;
		decoBoulder.maxY = 80;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoCobwebs decoCobwebs = new DecoCobwebs();
		decoCobwebs.chance = 1;
		decoCobwebs.minY = 63;
		decoCobwebs.maxY = 76;
		decoCobwebs.strengthFactor = 30f;
		decoCobwebs.adjacentBlock = Blocks.LOG2.getStateFromMeta(1);
		decoCobwebs.minAdjacents = 2;
		this.addDeco(decoCobwebs);
        
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

    @Override
    public void initConfig() {
        this.config.WATER_POND_CHANCE.setDefault(3);
        this.config.LAVA_POND_CHANCE.setDefault(0);
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.DIRT.getStateFromMeta(2));
    }
}