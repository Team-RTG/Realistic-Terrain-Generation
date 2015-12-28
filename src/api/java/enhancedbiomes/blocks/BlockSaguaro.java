package enhancedbiomes.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.blocks.tileentity.TileEntitySaguaro;
import enhancedbiomes.helpers.EnhancedBiomesMath;
import enhancedbiomes.items.EnhancedBiomesItems;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import static enhancedbiomes.blocks.EnhancedBiomesBlocks.saguaro;
import static enhancedbiomes.helpers.EnhancedBiomesMath.isInputInList;

public class BlockSaguaro extends Block implements IPlantable, ITileEntityProvider {
	
	IIcon iconL;
	IIcon iconR;
	IIcon iconTop;
	IIcon iconTip;
	IIcon iconCentre;
	IIcon iconSide;
	
	public BlockSaguaro() {
		super(Material.cactus);
        this.setTickRandomly(true);
	}

    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    }

    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySaguaro();
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int meta = world.getBlockMetadata(x, y, z);
    	
    	if(world.isAirBlock(x, y + 1, z) && meta == 2)
        {
    		if(rand.nextInt(50) != 0) return;
        	
        	int count;            
            for(count = 1; world.getBlock(x, y - count, z) == this && world.getBlockMetadata(x, y - count, z) == 0; count++) {}
            
            if(count < 4) {
            	world.setBlock(x, y + 1, z, saguaro, 2, 3);
            	world.setBlockMetadataWithNotify(x, y, z, 0, 3);
            	world.setBlockMetadataWithNotify(x, y + 1, z, 2, 3);
            }
        }
    	
    	if(world.isAirBlock(x, y + 1, z) && meta == 11)
        {
    		if(rand.nextInt(50) != 0) return;
        	
        	int count;            
            for(count = 1; world.getBlock(x, y - count, z) == this && world.getBlockMetadata(x, y - count, z) == 0; count++) {}
            
            if(count < 7) {
            	world.setBlock(x, y + 1, z, saguaro, 11, 3);
            	world.setBlockMetadataWithNotify(x, y, z, 0, 3);
            	world.setBlockMetadataWithNotify(x, y + 1, z, 11, 3);
            }
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
    {
        p_149670_5_.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return false;
    }
    
    /*@Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) 
    {
    	float f = 0.0625F;
        int meta = world.getBlockMetadata(x, y, z);
        boolean isXNeg = isInputInList(meta, 1, 3, 4, 7);
        boolean isXPos = isInputInList(meta, 1, 3, 4, 8);
        boolean isZNeg = isInputInList(meta, 1, 5, 6, 9);
        boolean isZPos = isInputInList(meta, 1, 5, 6, 10);
        boolean isYNeg = isInputInList(meta, 0, 1, 2);
        boolean isYPos = isInputInList(meta, 0, 1, 7, 8, 9, 10);
        float minX = isXNeg ? 0 : f;
        float maxX = isXPos ? 1 : 1 - f;
        float minZ = isZNeg ? 0 : f;
        float maxZ = isZPos ? 1 : 1 - f;
        float minY = isYNeg ? 0 : f;
        float maxY = isYPos ? 1 : 1 - f;
        
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }*/

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IIconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.blockIcon = par1IIconRegister.registerIcon("enhancedbiomes:saguaro");
        this.iconL = par1IIconRegister.registerIcon("enhancedbiomes:saguaro_corner_L");
        this.iconR = par1IIconRegister.registerIcon("enhancedbiomes:saguaro_corner_R");
        this.iconTop = par1IIconRegister.registerIcon("enhancedbiomes:saguaro_end");
        this.iconTip = par1IIconRegister.registerIcon("enhancedbiomes:saguaro_tip");
        this.iconCentre = par1IIconRegister.registerIcon("enhancedbiomes:saguaro_centre");
        this.iconSide = par1IIconRegister.registerIcon("enhancedbiomes:saguaro_side");
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public IIcon getIcon(int side, int meta)
    {
        IIcon icon = this.blockIcon;
        
        if(meta == 11) meta = 2;
        
        if(meta == 1) return iconCentre;
        else if(meta == 2) {
        	if(side != 1 && side != 0) return iconTip;
        	else if(side == 1) return iconTop;
        	else return iconCentre;
        }
        else if ((meta < 2 || meta > 6) && side == 1) return iconCentre;
        else if (meta < 3 && side == 0) return iconCentre;
        
        else if(meta == 3 || meta == 4) {
        	if(side == 0 || side == 1) return iconSide;
        	else if(side == 2 || side == 3) return iconSide;
        	else if(side == 4 || side == 5) return iconCentre;
        }
        
        else if(meta == 5 || meta == 6) {
        	if(side == 0 || side == 1) return blockIcon;
        	else if(side == 2 || side == 3) return iconCentre;
        	else if(side == 4 || side == 5) return iconSide;
        }
        
        else if(meta >= 7) {
        	if(side == 1) return iconCentre;
        	else if(side == 0) {
        		if(meta <= 8) return iconSide;
        	}
        	else if(side == getOppositeSide(5 - (meta - 3) % 4)) return iconCentre;
        	else if(side == 5 - (meta - 3) % 4) return blockIcon;
        	else if(meta <= 8 && side % 2 == meta % 2) return iconR;
        	else if(meta >= 9 && (side + 1) % 2 == meta % 2) return iconR;
        	else return iconL;
        }

        return icon;
    }

    private int getOppositeSide(int i) {
		if(i % 2 == 1) return i - 1;
    	return i + 1;
	}

	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        if (!this.canBlockStay(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_))
        {
            p_149695_1_.func_147480_a(p_149695_2_, p_149695_3_, p_149695_4_, true);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
    {
    	int meta = p_149718_1_.getBlockMetadata(p_149718_2_, p_149718_3_, p_149718_4_);
    	
    	if(meta == 11) meta = 2;
        
        if (canBeSustainedByDirection(meta, 2) && canSustainDirection(p_149718_1_.getBlock(p_149718_2_ - 1, p_149718_3_, p_149718_4_), p_149718_1_.getBlockMetadata(p_149718_2_ - 1, p_149718_3_, p_149718_4_), 3))
        {
            return true;
        }
        else if (canBeSustainedByDirection(meta, 3) && canSustainDirection(p_149718_1_.getBlock(p_149718_2_ + 1, p_149718_3_, p_149718_4_), p_149718_1_.getBlockMetadata(p_149718_2_ + 1, p_149718_3_, p_149718_4_), 2))
        {
            return true;
        }
        else if (canBeSustainedByDirection(meta, 4) && canSustainDirection(p_149718_1_.getBlock(p_149718_2_, p_149718_3_, p_149718_4_ - 1), p_149718_1_.getBlockMetadata(p_149718_2_, p_149718_3_, p_149718_4_ - 1), 5))
        {
            return true;
        }
        else if (canBeSustainedByDirection(meta, 5) && canSustainDirection(p_149718_1_.getBlock(p_149718_2_, p_149718_3_, p_149718_4_ + 1), p_149718_1_.getBlockMetadata(p_149718_2_, p_149718_3_, p_149718_4_ + 1), 4))
        {
            return true;
        }
        else if (canBeSustainedByDirection(meta, 0) && canSustainDirection(p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_), p_149718_1_.getBlockMetadata(p_149718_2_, p_149718_3_ - 1, p_149718_4_), 1))
        {
            return true;
        }
    	
    	Block block = p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
        return block.canSustainPlant(p_149718_1_, p_149718_2_, p_149718_3_ - 1, p_149718_4_, ForgeDirection.UP, this);
    }
    
    public static boolean canBeSustainedByDirection(int meta, int direction) {
    	switch (direction) {
		case 0:
			return isInputInList(meta, 0, 1, 2);
		case 1:
			return isInputInList(meta);
		case 2:
			return isInputInList(meta, 3, 7);
		case 3:
			return isInputInList(meta, 4, 8);
		case 4:
			return isInputInList(meta, 5, 9);
		case 5:
			return isInputInList(meta, 6, 10);
		default:				
			break;
		}
    	return false;
	}

	public static boolean canSustainDirection(Block block, int meta, int direction) {
		if(block == saguaro) {
			switch (direction) {
			case 0:
				return isInputInList(meta);
			case 1:
				return isInputInList(meta, 0, 1, 7, 8, 9, 10);
			case 2:
				return isInputInList(meta, 1, 4);
			case 3:
				return isInputInList(meta, 1, 3);
			case 4:
				return isInputInList(meta, 1, 6);
			case 5:
				return isInputInList(meta, 1, 5);
			default:				
				break;
			}
		}
    	return false;
	}

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return EnhancedBiomesItems.saguaro;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return 0;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Desert;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return -1;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return p_149650_2_.nextInt(10) == 0 ? EnhancedBiomesItems.saguaro : Item.getItemFromBlock(Blocks.cactus);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return 0;
    }
}
