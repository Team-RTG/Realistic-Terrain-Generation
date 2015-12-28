package enhancedbiomes.api.internal;

import net.minecraft.block.Block;

public class OreGenEntry 
{
	public Block ore;
	public Block defaultOre;
	public int minHeight;
	public int maxHeight;
	public int rate;
	public int quantity;
	
	public OreGenEntry(Block ore, Block originalOre, int minHeight, int maxHeight, int rate, int quantity) {
		this.ore = ore;
		this.defaultOre = originalOre;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.rate = rate;
		this.quantity = quantity;
	}
}
