package enhancedbiomes.blocks;

import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;
import enhancedbiomes.helpers.TreeGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaplingEnhancedBiomes extends BlockBush implements IGrowable
{
	public static final String[] woodType = BlockPlanksEB.woodType;
	public static final String[] names = EnhancedBiomesBlocks.woodNames;

	IIcon[] saplingIcons = new IIcon[woodType.length];
	
	float pixelSize = 0.0625F;	
	
	public BlockSaplingEnhancedBiomes()
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
    	switch(meta) 
    	{
		//Oak
    	case 0:
			wh[0] = 16;	wh[1] = 16;	break;
		case 1:
			wh[0] = 14;	wh[1] = 13;	break;
		case 2:
			wh[0] = 6;	wh[1] = 15;	break;
		case 3:
			wh[0] = 8;	wh[1] = 9;	break;
		//Spruce
		case 4:
			wh[0] = 10;	wh[1] = 13;	break;
		case 5:
			wh[0] = 8;	wh[1] = 15;	break;
		case 6:
			wh[0] = 10;	wh[1] = 16;	break;
		case 7:
			wh[0] = 6;	wh[1] = 16;	break;
		//Birch
		case 8:
			wh[0] = 14;	wh[1] = 16;	break;
		case 9:
			wh[0] = 16;	wh[1] = 15;	break;
		case 10:
			wh[0] = 8;	wh[1] = 14;	break;
		case 11:
			wh[0] = 16;	wh[1] = 16;	break;
		//Jungle
		case 12:
			wh[0] = 12;	wh[1] = 11;	break;
		case 13:
			wh[0] = 16;	wh[1] = 16;	break;
		case 14:
			wh[0] = 16;	wh[1] = 15;	break;
		case 15:
			wh[0] = 16;	wh[1] = 16;	break;
		default:
			wh[0] = 16;	wh[1] = 16;	break;
		}
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
        	if(woodNames[a] != "" && woodNames[a] != "Dead")
    		{
    			this.saplingIcons[a] = par1IconRegister.registerIcon("enhancedbiomes:sapling_" + this.woodType[a]);
    		}
        }
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
    	return this.saplingIcons[par2];
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

        int meta = par1World.getBlockMetadata(par2, par3, par4);
        Object var7 = null;
        int var8 = 0;
        int var9 = 0;
        boolean var10 = false;

        for (var8 = 0; var8 >= -1; --var8)
        {
            for (var9 = 0; var9 >= -1; --var9)
            {
            	if(meta == 4)
            	{
            		if (this.isSameSapling(par1World, par2 + var8, par3, par4 + var9, meta) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9, meta) && this.isSameSapling(par1World, par2 + var8, par3, par4 + var9 + 1, meta) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9 + 1, meta))
                    {
                		var7 = TreeGen.fir_large(par5Random);
            			var10 = true;
            			break;
                    }
            	}
            	else if(meta == 15)
            	{
            		if (this.isSameSapling(par1World, par2 + var8, par3, par4 + var9, meta) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9, meta) && this.isSameSapling(par1World, par2 + var8, par3, par4 + var9 + 1, meta) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9 + 1, meta))
                	{
                		var7 = TreeGen.kapok(par5Random);
            			var10 = true;
            			break;
                    }
            	}
            }

            if (var7 != null)
            {
                break;
            }
        }

        if (var7 == null)
        {
            var9 = 0;
            var8 = 0;
            
            switch(meta)
            {
            case 0:
            	var7 = TreeGen.greatOak(par5Random);
            	break;
            case 1:
            	var7 = TreeGen.thorntree();
            	break;
            case 2:
            	var7 = TreeGen.poplar(par5Random);
            	break;
            case 3:
            	var7 = TreeGen.mangrove();
            	break;
            case 4:
            	var7 = TreeGen.fir(par5Random);
            	break;
            case 5:
            	var7 = TreeGen.cypress(par5Random);
            	break;
            case 6:
            	var7 = TreeGen.pine(par5Random);
            	break;
            case 7:
            	var7 = TreeGen.silverPine(par5Random);
            	break;
            case 8:
            	var7 = TreeGen.alder(par5Random);
            	break;
            case 9:
            	var7 = TreeGen.eucalyptus(par5Random);
            	break;
            case 10:
            	var7 = TreeGen.aspen(par5Random);
            	break;
            case 11:
            	var7 = null;//TreeGen.;
            	break;
            case 12:
            	var7 = TreeGen.baobab(par5Random);
            	break;
            case 13:
            	var7 = null;//TreeGen.;
            	break;
            case 14:
            	var7 = TreeGen.cherry(par5Random);
            	break;
            case 15:
            	var7 = null;//TreeGen.;
            	break;
            default:
            	var7 = TreeGen.greatOak(par5Random);
            }
            
            if (var7 == null)
            {
            	var7 = TreeGen.greatOak(par5Random);
            }
        }
        
        if (var10)
        {
            par1World.setBlockToAir(par2 + var8, par3, par4 + var9);
            par1World.setBlockToAir(par2 + var8 + 1, par3, par4 + var9);
            par1World.setBlockToAir(par2 + var8, par3, par4 + var9 + 1);
            par1World.setBlockToAir(par2 + var8 + 1, par3, par4 + var9 + 1);
        }
        else
        {
            par1World.setBlockToAir(par2, par3, par4);
        }

        if (!((WorldGenerator)var7).generate(par1World, par5Random, par2 + var8, par3, par4 + var9))
        {
            if (var10)
            {
                par1World.setBlock(par2 + var8, par3, par4 + var9, this, meta, 3);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, this, meta, 3);
                par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, this, meta, 3);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, this, meta, 3);
            }
            else
            {
                par1World.setBlock(par2, par3, par4, this, meta, 3);
            }
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlock(par2, par3, par4) == this && par1World.getBlockMetadata(par2, par3, par4) == par5;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)

    @Override
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int a = 0; a < 16; a++)
    	{
    		if(woodNames[a] != "" && woodNames[a] != "Dead")
    		{
    			par3List.add(new ItemStack(par1, 1, a)); 
    		}
    	}
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
}
