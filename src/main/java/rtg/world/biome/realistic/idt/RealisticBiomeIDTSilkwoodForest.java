package rtg.world.biome.realistic.idt;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.idt.config.BiomeConfigIDTSilkwoodForest;
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
import rtg.world.gen.surface.idt.SurfaceIDTSilkwoodForest;
import rtg.world.gen.terrain.idt.TerrainIDTSilkwoodForest;

public class RealisticBiomeIDTSilkwoodForest extends RealisticBiomeIDTBase
{
    public RealisticBiomeIDTSilkwoodForest(BiomeGenBase idtBiome, BiomeConfig config)
    {
 
        super(config, 
    		idtBiome, BiomeGenBase.river,
    		new TerrainIDTSilkwoodForest(),
            new SurfaceIDTSilkwoodForest(config, idtBiome.topBlock, idtBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, idtBiome.topBlock, (byte)0, 0.10f)
        );
        
        // Trees first.
		
		TreeRTG ponderosaSilkwoodTree = new TreeRTGPinusPonderosa();
		ponderosaSilkwoodTree.logBlock = this.silkwoodLogBlock;
		ponderosaSilkwoodTree.logMeta = this.silkwoodLogMeta;
		ponderosaSilkwoodTree.leavesBlock = this.silkwoodLeavesBlock;
		ponderosaSilkwoodTree.leavesMeta = this.silkwoodLeavesMeta;
		ponderosaSilkwoodTree.minTrunkSize = 11;
		ponderosaSilkwoodTree.maxTrunkSize = 21;
		ponderosaSilkwoodTree.minCrownSize = 15;
		ponderosaSilkwoodTree.maxCrownSize = 29;
		this.addTree(ponderosaSilkwoodTree);
		
		DecoTree silkwoodPines = new DecoTree(ponderosaSilkwoodTree);
		silkwoodPines.strengthFactorForLoops = 8f;
		silkwoodPines.treeType = TreeType.RTG_TREE;
		silkwoodPines.distribution.noiseDivisor = 100f;
		silkwoodPines.distribution.noiseFactor = 6f;
		silkwoodPines.distribution.noiseAddend = 0.8f;
		silkwoodPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		silkwoodPines.treeConditionNoise = 0f;
		silkwoodPines.treeConditionChance = 1;
		silkwoodPines.maxY = 85;
		this.addDeco(silkwoodPines);
		
		// More trees.
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
		sitchensisTree.logBlock = this.silkwoodLogBlock;
		sitchensisTree.logMeta = this.silkwoodLogMeta;
		sitchensisTree.leavesBlock = this.silkwoodLeavesBlock;
		sitchensisTree.leavesMeta = this.silkwoodLeavesMeta;
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 10;
    	sitchensisTree.minCrownSize = 6;
    	sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree smallSilkwoodPines = new DecoTree(sitchensisTree);
		smallSilkwoodPines.strengthFactorForLoops = 3f;
    	smallSilkwoodPines.treeType = TreeType.RTG_TREE;
		smallSilkwoodPines.treeCondition = TreeCondition.RANDOM_CHANCE;
		smallSilkwoodPines.treeConditionChance = 4;
		smallSilkwoodPines.maxY = 100;
		this.addDeco(smallSilkwoodPines);
		
		// Add some fallen trees.
        DecoFallenTree decoFallenSilkwood = new DecoFallenTree();
        decoFallenSilkwood.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenSilkwood.logConditionChance = 16;
        decoFallenSilkwood.maxY = 80;
        decoFallenSilkwood.logBlock = this.silkwoodLogBlock;
        decoFallenSilkwood.logMeta = this.silkwoodLogMeta;
        decoFallenSilkwood.leavesBlock = this.silkwoodLeavesBlock;
        decoFallenSilkwood.leavesMeta = (byte)-1;
        decoFallenSilkwood.minSize = 3;
        decoFallenSilkwood.maxSize = 6;
		this.addDeco(decoFallenSilkwood, this.config._boolean(BiomeConfigIDTSilkwoodForest.decorationLogsId));
        
        // Shrubs to fill in the blanks.
        DecoShrub decoShrubSilkwood = new DecoShrub();
        decoShrubSilkwood.logBlock = this.silkwoodLogBlock;
        decoShrubSilkwood.logMeta = this.silkwoodLogMeta;
        decoShrubSilkwood.leavesBlock = this.silkwoodLeavesBlock;
        decoShrubSilkwood.leavesMeta = this.silkwoodLeavesMeta;
        decoShrubSilkwood.maxY = 140;
        decoShrubSilkwood.strengthFactor = 4f;
        decoShrubSilkwood.chance = 3;
		this.addDeco(decoShrubSilkwood);

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
