package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPinusPonderosa;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaSwampland extends RTGBiomeVanilla {

    public RTGBiomeVanillaSwampland() {

        super(
                Biomes.SWAMPLAND,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainMarsh(x, y, rtgWorld.simplex, 62f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceSwamp(this);
    }

    @Override
    public void initDecos() {
		TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
		myrtilloidesTree.logBlock = Blocks.LOG.getDefaultState();
		myrtilloidesTree.leavesBlock = Blocks.LEAVES.getDefaultState();
		this.addTree(myrtilloidesTree);
        
		DecoTree decoTrees = new DecoTree(myrtilloidesTree);
		decoTrees.strengthNoiseFactorXForLoops = true;
		decoTrees.strengthFactorForLoops = 1f;
		decoTrees.distribution.noiseDivisor = 80f;
		decoTrees.distribution.noiseFactor = 60f;
		decoTrees.distribution.noiseAddend = -15f;
		decoTrees.treeType = TreeType.RTG_TREE;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 12;
		decoTrees.maxY = 70;
		this.addDeco(decoTrees);
		
		TreeRTG ponderosaTree = new TreeRTGPinusPonderosa();
		ponderosaTree.logBlock = Blocks.LOG.getDefaultState();
		ponderosaTree.leavesBlock = Blocks.LEAVES.getDefaultState();
		ponderosaTree.minTrunkSize = 3;
		ponderosaTree.maxTrunkSize = 6;
		ponderosaTree.minCrownSize = 6;
		ponderosaTree.maxCrownSize = 14;
		ponderosaTree.noLeaves = true;
		this.addTree(ponderosaTree);
		
		DecoTree deadPineTree = new DecoTree(ponderosaTree);
		deadPineTree.treeType = TreeType.RTG_TREE;
		deadPineTree.treeCondition = TreeCondition.RANDOM_CHANCE;
		deadPineTree.treeConditionChance = 18;
		deadPineTree.maxY = 100;
		this.addDeco(deadPineTree);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
		
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.logBlock = Blocks.LOG2.getStateFromMeta(1);
		decoFallenTree.leavesBlock = Blocks.LEAVES2.getStateFromMeta(1);
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 50f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {
        this.config.SCATTERED_FEATURE.setDefault(BiomeConfig.FeatureType.WITCH_HUT.name());
    }
}
