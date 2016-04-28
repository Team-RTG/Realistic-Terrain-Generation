package rtg.world.biome.deco.collection;

import net.minecraft.world.gen.feature.WorldGenShrub;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoDesertWell;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSavanna;


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
		
		DecoTree riverTrees = new DecoTree(new WorldGenTreeRTGSavanna(1, false));
		riverTrees.checkRiver = true;
		riverTrees.minRiver = 0.86f;
		riverTrees.loops = 2;
		riverTrees.treeType = TreeType.WORLDGEN;
		riverTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		riverTrees.maxY = 65;
		
		DecoTree riverShrubs = new DecoTree(riverTrees);
		riverShrubs.worldGen = new WorldGenShrub(0, 0);
		riverShrubs.strengthFactorForLoops = 4f;
		
		DecoHelperThisOrThat decoHelperThisOrThat = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, riverShrubs, riverTrees);
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
