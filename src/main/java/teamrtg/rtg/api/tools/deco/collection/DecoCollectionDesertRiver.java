package teamrtg.rtg.api.tools.deco.collection;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoCactus;
import teamrtg.rtg.api.tools.deco.DecoGrassDoubleTallgrass;
import teamrtg.rtg.api.tools.deco.DecoReed;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperThisOrThat;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperThisOrThat.ChanceType;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGAcaciaBucheri;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionDesertRiver extends DecoCollectionBase
{

	public DecoCollectionDesertRiver()
	{
		super();

		TreeRTG acaciaTree = new TreeRTGAcaciaBucheri();
		acaciaTree.logBlock = Blocks.LOG2.getDefaultState();
		acaciaTree.leavesBlock = Blocks.LEAVES2.getDefaultState();
		acaciaTree.minTrunkSize = 12;
		acaciaTree.maxTrunkSize = 16;
		this.addTree(acaciaTree);
		
		DecoTree acaciaTrees = new DecoTree(acaciaTree);
		acaciaTrees.checkRiver = true;
		acaciaTrees.minRiver = 0.86f;
		acaciaTrees.loops = 1;
		acaciaTrees.treeType = TreeType.RTG_TREE;
		acaciaTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		acaciaTrees.maxY = 65;
		this.addDeco(acaciaTrees);

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.checkRiver = true;
        acaciaShrub.minRiver = 0.86f;
        acaciaShrub.logBlock = Blocks.LOG2.getDefaultState();
        acaciaShrub.leavesBlock = Blocks.LEAVES2.getDefaultState();
        acaciaShrub.maxY = 65;
        acaciaShrub.loops = 1;
        acaciaShrub.chance = 1;
		this.addDeco(acaciaShrub);
		
		DecoHelperThisOrThat decoHelperThisOrThat = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, acaciaShrub, acaciaTrees);
		this.addDeco(decoHelperThisOrThat);
            
		DecoCactus decoRiverCactus = new DecoCactus();
		decoRiverCactus.checkRiver = true;
		decoRiverCactus.minRiver = 0.7f;
		decoRiverCactus.maxY = 80;
		decoRiverCactus.strengthFactor = 12f;
        this.addDeco(decoRiverCactus);
        
        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
		decoReed.maxY = 68;
		decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.checkRiver = true;
        decoGrassDoubleTallgrass.minRiver = 0.7f;
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        this.addDeco(decoGrassDoubleTallgrass);
	}
}
