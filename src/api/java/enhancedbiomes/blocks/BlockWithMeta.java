package enhancedbiomes.blocks;

import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;
import static net.minecraft.init.Blocks.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BlockWithMeta {		
	public Block block;
	public byte meta;
	
	public BlockWithMeta(Block block, int meta) {
		this.block = block;
		this.meta = (byte) meta;
	}

	public static BlockWithMeta stone;
	public static BlockWithMeta basalt;
	public static BlockWithMeta shale;
	public static BlockWithMeta sandstone;
	public static BlockWithMeta limestone;
	public static BlockWithMeta slate;
	public static BlockWithMeta rhyolite;
	
	public static BlockWithMeta chalk;
	public static BlockWithMeta marble;
	public static BlockWithMeta dolomite;
	public static BlockWithMeta schist;
	public static BlockWithMeta chert;
	public static BlockWithMeta gabbro;
	public static BlockWithMeta dacite;

	public static BlockWithMeta dirt;
	public static BlockWithMeta alfisol;
	public static BlockWithMeta andisol;
	public static BlockWithMeta gelisol;
	public static BlockWithMeta histosol;
	public static BlockWithMeta inceptisol;
	public static BlockWithMeta mollisol;
	public static BlockWithMeta oxisol;

	public static BlockWithMeta[] rocksIgneous;
	public static BlockWithMeta[] rocksMetamorphic;
	public static BlockWithMeta[] rocksSedimentary;
	
	public static void createList() {
		stone = new BlockWithMeta(Blocks.stone, 0);
		basalt = new BlockWithMeta(stoneEB, 0);
		shale = new BlockWithMeta(stoneEB, 1);
		sandstone = new BlockWithMeta(stoneEB, 2);
		limestone = new BlockWithMeta(stoneEB, 3);
		slate = new BlockWithMeta(stoneEB, 4);
		rhyolite = new BlockWithMeta(stoneEB, 5);
		
		chalk = new BlockWithMeta(stoneEB, 6);
		marble = new BlockWithMeta(stoneEB, 7);
		dolomite = new BlockWithMeta(stoneEB, 8);
		schist = new BlockWithMeta(stoneEB, 9);
		chert = new BlockWithMeta(stoneEB, 10);
		gabbro = new BlockWithMeta(stoneEB, 11);
		dacite = new BlockWithMeta(stoneEB, 12);

		dirt = new BlockWithMeta(Blocks.dirt, 0);
		alfisol = new BlockWithMeta(dirtEB, 0);
		andisol = new BlockWithMeta(dirtEB, 1);
		gelisol = new BlockWithMeta(dirtEB, 3);
		histosol = new BlockWithMeta(dirtEB, 4);
		inceptisol = new BlockWithMeta(dirtEB, 5);
		mollisol = new BlockWithMeta(dirtEB, 6);
		oxisol = new BlockWithMeta(dirtEB, 7);

		rocksIgneous = new BlockWithMeta[]{basalt, rhyolite, gabbro, dacite};
		rocksMetamorphic = new BlockWithMeta[]{slate, marble, schist};
		rocksSedimentary = new BlockWithMeta[]{shale, sandstone, limestone, chalk, dolomite, chert};
	}
}