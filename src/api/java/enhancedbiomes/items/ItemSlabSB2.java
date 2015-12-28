package enhancedbiomes.items;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemSlabSB2 extends ItemSlab
{
	public ItemSlabSB2(Block p_i45355_2_) 
	{
		super(p_i45355_2_, (BlockSlab) p_i45355_2_, EnhancedBiomesBlocks.doubleSlabSB2, false);
	}
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + "_" + itemstack.getItemDamage();
    }
}
