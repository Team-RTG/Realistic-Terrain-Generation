package enhancedbiomes.items;

import enhancedbiomes.blocks.BlockLogJungle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockLogJungle extends ItemBlockWithMetadata
{
    private Block theBlock;
    static String[] names = BlockLogJungle.woodType;

    public ItemBlockLogJungle(Block par2Block)
    {
        super(par2Block, par2Block);
        this.theBlock = par2Block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + "." + names[itemstack.getItemDamage()%4];
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public IIcon getIconFromDamage(int par1)
    {
        return this.theBlock.getIcon(2, par1);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }
}
