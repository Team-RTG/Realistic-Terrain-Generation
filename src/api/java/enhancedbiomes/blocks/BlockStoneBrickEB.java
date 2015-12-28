package enhancedbiomes.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.helpers.EnhancedBiomesJava;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStoneBrickEB extends BlockStone 
{
    public static String[] stones = EnhancedBiomesJava.createList(BlockStoneEB.stones, BlockStoneEB.stones2);
	
	private IIcon[] iconArray = new IIcon[stones.length];

    public BlockStoneBrickEB()
	{
        super();
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

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int x = 0; x < stones.length; x++)
        {
        	par3List.add(new ItemStack(par1, 1, x));
        }
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return this.damageDropped(p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IIconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.iconArray = new IIcon[stones.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
        	this.iconArray[i] = par1IIconRegister.registerIcon("enhancedbiomes:" + this.getTextureName() + "_" + stones[i]);
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, par1);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    } 

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta) {
        case 0://b
			return this.blockHardness + 0.5F;
        case 1://sh
			return this.blockHardness - 0.3F;
		case 2://sa
			return this.blockHardness - 0.4F;
		case 3://l
			return this.blockHardness - 0.5F;
		case 4://sl
			return this.blockHardness - 0.1F;
		case 5://r
			return this.blockHardness + 0.3F;
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
