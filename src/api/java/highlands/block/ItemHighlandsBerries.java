package highlands.block;

import highlands.Highlands;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHighlandsBerries extends ItemFood{

	public ItemHighlandsBerries() {
		super(2, false);//id, 1 whole porkchop (hunger), wolves don't eat it.
		this.setCreativeTab(Highlands.tabHighlands);
	}

	/**
     * How long it takes to use or consume an item
     */
	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 16;
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("Highlands:itemBerries");
    }
}
