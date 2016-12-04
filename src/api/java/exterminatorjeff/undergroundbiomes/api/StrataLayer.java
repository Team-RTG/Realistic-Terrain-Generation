package exterminatorjeff.undergroundbiomes.api;

import exterminatorjeff.undergroundbiomes.api.names.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * 
 * @author CurtisA, LouisDB
 *
 */
public final class StrataLayer {

	public final Block block;
	public final int meta;
	public final IBlockState filler;
	public final int minHeight, maxHeight;

	public StrataLayer(BlockEntry blockEntry, int meta, int minHeight, int maxHeight) {
		this(blockEntry.getBlock(), meta, minHeight, maxHeight);
	}

	@SuppressWarnings("deprecation")
	public StrataLayer(Block block, int meta, int minHeight, int maxHeight) {
		this.block = block;
		this.meta = meta;
		filler = block.getStateFromMeta(meta);
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	public boolean heightInLayer(int y) {
		return (y >= minHeight && y <= maxHeight);
	}

}
