package enhancedbiomes.items;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemSlab2 extends ItemSlab
{
	public ItemSlab2(Block p_i45355_2_) 
	{
		super(p_i45355_2_, (BlockSlab) p_i45355_2_, EnhancedBiomesBlocks.doubleSlab2, false);
	}
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + "_" + itemstack.getItemDamage();
    }
}
