package enhancedbiomes.items;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemSlabSC extends ItemSlab
{
	public ItemSlabSC(Block p_i45355_2_) 
	{
		super(p_i45355_2_, (BlockSlab) p_i45355_2_, EnhancedBiomesBlocks.doubleSlabSC, false);
	}
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + "_" + itemstack.getItemDamage();
    }
}
