package enhancedbiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockPlanksEB extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {	BlockLogOak.woodType[0], BlockLogOak.woodType[1], BlockLogOak.woodType[2], BlockLogOak.woodType[3],
															BlockLogSpruce.woodType[0], BlockLogSpruce.woodType[1], BlockLogSpruce.woodType[2], BlockLogSpruce.woodType[3],
															BlockLogBirch.woodType[0], BlockLogBirch.woodType[1], BlockLogBirch.woodType[2], BlockLogBirch.woodType[3],
															BlockLogJungle.woodType[0], BlockLogJungle.woodType[1], BlockLogJungle.woodType[2], BlockLogJungle.woodType[3]};
    
    public static final String[] names = EnhancedBiomesBlocks.woodNames;

    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public BlockPlanksEB()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.textureName = "wood";
        //Blocks.fire.setFireInfo(this, 5, 20);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length)
        {
            par2 = 0;
        }

        return this.iconArray[par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int x = 0; x < 16; x++)
        {
        	if(woodType[x] != "")
        	{
            	par3List.add(new ItemStack(par1, 1, x));
        	}
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IIconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.iconArray = new IIcon[woodType.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
        	if(woodType[i] != "")
        	{
            	this.iconArray[i] = par1IIconRegister.registerIcon("enhancedbiomes:" + this.getTextureName() + "_" + woodType[i]);
        	}
        }
    }
}
