package enhancedbiomes.blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSoilEB extends Block
{
    public static final String[] soils = new String[] {"alfisol", "andisol", "entisol", "gelisol", 
    												   "histosol", "inceptisol", "mollisol", "oxisol"};
    public IIcon[] icons = new IIcon[soils.length];
    
    public BlockSoilEB()
    {
        super(Material.ground);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int x, int y, int z, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
        	/*if (p_149674_1_.getBlockLightValue(x, y + 1, z) >= 4 && p_149674_1_.getBlockLightOpacity(x, y + 1, z) <= 2)
            {
                int count = 0;
                for(int chX = -1; chX <= 1; chX++) {
                    for(int chY = -1; chY <= 3; chY++) {
                        for(int chZ = -1; chZ <= 1; chZ++) {
                        	if (p_149674_1_.getBlock(x + chX, y + chY, z + chZ) == Blocks.grass && p_149674_1_.getBlockLightValue(x + chX, y + chY + 1, z + chZ) >= 9) count++;
                        }
                    }
                }
        		
        		for (int l = 0; l < 4 * count; ++l) {
                    if (p_149674_5_.nextInt(45) == 0) {
                    	p_149674_1_.setBlock(x, y, z, EnhancedBiomesBlocks.grassEB, p_149674_1_.getBlockMetadata(x, y, z), 2);
                    	return;
                    }
                }
            }*/
        	if (p_149674_1_.getBlockLightValue(x, y + 1, z) >= 4 && p_149674_1_.getBlockLightOpacity(x, y + 1, z) <= 2)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = x + p_149674_5_.nextInt(3) - 1;
                    int j1 = y + p_149674_5_.nextInt(5) - 1;
                    int k1 = z + p_149674_5_.nextInt(3) - 1;
                    Block block = p_149674_1_.getBlock(i1, j1 + 1, k1);

                    if (p_149674_1_.getBlock(i1, j1, k1) == Blocks.grass && p_149674_1_.getBlockLightValue(i1, j1 + 1, k1) >= 9)
                    {
                    	p_149674_1_.setBlock(x, y, z, EnhancedBiomesBlocks.grassEB, p_149674_1_.getBlockMetadata(x, y, z), 2);
                    }
                }
            }
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return meta < icons.length ? icons[meta] : icons[0];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int meta)
    {
        return meta;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
    	for(int a = 0; a < soils.length; a++)
        {
    		if(a != 2) p_149666_3_.add(new ItemStack(this, 1, a));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        for(int a = 0; a < soils.length; a++)
        {
        	if(soils[a] != "entisol") icons[a] = p_149651_1_.registerIcon("enhancedbiomes:dirt_" + soils[a]);
        }
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        int l = p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_);

        return l;
    }
    
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        switch (plantType)
        {
            case Plains: return true;
            case Beach: return 	world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
        						world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
        						world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
        						world.getBlock(x,     y, z + 1).getMaterial() == Material.water;
            default: return super.canSustainPlant(world, x, y, z, direction, plantable);
        }
    }
}