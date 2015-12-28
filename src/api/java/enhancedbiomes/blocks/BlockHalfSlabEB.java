package enhancedbiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHalfSlabEB extends BlockSlab
{
    /** The type of tree this slab came from. */
    public static String[] woodType0;
    public static String[] woodType1;
    public boolean doubleSlab;
    
    public BlockHalfSlabEB(boolean par2)
    {
        super(par2, Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.useNeighborBrightness = true;
        doubleSlab = par2;
        //Blocks.fire.setFireInfo(this, 5, 20);
        woodType0 = new String[] {	BlockLogOak.woodType[0], BlockLogOak.woodType[1], BlockLogOak.woodType[2], BlockLogOak.woodType[3],
									BlockLogSpruce.woodType[0], BlockLogSpruce.woodType[1], BlockLogSpruce.woodType[2], BlockLogSpruce.woodType[3]};
        
        woodType1 = new String[] {	BlockLogBirch.woodType[0], BlockLogBirch.woodType[1], BlockLogBirch.woodType[2], BlockLogBirch.woodType[3],
									BlockLogJungle.woodType[0], BlockLogJungle.woodType[1], BlockLogJungle.woodType[2], BlockLogJungle.woodType[3]};
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return EnhancedBiomesBlocks.planksEB.getIcon(par1, par2 % 8 + (this == EnhancedBiomesBlocks.slab2 || this == EnhancedBiomesBlocks.doubleSlab2 ? 8 : 0));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return this == EnhancedBiomesBlocks.slab2 || this == EnhancedBiomesBlocks.doubleSlab2 ? Item.getItemFromBlock(EnhancedBiomesBlocks.slab2) : Item.getItemFromBlock(EnhancedBiomesBlocks.slab1);
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
    	Block block = this == EnhancedBiomesBlocks.slab2 || this == EnhancedBiomesBlocks.doubleSlab2 ? EnhancedBiomesBlocks.slab2 : EnhancedBiomesBlocks.slab1;
        return new ItemStack(block, 2, par1 & 7);
    }

    /*/**
     * Returns the slab block name with step type.
     */
    /*@Override
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= (this == EnhancedBiomesBlocks.slab2 || this == EnhancedBiomesBlocks.doubleSlab2 ? woodType1.length : woodType0.length))
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + (this == EnhancedBiomesBlocks.slab2 || this == EnhancedBiomesBlocks.doubleSlab2 ? woodType1[par1] : woodType0[par1]);
    }*/

    @SideOnly(Side.CLIENT)

    @Override
     /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (par1 == Item.getItemFromBlock(EnhancedBiomesBlocks.slab1) || par1 == Item.getItemFromBlock(EnhancedBiomesBlocks.slab2))
        {
            for (int j = 0; j < 8; ++j)
            {
                if((this == EnhancedBiomesBlocks.slab2 ? woodType1[j] : woodType0[j]) != "")
                {
                	par3List.add(new ItemStack(par1, 1, j));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {}

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock((this == EnhancedBiomesBlocks.doubleSlab1 ? EnhancedBiomesBlocks.slab1 : (this == EnhancedBiomesBlocks.doubleSlab2 ? EnhancedBiomesBlocks.slab2 : this)));
    }

    /*@SideOnly(Side.CLIENT)

    /**
     * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
     */
    /*@Override
    private static boolean func_150003_a(Block par0)
    {
        return par0 == Blocks.stone_slab || par0 == Blocks.wooden_slab || par0 == EnhancedBiomesBlocks.slab1 || par0 == EnhancedBiomesBlocks.slab2;
    }*/

	@Override
	public String func_150002_b(int var1) 
	{
		return null;
	}
}
