package rtg.world.biome.realistic.highlands;

import highlands.Highlands;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLAutumnForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.highlands.SurfaceHLAutumnForest;
import rtg.world.gen.terrain.highlands.TerrainHLAutumnForest;

public class RealisticBiomeHLAutumnForest extends RealisticBiomeHLBase {
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.autumnForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLAutumnForest(BiomeConfig config) {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLAutumnForest(0f, 50f, 68f, 200f),
            new SurfaceHLAutumnForest(config, topBlock, fillerBlock)
        );
        this.noWaterFeatures = false;
        
		DecoTree yellowPines = new DecoTree();
		yellowPines.logBlock = Blocks.log;
		yellowPines.logMeta = (byte)0;
		yellowPines.leavesBlock = Highlands.vanillaBlocksFlag ? Blocks.leaves : HighlandsBlocks.autumnYellowLeaves;
		yellowPines.leavesMeta = Highlands.vanillaBlocksFlag ? (byte)0 : (byte)0;
		yellowPines.minTrunkSize = 8;
		yellowPines.maxTrunkSize = 16;
		yellowPines.minCrownSize = 12;
		yellowPines.maxCrownSize = 24;
		yellowPines.strengthFactorForLoops = 6f;
		yellowPines.treeType = TreeType.PINACEAE_PINUS_PONDEROSA;
		yellowPines.distribution.noiseDivisor = 100f;
		yellowPines.distribution.noiseFactor = 6f;
		yellowPines.distribution.noiseAddend = 0.8f;
		yellowPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		yellowPines.treeConditionNoise = 0f;
		yellowPines.treeConditionChance = 1;
		yellowPines.maxY = 110;
		
		DecoTree orangePines = new DecoTree(yellowPines);
		orangePines.leavesBlock = Highlands.vanillaBlocksFlag ? Blocks.leaves : HighlandsBlocks.autumnOrangeLeaves;
		orangePines.leavesMeta = Highlands.vanillaBlocksFlag ? (byte)2 : (byte)0;
		
		DecoHelper5050 decoHelper5050 = new DecoHelper5050(yellowPines, orangePines);
		this.addDeco(decoHelper5050);
		
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 16;
        decoFallenOak.maxY = 90;
        decoFallenOak.logBlock = Blocks.log;
        decoFallenOak.logMeta = (byte)0;
        decoFallenOak.leavesBlock = Blocks.leaves;
        decoFallenOak.leavesMeta = (byte)-1;
        decoFallenOak.minSize = 4;
        decoFallenOak.maxSize = 8;
		
        DecoFallenTree decoFallenSpruce = new DecoFallenTree(decoFallenOak);
        decoFallenSpruce.logConditionChance = 24;
        decoFallenSpruce.logMeta = (byte)1;
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 5;
        
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLAutumnForest.decorationLogsId));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.minY = 53;
		decoGrass.maxY = 128;
		decoGrass.loops = 8;
        this.addDeco(decoGrass);
    }
}
