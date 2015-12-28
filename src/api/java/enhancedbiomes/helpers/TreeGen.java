package enhancedbiomes.helpers;

import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;

import java.util.Random;

import enhancedbiomes.world.gen.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSwamp;

public class TreeGen 
{
	public static WorldGenAbstractTree greatOak(Random rand){return new WorldGenOak(logOak, 0, leavesOak, 0, 4 + rand.nextInt(3), 5, 16);}
	public static WorldGenAbstractTree thorntree(){return new WorldGenAcacia(1, logOak, 1, leavesOak, 1);}
	public static WorldGenAbstractTree poplar(Random rand){return new WorldGenPoplar(logOak, 2, leavesOak, 2, 2 + rand.nextInt(3));}
	public static WorldGenAbstractTree mangrove(){return new WorldGenMangrove(logOak, 3, leavesOak, 3, 3);}
	//TODO New trees
	//public static WorldGenAbstractTree tallow(Random rand){return new WorldGenTallow(logOak, 2, leavesOak, 2, 4 + rand.nextInt(2));}

	public static WorldGenAbstractTree fir(Random rand){return new WorldGenFir(3 + rand.nextInt(3), logSpruce, 0, leavesSpruce, 0, false);}
	public static WorldGenAbstractTree cypress(Random rand){return new WorldGenCypress(logSpruce, 1, leavesSpruce, 1, 4 + rand.nextInt(3));}
	public static WorldGenAbstractTree pine(Random rand){return new WorldGenPine(logSpruce, 2, leavesSpruce, 2, 4 + rand.nextInt(3));}
	public static WorldGenAbstractTree silverPine(Random rand){return new WorldGenSilverPine(logSpruce, 3, leavesSpruce, 3, 6 + rand.nextInt(3));}
	//TODO New trees
	//public static WorldGenAbstractTree wollemi(Random rand){return new WorldGenWollemiPine(logSpruce, 2, leavesSpruce, 2, 12 + rand.nextInt(6));}

	public static WorldGenAbstractTree alder(Random rand){return new WorldGenAlder(logBirch, 0, leavesBirch, 0, 6 + rand.nextInt(3), 5, 4);}
	public static WorldGenAbstractTree eucalyptus(Random rand){return new WorldGenEucalyptus(logBirch, 1, leavesBirch, 1, 8 + rand.nextInt(4), 5, 8);}
	public static WorldGenAbstractTree aspen(Random rand){return new WorldGenAspen(logBirch, 2, leavesBirch, 2, 8 + rand.nextInt(3));}

	public static WorldGenAbstractTree baobab(Random rand){return new WorldGenBaobab(logJungle, 0, leavesJungle, 0, 4 + rand.nextInt(3));}
	public static WorldGenAbstractTree dead(Random rand){return new WorldGenOak(logJungle, 1, Blocks.air, 0, 4 + rand.nextInt(3), 5, 16);}
	public static WorldGenAbstractTree cherry(Random rand){
		int meta;
    	if(rand.nextInt(6) == 0) meta = 1; 
    	else meta = 2;
        return (WorldGenAbstractTree)(new WorldGenTreesEnhancedBiomes(true, 4, 2, meta, false, logJungle, leavesJungle));
	}
	public static WorldGenAbstractTree kapok(Random rand){return new WorldGenKapok(logJungle, 3, leavesJungle, 3, 30 + rand.nextInt(15));}
	
	public static WorldGenAbstractTree fir_large(Random rand){return new WorldGenFir(5 + rand.nextInt(6), logSpruce, 0, leavesSpruce, 0, true);}	
	public static WorldGenAbstractTree cherry_large(){return new WorldGenShrineTree(true);}
	
	public static WorldGenAbstractTree eucalyptus_shrub(Random rand){return new WorldGenTallShrub(logBirch, 1, leavesBirch, 1, 2);}

	public static WorldGenAbstractTree birch(){return new WorldGenForest(false, false);}
	public static WorldGenAbstractTree swamp(){return new WorldGenSwamp();}
}
