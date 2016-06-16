package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;


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
		acaciaTree.logBlock = Blocks.log2;
		acaciaTree.logMeta = (byte)0;
		acaciaTree.leavesBlock = Blocks.leaves2;
		acaciaTree.leavesMeta = (byte)0;
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
        acaciaShrub.logBlock = Blocks.log2;
        acaciaShrub.logMeta = (byte)0;
        acaciaShrub.leavesBlock = Blocks.leaves2;
        acaciaShrub.leavesMeta = (byte)0;
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
