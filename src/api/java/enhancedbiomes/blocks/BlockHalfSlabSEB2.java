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

public class BlockHalfSlabSEB2 extends BlockSlab
{
    /** The type of tree this slab came from. */
    public static String[] stoneType = BlockStoneEB.stones2;
    public static int domStoneSize = BlockStoneEB.stones.length;
    public boolean doubleSlab;

    IIcon[] stone = new IIcon[stoneType.length];
    IIcon[] stoneTop = new IIcon[stoneType.length];
    
    public BlockHalfSlabSEB2(boolean par2)
    {
        super(par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.useNeighborBrightness = true;
        doubleSlab = par2;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public IIcon getIcon(int par1, int par2)
    {        
    	if(par2 % 8 >= stoneType.length) return null;
    	
    	if(this == EnhancedBiomesBlocks.slabS2 || this == EnhancedBiomesBlocks.doubleSlabS2)
    	{
    		return par1 == 0 || par1 == 1 ? stoneTop[par2 % 8] : stone[par2 % 8];
    	}
    	else if(this == EnhancedBiomesBlocks.slabSB2 || this == EnhancedBiomesBlocks.doubleSlabSB2)
    	{
    		return EnhancedBiomesBlocks.stoneBrickEB.getIcon(par1, par2 % 8 + domStoneSize);
    	}

    	else if(this == EnhancedBiomesBlocks.slabSC2 || this == EnhancedBiomesBlocks.doubleSlabSC2)
    	{
    		return EnhancedBiomesBlocks.stoneCobbleEB.getIcon(par1, par2 % 8 + domStoneSize);
    	}
    	return null;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return this == EnhancedBiomesBlocks.slabS2 || this == EnhancedBiomesBlocks.doubleSlabS2 ? Item.getItemFromBlock(EnhancedBiomesBlocks.slabS2) : 
        	this == EnhancedBiomesBlocks.slabSC2 || this == EnhancedBiomesBlocks.doubleSlabSC2 ? Item.getItemFromBlock(EnhancedBiomesBlocks.slabSC2) : 
        	Item.getItemFromBlock(EnhancedBiomesBlocks.slabSC2);
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
    	Block block = this == EnhancedBiomesBlocks.slabS2 || this == EnhancedBiomesBlocks.doubleSlabS2 ? EnhancedBiomesBlocks.slabS2 : 
    		this == EnhancedBiomesBlocks.slabSB2 || this == EnhancedBiomesBlocks.doubleSlabSB2 ? EnhancedBiomesBlocks.slabSB2 : 
    		EnhancedBiomesBlocks.slabSC2;
        return new ItemStack(block, 1, par1 & 7);
    }

    @SideOnly(Side.CLIENT)

    @Override
     /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (par1 == Item.getItemFromBlock(EnhancedBiomesBlocks.slabS2) || 
        		par1 == Item.getItemFromBlock(EnhancedBiomesBlocks.slabSB2) || 
        		par1 == Item.getItemFromBlock(EnhancedBiomesBlocks.slabSC2))
        {
            for (int j = 0; j < stoneType.length; ++j)
            {
            	par3List.add(new ItemStack(par1, 1, j));
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister) 
    {
    	for (int i = 0; i < this.stoneType.length; ++i)
        {
            this.stone[i] = par1IIconRegister.registerIcon("enhancedbiomes:stoneSlab" + "_" + stoneType[i]);
            this.stoneTop[i] = par1IIconRegister.registerIcon("enhancedbiomes:stoneSlab" + "_" + stoneType[i] + "_top");
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock((this == EnhancedBiomesBlocks.doubleSlabS2 ? EnhancedBiomesBlocks.slabS2 : 
        	(this == EnhancedBiomesBlocks.doubleSlabSB2 ? EnhancedBiomesBlocks.slabSB2 : 
        		(this == EnhancedBiomesBlocks.doubleSlabSC2 ? EnhancedBiomesBlocks.slabSC2 : this))));
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

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta % 8 + 8) {
        case 6://cha
			return this.blockHardness - 0.7F;
		case 7://m
			return this.blockHardness + 0.2F;
		case 8://d
			return this.blockHardness;
		case 9://sc
			return this.blockHardness - 0.2F;
		case 10://che
			return this.blockHardness - 0.6F;
		case 11://g
			return this.blockHardness + 0.4F;
		case 12://d
			return this.blockHardness + 0.1F;
		default:
			break;
		}
    	return this.blockHardness;
    }
}
