package enhancedbiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;

public class BlockLogOak extends BlockRotatedPillar
{
    /** The type of tree this log came from. */
    public static final String[] woodType = new String[] {"greatOak", "savannah", "poplar", "mangrove"};
    public static final String[] names = new String[]{woodNames[0], woodNames[1], woodNames[2], woodNames[3]};
    private IIcon[] field_111052_c = new IIcon[4];
    private IIcon[] tree_top = new IIcon[4];

    protected BlockLogOak()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
        //Blocks.fire.setFireInfo(this, 5, 5);
        this.setBlockTextureName("log");
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        Block j2 = par1World.getBlock(par2 + k1, par3 + l1, par4 + i2);

                        if (j2 != null)
                        {
                            j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * The icon for the side of the block.
     */
    @Override
    protected IIcon getSideIcon(int par1)
    {
        return this.field_111052_c[par1];
    }

    @SideOnly(Side.CLIENT)

    /**
     * The icon for the tops and bottoms of the block.
     */
    @Override
    protected IIcon getTopIcon(int par1)
    {
        return this.tree_top[par1];
    }

    /*/**
     * returns a number between 0 and 3
     */
    /*@Override
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }*/

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.field_111052_c = new IIcon[woodType.length];
        this.tree_top = new IIcon[woodType.length];

        for (int i = 0; i < this.field_111052_c.length; ++i)
        {
        	if(woodType[i] != "")
    		{
    			this.field_111052_c[i] = par1IconRegister.registerIcon("enhancedbiomes:" + this.getTextureName() + "_" + woodType[i]);
    			this.tree_top[i] = par1IconRegister.registerIcon("enhancedbiomes:" + this.getTextureName() + "_" + woodType[i] + "_top");
    		}
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
