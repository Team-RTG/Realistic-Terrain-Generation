package rtg.world.biome.realistic.idt;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.idt.config.BiomeConfigIDTEbonyForest;
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
import rtg.world.gen.surface.idt.SurfaceIDTEbonyForest;
import rtg.world.gen.terrain.idt.TerrainIDTEbonyForest;

public class RealisticBiomeIDTEbonyForest extends RealisticBiomeIDTBase
{
    public RealisticBiomeIDTEbonyForest(BiomeGenBase idtBiome, BiomeConfig config)
    {
 
        super(config, 
    		idtBiome, BiomeGenBase.river,
    		new TerrainIDTEbonyForest(),
            new SurfaceIDTEbonyForest(config, idtBiome.topBlock, idtBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, idtBiome.topBlock, (byte)0, 0.10f)
        );
        
        // Trees first.
		
		TreeRTG ponderosaEbonyTree = new TreeRTGPinusPonderosa();
		ponderosaEbonyTree.logBlock = this.ebonyLogBlock;
		ponderosaEbonyTree.logMeta = this.ebonyLogMeta;
		ponderosaEbonyTree.leavesBlock = this.ebonyLeavesBlock;
		ponderosaEbonyTree.leavesMeta = this.ebonyLeavesMeta;
		ponderosaEbonyTree.minTrunkSize = 11;
		ponderosaEbonyTree.maxTrunkSize = 21;
		ponderosaEbonyTree.minCrownSize = 15;
		ponderosaEbonyTree.maxCrownSize = 29;
		this.addTree(ponderosaEbonyTree);
		
		DecoTree ebonyPines = new DecoTree(ponderosaEbonyTree);
		ebonyPines.strengthFactorForLoops = 8f;
		ebonyPines.treeType = TreeType.RTG_TREE;
		ebonyPines.distribution.noiseDivisor = 100f;
		ebonyPines.distribution.noiseFactor = 6f;
		ebonyPines.distribution.noiseAddend = 0.8f;
		ebonyPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		ebonyPines.treeConditionNoise = 0f;
		ebonyPines.treeConditionChance = 1;
		ebonyPines.maxY = 85;
		this.addDeco(ebonyPines);
		
		// More trees.
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
		sitchensisTree.logBlock = this.ebonyLogBlock;
		sitchensisTree.logMeta = this.ebonyLogMeta;
		sitchensisTree.leavesBlock = this.ebonyLeavesBlock;
		sitchensisTree.leavesMeta = this.ebonyLeavesMeta;
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 10;
    	sitchensisTree.minCrownSize = 6;
    	sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree smallEbonyPines = new DecoTree(sitchensisTree);
		smallEbonyPines.strengthFactorForLoops = 3f;
    	smallEbonyPines.treeType = TreeType.RTG_TREE;
		smallEbonyPines.treeCondition = TreeCondition.RANDOM_CHANCE;
		smallEbonyPines.treeConditionChance = 4;
		smallEbonyPines.maxY = 100;
		this.addDeco(smallEbonyPines);
		
		// Add some fallen trees.
        DecoFallenTree decoFallenEbony = new DecoFallenTree();
        decoFallenEbony.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenEbony.logConditionChance = 16;
        decoFallenEbony.maxY = 80;
        decoFallenEbony.logBlock = this.ebonyLogBlock;
        decoFallenEbony.logMeta = this.ebonyLogMeta;
        decoFallenEbony.leavesBlock = this.ebonyLeavesBlock;
        decoFallenEbony.leavesMeta = (byte)-1;
        decoFallenEbony.minSize = 3;
        decoFallenEbony.maxSize = 6;
		this.addDeco(decoFallenEbony, this.config._boolean(BiomeConfigIDTEbonyForest.decorationLogsId));
        
        // Shrubs to fill in the blanks.
        DecoShrub decoShrubEbony = new DecoShrub();
        decoShrubEbony.logBlock = this.ebonyLogBlock;
        decoShrubEbony.logMeta = this.ebonyLogMeta;
        decoShrubEbony.leavesBlock = this.ebonyLeavesBlock;
        decoShrubEbony.leavesMeta = this.ebonyLeavesMeta;
        decoShrubEbony.maxY = 140;
        decoShrubEbony.strengthFactor = 4f;
        decoShrubEbony.chance = 3;
		this.addDeco(decoShrubEbony);

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
