package enhancedbiomes.creativetab;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class CreativeTabEnhancedBiomes extends CreativeTabs
{
	int tabType;
	
    public CreativeTabEnhancedBiomes(int par1, String par2Str, int par3)
    {
        super(par1, par2Str);
        tabType = par3;
    }

    @SideOnly(Side.CLIENT)

    /**
     * the item to be displayed on the tab
     */
    @Override
	public Item getTabIconItem() 
	{
		return tabType == 0 ? Item.getItemFromBlock(EnhancedBiomesBlocks.saplingEB) : Item.getItemFromBlock(EnhancedBiomesBlocks.stoneEB);
	}
}
