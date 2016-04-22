package highlands;

import highlands.api.HighlandsBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HighLandsCreativeTab extends CreativeTabs{

	public HighLandsCreativeTab() {
		super("Highlands");
	}

	public ItemStack getIconItemStack() {
		return new ItemStack(HighlandsBlocks.ironwoodSapling, 1, 0);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(HighlandsBlocks.ironwoodSapling);
	}
}
