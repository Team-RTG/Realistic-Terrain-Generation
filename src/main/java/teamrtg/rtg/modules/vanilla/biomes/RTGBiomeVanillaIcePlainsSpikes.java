package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGCupressusSempervirens;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPiceaSitchensis;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPinusPonderosa;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaIcePlainsSpikes extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.ICE_PLAINS;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaIcePlainsSpikes() {
        super(
                mutationBiome,
            Biomes.FROZEN_RIVER
        );
        this.noLakes = true;
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.4f)
            .add(new DepthSelector(0, 1)
                .add(PARTS.rand(3)
                    .add(new BlockPart(config.CLIFF_BLOCK_2.get()))))
            .add(new BlockPart(config.CLIFF_BLOCK_1.get())));
        surface.add(PARTS.surfaceMix((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 60f, z / 60f) + rtgWorld.simplex.noise2(x / 14f, z / 14f) * 0.25f > -0.14f));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 65f);
            }
        };
    }


    @Override
    public void initDecos() {
		TreeRTG ponderosaOakTree = new TreeRTGPinusPonderosa();
		ponderosaOakTree.logBlock = Blocks.PACKED_ICE.getDefaultState();
		ponderosaOakTree.leavesBlock = Blocks.ICE.getDefaultState();
		ponderosaOakTree.minTrunkSize = 11;
		ponderosaOakTree.maxTrunkSize = 21;
		ponderosaOakTree.minCrownSize = 15;
		ponderosaOakTree.maxCrownSize = 29;
		ponderosaOakTree.validGroundBlocks.clear();
		ponderosaOakTree.validGroundBlocks.add(Blocks.SNOW.getDefaultState());
		ponderosaOakTree.validGroundBlocks.add(Blocks.SNOW_LAYER.getDefaultState());
		
		DecoTree oakPines = new DecoTree(ponderosaOakTree);
		oakPines.strengthFactorForLoops = 6f;
		oakPines.treeType = TreeType.RTG_TREE;
		oakPines.distribution.noiseDivisor = 100f;
		oakPines.distribution.noiseFactor = 6f;
		oakPines.distribution.noiseAddend = 0.8f;
		oakPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		oakPines.treeConditionNoise = 0f;
		oakPines.treeConditionChance = 1;
		oakPines.maxY = 85;
		this.addDeco(oakPines);
		
		TreeRTG sempervirensOakTree = new TreeRTGCupressusSempervirens();
		sempervirensOakTree.logBlock = Blocks.PACKED_ICE.getDefaultState();
		sempervirensOakTree.leavesBlock = Blocks.ICE.getDefaultState();
		sempervirensOakTree.minTrunkSize = 3;
		sempervirensOakTree.maxTrunkSize = 4;
		sempervirensOakTree.minCrownSize = 6;
		sempervirensOakTree.maxCrownSize = 14;
		sempervirensOakTree.validGroundBlocks.clear();
		sempervirensOakTree.validGroundBlocks.add(Blocks.SNOW.getDefaultState());
		sempervirensOakTree.validGroundBlocks.add(Blocks.SNOW_LAYER.getDefaultState());
		
		DecoTree decoSempervirensTree = new DecoTree(sempervirensOakTree);
		decoSempervirensTree.treeType = DecoTree.TreeType.RTG_TREE;
		decoSempervirensTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoSempervirensTree.distribution = new DecoTree.Distribution(80f, 60f, -15f);
		decoSempervirensTree.treeConditionNoise = -0.1f;
		decoSempervirensTree.treeConditionChance = 1;
		decoSempervirensTree.loops = 1;
		this.addDeco(decoSempervirensTree);
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
    	sitchensisTree.logBlock = Blocks.PACKED_ICE.getDefaultState();
    	sitchensisTree.leavesBlock = Blocks.ICE.getDefaultState();
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 10;
    	sitchensisTree.minCrownSize = 6;
    	sitchensisTree.maxCrownSize = 14;
		
		DecoTree oakPine = new DecoTree(sitchensisTree);
		oakPine.strengthFactorForLoops = 3f;
    	oakPine.treeType = TreeType.RTG_TREE;
    	oakPine.distribution = new DecoTree.Distribution(80f, 60f, -15f);
		oakPine.treeCondition = TreeCondition.RANDOM_CHANCE;
		oakPine.treeConditionChance = 2;
		oakPine.maxY = 100;
		this.addDeco(oakPine);
        
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 140;
        decoShrubOak.logBlock = Blocks.PACKED_ICE.getDefaultState();
        decoShrubOak.leavesBlock = Blocks.ICE.getDefaultState();
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 4;
		this.addDeco(decoShrubOak);
        
		// TODO: Fix this.
		//int iceSpikeChance = this.config._int(BiomeConfigVanillaIcePlainsSpikes.iceSpikeChanceId);
		int iceSpikeChance = 0;
		if (iceSpikeChance > 0) {
			
			DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
			decoBaseBiomeDecorations.equalsZeroChance = iceSpikeChance;
			this.addDeco(decoBaseBiomeDecorations);
		}
    }

    @Override
    public void initConfig() {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.PACKED_ICE.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.ICE.getDefaultState());
    }
}
