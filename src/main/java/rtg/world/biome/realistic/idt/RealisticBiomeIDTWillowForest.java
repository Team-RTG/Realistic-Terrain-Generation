package rtg.world.biome.realistic.idt;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.idt.config.BiomeConfigIDTWillowForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.surface.idt.SurfaceIDTWillowForest;
import rtg.world.gen.terrain.idt.TerrainIDTWillowForest;

public class RealisticBiomeIDTWillowForest extends RealisticBiomeIDTBase
{
    public RealisticBiomeIDTWillowForest(BiomeGenBase idtBiome, BiomeConfig config)
    {
 
        super(config, 
    		idtBiome, BiomeGenBase.river,
    		new TerrainIDTWillowForest(),
            new SurfaceIDTWillowForest(config, idtBiome.topBlock, idtBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, idtBiome.topBlock, (byte)0, 0.10f)
        );
        
        // Trees first.
		
		TreeRTG ponderosaWillowTree = new TreeRTGPinusPonderosa();
		ponderosaWillowTree.logBlock = this.willowLogBlock;
		ponderosaWillowTree.logMeta = this.willowLogMeta;
		ponderosaWillowTree.leavesBlock = this.willowLeavesBlock;
		ponderosaWillowTree.leavesMeta = this.willowLeavesMeta;
		ponderosaWillowTree.minTrunkSize = 11;
		ponderosaWillowTree.maxTrunkSize = 21;
		ponderosaWillowTree.minCrownSize = 15;
		ponderosaWillowTree.maxCrownSize = 29;
		this.addTree(ponderosaWillowTree);
		
		DecoTree willowPines = new DecoTree(ponderosaWillowTree);
		willowPines.strengthFactorForLoops = 8f;
		willowPines.treeType = TreeType.RTG_TREE;
		willowPines.distribution.noiseDivisor = 100f;
		willowPines.distribution.noiseFactor = 6f;
		willowPines.distribution.noiseAddend = 0.8f;
		willowPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		willowPines.treeConditionNoise = 0f;
		willowPines.treeConditionChance = 1;
		willowPines.maxY = 85;
		this.addDeco(willowPines);
		
		// More trees.
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
		sitchensisTree.logBlock = this.willowLogBlock;
		sitchensisTree.logMeta = this.willowLogMeta;
		sitchensisTree.leavesBlock = this.willowLeavesBlock;
		sitchensisTree.leavesMeta = this.willowLeavesMeta;
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 10;
    	sitchensisTree.minCrownSize = 6;
    	sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree smallWillowPines = new DecoTree(sitchensisTree);
		smallWillowPines.strengthFactorForLoops = 3f;
    	smallWillowPines.treeType = TreeType.RTG_TREE;
		smallWillowPines.treeCondition = TreeCondition.RANDOM_CHANCE;
		smallWillowPines.treeConditionChance = 4;
		smallWillowPines.maxY = 100;
		this.addDeco(smallWillowPines);
		
		// Add some fallen trees.
        DecoFallenTree decoFallenWillow = new DecoFallenTree();
        decoFallenWillow.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenWillow.logConditionChance = 16;
        decoFallenWillow.maxY = 80;
        decoFallenWillow.logBlock = this.willowLogBlock;
        decoFallenWillow.logMeta = this.willowLogMeta;
        decoFallenWillow.leavesBlock = this.willowLeavesBlock;
        decoFallenWillow.leavesMeta = (byte)-1;
        decoFallenWillow.minSize = 3;
        decoFallenWillow.maxSize = 6;
		this.addDeco(decoFallenWillow, this.config._boolean(BiomeConfigIDTWillowForest.decorationLogsId));
        
        // Shrubs to fill in the blanks.
        DecoShrub decoShrubWillow = new DecoShrub();
        decoShrubWillow.logBlock = this.willowLogBlock;
        decoShrubWillow.logMeta = this.willowLogMeta;
        decoShrubWillow.leavesBlock = this.willowLeavesBlock;
        decoShrubWillow.leavesMeta = this.willowLeavesMeta;
        decoShrubWillow.maxY = 140;
        decoShrubWillow.strengthFactor = 4f;
        decoShrubWillow.chance = 3;
		this.addDeco(decoShrubWillow);

		DecoBaseBiomeDecorations decoBaseBiome = new DecoBaseBiomeDecorations();
		decoBaseBiome.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiome);
        
        // Grass filler.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.minY = 60;
		decoGrass.maxY = 128;
		decoGrass.loops = 8;
        this.addDeco(decoGrass);
    }
}
