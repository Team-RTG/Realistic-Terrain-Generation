package teamrtg.rtg.api.tools.deco.collection;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoDoubleGrass;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGAcaciaBucheri;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionSavanna extends DecoCollectionBase
{

	public DecoCollectionSavanna(boolean fallenTrees)
	{

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.logBlock = Blocks.LOG2.getDefaultState();
        acaciaShrub.leavesBlock = Blocks.LEAVES2.getDefaultState();
        acaciaShrub.maxY = 160;
        acaciaShrub.strengthFactor = 2f;
        acaciaShrub.chance = 12;
		this.addDeco(acaciaShrub);
		
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 36;
		decoFallenTree.logBlock = Blocks.LOG2;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Blocks.LEAVES2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, fallenTrees);

		TreeRTG bucheriTree = new TreeRTGAcaciaBucheri();
		bucheriTree.logBlock = Blocks.LOG2.getDefaultState();
		bucheriTree.leavesBlock = Blocks.LEAVES2.getDefaultState();
		bucheriTree.minTrunkSize = 4;
		bucheriTree.maxTrunkSize = 9;
		this.addTree(bucheriTree);

		DecoTree bucheriTrees = new DecoTree(bucheriTree);
		bucheriTrees.loops = 1;
		bucheriTrees.treeType = TreeType.RTG_TREE;
		bucheriTrees.distribution.noiseDivisor = 80f;
		bucheriTrees.distribution.noiseFactor = 60f;
		bucheriTrees.distribution.noiseAddend = -15f;
		bucheriTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bucheriTrees.treeConditionNoise = -0.4f;
		bucheriTrees.treeConditionChance = 28;
		this.addDeco(bucheriTrees);

		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
		decoBoulder.chance = 32;
		decoBoulder.maxY = 95;
		this.addDeco(decoBoulder);
        
		DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
		decoDoubleGrass.maxY = 128;
		decoDoubleGrass.strengthFactor = 3f;
        this.addDeco(decoDoubleGrass);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
	}
}
