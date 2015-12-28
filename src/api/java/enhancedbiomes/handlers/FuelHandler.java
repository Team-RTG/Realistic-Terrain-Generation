package enhancedbiomes.handlers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;

public class FuelHandler implements IFuelHandler 
{
	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		Item item = fuel.getItem();

		if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
        {
            Block block = Block.getBlockFromItem(item);

            if(block == saplingEB) return 100;
            else if(block == slab1) return 150;
            else if(block == slab2) return 150;
        }
		
		return 0;
	}	
}