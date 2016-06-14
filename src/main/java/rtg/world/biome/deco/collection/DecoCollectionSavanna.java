package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDoubleGrass;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;


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
        acaciaShrub.logBlock = Blocks.log2;
        acaciaShrub.logMeta = (byte)0;
        acaciaShrub.leavesBlock = Blocks.leaves2;
        acaciaShrub.leavesMeta = (byte)0;
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
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, fallenTrees);

		TreeRTG bucheriTree = new TreeRTGAcaciaBucheri();
		bucheriTree.logBlock = Blocks.log2;
		bucheriTree.logMeta = (byte)0;
		bucheriTree.leavesBlock = Blocks.leaves2;
		bucheriTree.leavesMeta = (byte)0;
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
		decoBoulder.boulderBlock = Blocks.cobblestone;
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
