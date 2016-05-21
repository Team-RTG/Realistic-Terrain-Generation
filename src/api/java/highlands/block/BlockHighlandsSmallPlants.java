package highlands.block;

import static net.minecraftforge.common.EnumPlantType.Plains;
import highlands.Highlands;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHighlandsSmallPlants extends BlockFlower implements IPlantable
{
	private int plantType;
	
	private String[] plantNames = 
		{
			"BlueFlower",
			"GreenLeaf",
			"WhiteFlower",
			"Cattail",
			"Lavender",
			"RaspberryBush",
			"BlueberryBush",
			"Thornbush",
			"Cotton",
		};
	
    public BlockHighlandsSmallPlants(int type)
    {
        super(type);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(Highlands.tabHighlands);
        plantType = type;
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	//TODO- all this ItemStack stuff, again, worries me
    	if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==15 && player.inventory.getCurrentItem() == new ItemStack(Items.dye))
    	{
	    	Random rand = new Random();
	    	//adds random plants of same type to surrounding blocks
	    	new WorldGenSmallPlants(this, 10).generate(par1World, new Random(), par2, par3, par4);
	    	
	    	//reduce bonemeal stack size by one
	    	if(player.capabilities.isCreativeMode != true)player.inventory.getCurrentItem().stackSize--;
		    return true;
    	}
    	return false;
    }
    
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
    /**
     * Returns the Item to drop on destruction.
     */
	@Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Blocks.dirt.getItemDropped(0, par2Random, par3);
    }
    
    @Override 
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (plantType == 5 || plantType == 6) {
        	ret.add(new ItemStack(HighlandsBlocks.berries, 1, 0));
        }
        else {
        	ret.add(new ItemStack(this, 1, 0));
        }
        return ret;
    }
    
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity)
    {
    	if(plantType == 7) 
    	if (plantType == 7 && (entity instanceof EntityCreature || entity instanceof EntityPlayer)) {
    		entity.attackEntityFrom(DamageSource.cactus, 1);
        	if (entity instanceof EntityCreature) {
        		EntityCreature entityCreature = (EntityCreature)entity;
        		float rotation = entityCreature.getRotationYawHead();
        		entityCreature.motionY = 0.2;
        		entityCreature.motionX = -(Math.sin(Math.toRadians(rotation)) * 0.5);
        		entityCreature.motionZ = (Math.cos(Math.toRadians(rotation)) * 0.5);
        	}
        }
    }

    //IPlantable
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return Plains;
    }
    
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	this.blockIcon = par1IconRegister.registerIcon("Highlands:plant"+plantNames[plantType]);
    }
    
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		list.add(new ItemStack(block, 1, plantType));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}
    
}
