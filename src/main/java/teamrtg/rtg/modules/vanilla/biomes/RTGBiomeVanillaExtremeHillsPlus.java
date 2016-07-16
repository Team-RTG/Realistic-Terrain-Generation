package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoFlowersRTG;
import teamrtg.rtg.api.tools.deco.DecoLargeFernDoubleTallgrass;
import teamrtg.rtg.api.tools.deco.DecoPumpkin;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPinusNigra;
import teamrtg.rtg.api.tools.terrain.HeightEffect;
import teamrtg.rtg.api.tools.terrain.JitterEffect;
import teamrtg.rtg.api.tools.terrain.MountainsWithPassesEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaExtremeHillsPlus extends RTGBiomeVanilla {

    public RTGBiomeVanillaExtremeHillsPlus() {

        super(
                Biomes.EXTREME_HILLS_WITH_TREES,
            Biomes.RIVER
        );
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTopAndFill()
                .add(this.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
            .add(PARTS.selectTop()
                .add(PARTS.STONE_OR_COBBLE)))
            .add(PARTS.selectFill()
                .add(PARTS.STONE));
        surface.add(PARTS.surfaceMix(PARTS.MIX_NOISE_SMALL));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }


    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            private float width;
            private float strength;
            private float terrainHeight;
            private float spikeWidth = 30;
            private float spikeHeight = 40;
            private HeightEffect heightEffect;

            {
                width = 150f;
                strength = 120f;
                terrainHeight = 90f;
                MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
                mountainEffect.mountainHeight = strength;
                mountainEffect.mountainWavelength = width;
                mountainEffect.spikeHeight = this.spikeHeight;
                mountainEffect.spikeWavelength = this.spikeWidth;

                heightEffect = new JitterEffect(7f, 10f, mountainEffect);
                heightEffect = new JitterEffect(3f, 6f, heightEffect);
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return riverized(heightEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y) + terrainHeight, river);
            }
        };
    }


    @Override
    public void initDecos() {
		TreeRTG nigraTree = new TreeRTGPinusNigra();
		nigraTree.logBlock = Blocks.LOG.getDefaultState();
		nigraTree.leavesBlock = Blocks.LEAVES.getDefaultState();
		nigraTree.minTrunkSize = 18;
		nigraTree.maxTrunkSize = 27;
		nigraTree.minCrownSize = 7;
		nigraTree.maxCrownSize = 10;
		this.addTree(nigraTree);
        
		DecoTree decoTrees = new DecoTree(nigraTree);
		decoTrees.strengthFactorForLoops = 4f;
		decoTrees.strengthNoiseFactorXForLoops = true;
		decoTrees.distribution.noiseDivisor = 100f;
		decoTrees.distribution.noiseFactor = 6f;
		decoTrees.distribution.noiseAddend = 0.8f;
		decoTrees.treeType = TreeType.RTG_TREE;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 24;
		decoTrees.maxY = 100;
		this.addDeco(decoTrees);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(1);
		decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree);
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
		decoBoulder.chance = 12;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = DecoPumpkin.RandomType.USE_CHANCE_VALUE;
		decoPumpkin.chance = 28;
        this.addDeco(decoPumpkin);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.loops = 3;
        this.addDeco(decoFlowersRTG);
        
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.maxY = 128;
        decoDoublePlants.fernChance = 3;
        decoDoublePlants.loops = 15;
        this.addDeco(decoDoublePlants);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.GRAVEL.getDefaultState());
        config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.DIRT.getDefaultState());
        config.GENERATE_EMERALDS.setDefault(true);
    }
}
