package enhancedbiomes.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.renderer.BlockOreRenderer;
import enhancedbiomes.helpers.EnhancedBiomesJava;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOreEB extends Block
{
	public static String[] stones = EnhancedBiomesJava.createList(BlockStoneEB.stones, BlockStoneEB.stones2);
	
	private IIcon[] iconArray = new IIcon[stones.length];
	private IIcon overlay;
	private IIcon basicTexture;
	
	public BlockOreEB()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return this == EnhancedBiomesBlocks.oreCoalEB ? Items.coal : (this == EnhancedBiomesBlocks.oreDiamondEB ? Items.diamond : (this == EnhancedBiomesBlocks.oreLapisEB ? Items.dye : (this == EnhancedBiomesBlocks.oreEmeraldEB ? Items.emerald : Item.getItemFromBlock(EnhancedBiomesMod.getOreDrop(this)))));
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return this == EnhancedBiomesBlocks.oreLapisEB ? 4 + p_149745_1_.nextInt(5) : 1;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        if (p_149679_1_ > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, p_149679_2_, p_149679_1_))
        {
            int j = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }

            return this.quantityDropped(p_149679_2_) * (j + 1);
        }
        else
        {
            return this.quantityDropped(p_149679_2_);
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
    }

    private Random rand = new Random();
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this))
        {
            int j1 = 0;

            if (this == EnhancedBiomesBlocks.oreCoalEB)
            {
                j1 = MathHelper.getRandomIntegerInRange(rand, 0, 2);
            }
            else if (this == EnhancedBiomesBlocks.oreDiamondEB)
            {
                j1 = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            }
            else if (this == EnhancedBiomesBlocks.oreEmeraldEB)
            {
                j1 = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            }
            else if (this == EnhancedBiomesBlocks.oreLapisEB)
            {
                j1 = MathHelper.getRandomIntegerInRange(rand, 2, 5);
            }

            return j1;
        }
        return 0;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return this == EnhancedBiomesBlocks.oreLapisEB ? 4 : this == EnhancedBiomesBlocks.oreIronEB || this == EnhancedBiomesBlocks.oreGoldEB ? p_149692_1_ : 0;
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

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IIconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.iconArray = new IIcon[stones.length];

        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IIconRegister.registerIcon("enhancedbiomes:stone_" + stones[i]);
        }
        
        this.overlay = par1IIconRegister.registerIcon("enhancedbiomes:overlay_" + this.getTextureName());
        this.basicTexture = par1IIconRegister.registerIcon(this.getTextureName());
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return this.basicTexture;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getStones(int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length) {
            par2 = 0;
        }

        if(this.iconArray[par2] == null) System.err.println("Stones not loaded");
    	return this.iconArray[par2];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getOverlay() {
        if(this.overlay == null) System.err.println("Overlay not loaded");
    	return this.overlay;
    }

	@Override
	public int getRenderType() {
		return BlockOreRenderer.renderID;
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