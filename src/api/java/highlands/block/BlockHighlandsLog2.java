package highlands.block;

import highlands.Highlands;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHighlandsLog2 extends BlockLog
{
    /** The type of tree this log came from. */
	private String[] treeNames = 
		{
			"Hedge",
			"JapaneseMaple",			
		};
	private int treeType;
    
    @SideOnly(Side.CLIENT)
    private IIcon tree_side;
    @SideOnly(Side.CLIENT)
    private IIcon tree_top;

    public BlockHighlandsLog2(int type)
    {
        super();
        this.treeType = type;
        this.setCreativeTab(Highlands.tabHighlands);
        //setBurnProperties(this.blockID, 5, 5);
    }
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
	    if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==3 && player.inventory.getCurrentItem() == new ItemStack(Items.dye))
	    {

	    }
	    return false;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return 0;
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        int k = par2 & 12;
        int l = par2 & 3;
        return k == 0 && (par1 == 1 || par1 == 0) ? this.tree_top : (k == 4 && (par1 == 5 || par1 == 4) ? this.tree_top : (k == 8 && (par1 == 2 || par1 == 3) ? this.tree_top : this.tree_side));
    }
    
    public IIcon getIcon(int par1, int par2)
    {
        return this.getBlockTextureFromSideAndMetadata(par1, par2);
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Block par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, 0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.tree_top = par1IconRegister.registerIcon("Highlands:log"+treeNames[treeType]+"Top");
        this.tree_side = par1IconRegister.registerIcon("Highlands:log"+treeNames[treeType]+"Side");
    }

    //TODO- mfr isn't for 1.7.x yet
	//// MFR : IFactoryHarvestable
//	@Override
//	public int getPlantId() {
//		return blockID;
//	}
//
//	@Override
//	public HarvestType getHarvestType() {
//		return HarvestType.Tree;
//	}
//
//	@Override
//	public boolean breakBlock() {
//		return true;
//	}
//
//	@Override
//	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
//		return world.getBlockId(x,y,z) == blockID;
//	}
//
//	@Override
//	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
//		return Block.blocksList[ world.getBlockId(x,y,z) ].getBlockDropped(world, x,y,z, world.getBlockMetadata(x,y,z), 0);
//	}
//
//	@Override
//	public void preHarvest(World world, int x, int y, int z) {
//	}
//
//	@Override
//	public void postHarvest(World world, int x, int y, int z) {
//	}
}
