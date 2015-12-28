package enhancedbiomes.blocks;

import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.gen.WorldGenSaguaro;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaguaroSapling extends BlockBush implements IGrowable
{
	IIcon saplingIcon;
	
	float pixelSize = 0.0625F;	
	
	public BlockSaguaroSapling()
    {
        super();
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
    }

	/**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) 
    {
        float[] wh = getSaplingWidthAndHeight(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
        wh[0] *= pixelSize / 2;
        wh[1] *= pixelSize;
    	this.setBlockBounds(0.5F - wh[0], 0.0F, 0.5F - wh[0], 0.5F + wh[0], wh[1], 0.5F + wh[0]);
    }
    
    public float[] getSaplingWidthAndHeight(int meta)
    {
    	float[] wh = new float[2];
    	wh[0] = 16;	
    	wh[1] = 16;
		return wh;    	
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                this.markOrGrowMarked(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IIconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        for(int a = 0; a < 16; a++)
        {
        	this.saplingIcon = par1IconRegister.registerIcon("enhancedbiomes:saguaro_sapling");
        }
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
    	return this.saplingIcon;
    }
    
    public void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.growTree(par1World, par2, par3, par4, par5Random);
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;

        Object var7 = new WorldGenSaguaro();
        
        par1World.setBlockToAir(par2, par3, par4);

        if (!((WorldGenerator)var7).generate(par1World, par5Random, par2, par3, par4))
        {
        	par1World.setBlock(par2, par3, par4, this, 0, 3);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return 0;
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return (double)p_149852_1_.rand.nextFloat() < 0.45D;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.markOrGrowMarked(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }

    /**
     * is the block grass, dirt or farmland
     */
    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.sand;
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
    	return EnumPlantType.Desert;
    }
}
