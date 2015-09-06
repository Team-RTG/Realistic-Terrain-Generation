package rtg.data;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class VillageMaterialData 
{
	public int biomeID;
	
	public Block logBlock = Blocks.log;
	public Block cobbleBlock = Blocks.cobblestone;
	public Block plankBlock = Blocks.planks;
	public Block pathBlock = Blocks.cobblestone;
	public Block stairsWoodBlock = Blocks.oak_stairs;
	public Block stairsStoneBlock = Blocks.stone_stairs;
	public Block slabsBlock = Blocks.stone_slab;
	
	public int logBlockMeta = 0;
	public int cobbleBlockMeta = 0;
	public int plankBlockMeta = 0;
	public int pathBlockMeta = 0;
	public int slabsBlockMeta = 0;
	
	public VillageMaterialData(BiomeGenBase biome)
	{	
		biomeID = biome.biomeID;
	}
}
