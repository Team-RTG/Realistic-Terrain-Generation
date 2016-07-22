package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoFlowersRTG.HeightType;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionSmallPineTreesForest;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPinusPonderosa;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaFlowerForest extends RTGBiomeVanilla {

    public static Biome standardBiome = Biomes.FOREST;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaFlowerForest() {

        super(
                mutationBiome,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 80f, 65f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceForest(this);
    }

    @Override
    public void initDecos() {
        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 4f;
        decoShrub.chance = 3;
		this.addDeco(decoShrub);
		
		// Flowers are the most aesthetically important feature of this biome, so let's add those next.
		DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
		decoFlowers1.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; //Only colourful 1-block-tall flowers.
		decoFlowers1.strengthFactor = 12f; // Lots and lots of flowers!
		decoFlowers1.heightType = HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);
        
		DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
		decoFlowers2.flowers = new int[] {10, 11, 14, 15}; //Only 2-block-tall flowers.
		decoFlowers2.strengthFactor = 2f; // Not as many of these.
		decoFlowers2.chance = 3;
		decoFlowers2.heightType = HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers2);
		
        // Trees first.
        
		TreeRTG ponderosaOakTree = new TreeRTGPinusPonderosa();
		ponderosaOakTree.logBlock = Blocks.LOG.getDefaultState();
		ponderosaOakTree.leavesBlock = Blocks.LEAVES.getDefaultState();
		ponderosaOakTree.minTrunkSize = 11;
		ponderosaOakTree.maxTrunkSize = 21;
		ponderosaOakTree.minCrownSize = 15;
		ponderosaOakTree.maxCrownSize = 29;
		this.addTree(ponderosaOakTree);
        
		DecoTree oakPines = new DecoTree(ponderosaOakTree);
		oakPines.strengthNoiseFactorForLoops = true;
		oakPines.treeType = TreeType.RTG_TREE;
		oakPines.distribution.noiseDivisor = 80f;
		oakPines.distribution.noiseFactor = 60f;
		oakPines.distribution.noiseAddend = -15f;
		oakPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		oakPines.treeConditionNoise = 0f;
		oakPines.treeConditionChance = 1;
		oakPines.maxY = 140;
		
		TreeRTG ponderosaSpruceTree = new TreeRTGPinusPonderosa();
		ponderosaSpruceTree.logBlock = Blocks.LOG.getStateFromMeta(1);
		ponderosaSpruceTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
		ponderosaSpruceTree.minTrunkSize = 11;
		ponderosaSpruceTree.maxTrunkSize = 21;
		ponderosaSpruceTree.minCrownSize = 15;
		ponderosaSpruceTree.maxCrownSize = 29;
		this.addTree(ponderosaSpruceTree);
		
		DecoTree sprucePines = new DecoTree(ponderosaSpruceTree);
		sprucePines.strengthNoiseFactorForLoops = true;
		sprucePines.treeType = TreeType.RTG_TREE;
		sprucePines.distribution.noiseDivisor = 80f;
		sprucePines.distribution.noiseFactor = 60f;
		sprucePines.distribution.noiseAddend = -15f;
		sprucePines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		sprucePines.treeConditionNoise = 0f;
		sprucePines.treeConditionChance = 1;
		sprucePines.maxY = 140;
		
		DecoHelper5050 decoPines = new DecoHelper5050(oakPines, sprucePines);
		this.addDeco(decoPines);
		
		// More trees.
		this.addDecoCollection(new DecoCollectionSmallPineTreesForest());

        // Not much free space left, so let's give some space to the base biome.
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 4;
		this.addDeco(decoBaseBiomeDecorations);
		
		// Add some fallen trees of the oak and spruce variety (50/50 distribution).
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 8;
        decoFallenOak.maxY = 100;
        decoFallenOak.logBlock = Blocks.LOG.getDefaultState();
        decoFallenOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;
        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 8;
        decoFallenSpruce.maxY = 100;
        decoFallenSpruce.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoFallenSpruce.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
		this.addDeco(decoFallenTree);		
		
		// Grass filler.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.GRASS.getDefaultState());
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
    }
}
