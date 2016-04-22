package highlands.block;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHLPlankSlab extends BlockSlab {

	//protected final boolean isDoubleSlab;
	private Block modelBlock;
	
	public BlockHLPlankSlab(boolean isDouble, Block model) {
		super(isDouble, model.getMaterial());
		//isDoubleSlab = isDouble;
		modelBlock = model;
		if (!isDouble) this.setCreativeTab(Highlands.tabHighlands);
		// fix lighting
		this.setLightOpacity(0);
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return modelBlock.getIcon(par1, par2 & 7);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return Item.getItemFromBlock(HighlandsBlocks.hlplankhalf);
    }

	@Override
    public String func_150002_b(int metadata)
    {
        if (metadata < 0 || metadata >= BlockHighlandsPlanks.woodType.length)
        {
            metadata = 0;
        }

        return super.getUnlocalizedName() + "." + BlockHighlandsPlanks.woodType[metadata];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        if (item != Item.getItemFromBlock(Blocks.double_wooden_slab))
        {
            for (int i = 0; i < BlockHighlandsPlanks.woodType.length; ++i)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {}
    
    // double slab code
    public boolean isDoubleSlab() {
    	return field_150004_a;
    }
    
    @Override
    protected ItemStack createStackedBlock(int metadata){
    	return new ItemStack(Item.getItemFromBlock(HighlandsBlocks.hlplankhalf), 2, metadata & 7);
    }
    
    public int slabsDropped() {
    	if (this.field_150004_a) {return 2;} else {return 1;}
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
    	ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    	Item item = getItemDropped(metadata, world.rand, fortune);
    	if (item != null) {
    		ret.add(new ItemStack(item, slabsDropped(),metadata&7));
    	}
    	return ret;
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int metadata, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        return this.field_150004_a ? p_149660_9_ : (metadata != 0 && (metadata == 1 || (double)p_149660_7_ <= 0.5D) ? p_149660_9_ : p_149660_9_ | 8);
    }
}
