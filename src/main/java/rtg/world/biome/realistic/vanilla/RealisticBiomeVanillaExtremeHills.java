package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHills;
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
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHills;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.extremeHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.extremeHills.fillerBlock;
    
    public RealisticBiomeVanillaExtremeHills(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.extremeHills,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHills(10f, 120f, 10f, 200f),
            new SurfaceVanillaExtremeHills(config, topBlock, fillerBlock, Blocks.grass, Blocks.dirt, 60f, -0.14f, 14f, 0.25f)
        );
        this.generatesEmeralds = true;
        this.noLakes=true;
        this.noWaterFeatures=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 90;
		decoBoulder.strengthFactor = 3f;
		this.addDeco(decoBoulder);
        
		DecoTree decoTrees = new DecoTree();
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
		decoTrees.treeType = TreeType.PINACEAE_PINUS_NIGRA;
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
		decoFallenTree.logConditionChance = 16;
		decoFallenTree.maxY = 90;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 7;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaExtremeHills.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 4;
        this.addDeco(decoShrub);
        
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);
        
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 30f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
