package rtg.api.world.gen.feature.tree.rtg;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import rtg.api.util.BlockUtil;
import rtg.api.util.Logger;
import rtg.api.world.deco.DecoVariableAcacia;
import rtg.api.world.deco.DecoVariableBirch;
import rtg.api.world.deco.DecoVariableOak;
import rtg.api.world.deco.DecoVariableSpruce;
import rtg.api.world.deco.DecoVariableTree;

public class RTGSaplingManager {
	
	public boolean manages(IBlockState sapling) {
		if (!sapling.getBlock().equals(Blocks.SAPLING)) return false;
		
		Logger.info ("{} {}", sapling, Blocks.SAPLING.getDefaultState());
		if (similar(sapling, Blocks.SAPLING.getDefaultState())) return true;//Oak
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(1))) return true;//Spruce
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(2))) return true;//Birch
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(4))) return true;//Acacia
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(5))) return true;//Dark Oak
		return false;
	}
	
	public boolean reject2x2(IBlockState sapling) {
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(1))) return true;//Spruce
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(3))) return true;//Jungle
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(5))) return true;//Dark Oak
		return false;
	}
	
	public DecoVariableTree tree(IBlockState sapling) {
		if (!sapling.getBlock().equals(Blocks.SAPLING)) throw new RuntimeException();
		if (similar(sapling, Blocks.SAPLING.getDefaultState()))  return new DecoVariableOak();//Oak
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(1))) return new DecoVariableSpruce();//Spruce
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(2))) return new DecoVariableBirch();//Birch
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(4))) return new DecoVariableAcacia();//Acacia
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(5)))  throw new RuntimeException();//Dark Oak
		throw new RuntimeException();
	}
	
	public static boolean similar(IBlockState saplingBlock, IBlockState testSaplingBlock) {
		return saplingBlock.getBlock() == testSaplingBlock.getBlock() &&
                BlockUtil.getTypeFromSapling(saplingBlock) == BlockUtil.getTypeFromSapling(testSaplingBlock);
	}
	
	public boolean couldBeSwampWillow(IBlockState sapling) {
		if (similar(sapling, Blocks.SAPLING.getDefaultState()))  return true;
		return false;
	}
	
	public boolean darkOak(IBlockState sapling) {
		if (similar(sapling, Blocks.SAPLING.getStateFromMeta(5)))  return true;
		return false;
	}

}
