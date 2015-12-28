package enhancedbiomes.items;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemSlabSB extends ItemSlab
{
	public ItemSlabSB(Block p_i45355_2_) 
	{
		super(p_i45355_2_, (BlockSlab) p_i45355_2_, EnhancedBiomesBlocks.doubleSlabSB, false);
	}
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + "_" + itemstack.getItemDamage();
    }
}
