package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLRockMountains;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.world.gen.surface.highlands.SurfaceHLRockMountains;
import rtg.world.gen.terrain.highlands.TerrainHLRockMountains;

public class RealisticBiomeHLRockMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.rockMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLRockMountains(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLRockMountains(230f, 100f, 68f),
            new SurfaceHLRockMountains(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
        );
        this.generatesEmeralds = true;
        noWaterFeatures = true;
        
        // copied from Realistic Extreme hills. Does not call vanilla decorate so no Higland inverted rock parabolas.
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 3f;
		this.addDeco(decoBoulder);
        
		DecoTree decoTrees = new DecoTree(new TreeRTGPinusNigra());
		decoTrees.logBlock = Blocks.log;
		decoTrees.logMeta = (byte)0;
		decoTrees.leavesBlock = Blocks.leaves;
		decoTrees.leavesMeta = (byte)0;
		decoTrees.minTrunkSize = 18;
		decoTrees.maxTrunkSize = 27;
		decoTrees.minCrownSize = 7;
		decoTrees.maxCrownSize = 10;
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
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLRockMountains.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);
        
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);
        
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
        noLakes = true;
        noWaterFeatures = true;
    }
}
