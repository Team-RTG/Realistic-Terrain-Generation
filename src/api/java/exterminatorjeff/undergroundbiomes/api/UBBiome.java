package exterminatorjeff.undergroundbiomes.api;

import exterminatorjeff.undergroundbiomes.api.StrataLayer;
import com.google.common.base.Preconditions;

import exterminatorjeff.undergroundbiomes.api.names.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * 
 * @author CurtisA, LouisDB
 *
 */
public final class UBBiome {

    public final int ID;
	public final Block block;
	public final int meta;
	public final IBlockState filler;

	public StrataLayer[] strata;

	public UBBiome(int ID, BlockEntry blockEntry, int meta) {
		this(ID, blockEntry.getBlock(), meta);
	}

	@SuppressWarnings("deprecation")
	public UBBiome(int ID, Block block, int meta) {
            this.ID = ID;
		this.block = block;
		this.meta = meta;
		filler = block.getStateFromMeta(meta);
		Preconditions.checkNotNull(filler);
	}

	public UBBiome addStrataLayers(StrataLayer[] strata) {
		this.strata = strata;
		return this;
	}

	public IBlockState getStrataBlockAtLayer(int y) {
		for (StrataLayer layer : strata) {
			if (layer.heightInLayer(y))
				return layer.filler;
		}
		return filler;
	}

}
