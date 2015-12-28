package enhancedbiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enhancedbiomes.blocks.renderer.BlockGrassRenderer;

public class BlockGrassEB extends BlockGrass implements IGrowable
{
    private static final Logger logger = LogManager.getLogger();
    private static IIcon[] topIcon = new IIcon[EnhancedBiomesBlocks.dirtEB.soils.length];
    private static IIcon[] sideIcon = new IIcon[EnhancedBiomesBlocks.dirtEB.soils.length];
    private static IIcon[] sideSnowIcon = new IIcon[EnhancedBiomesBlocks.dirtEB.soils.length];
    private static IIcon[] sideOverlayIcon = new IIcon[EnhancedBiomesBlocks.dirtEB.soils.length];
    private static IIcon[] grassUnderlayIcon = new IIcon[EnhancedBiomesBlocks.dirtEB.soils.length];

    protected BlockGrassEB()
    {
        super();
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.topIcon[meta] : (side == 0 ? EnhancedBiomesBlocks.dirtEB.icons[meta] : this.sideIcon[meta]);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) < 4 && p_149674_1_.getBlockLightOpacity(p_149674_2_, p_149674_3_ + 1, p_149674_4_) > 2)
            {
                p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, EnhancedBiomesBlocks.dirtEB, p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_), 2);
            }
            else if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = p_149674_2_ + p_149674_5_.nextInt(3) - 1;
                    int j1 = p_149674_3_ + p_149674_5_.nextInt(5) - 3;
                    int k1 = p_149674_4_ + p_149674_5_.nextInt(3) - 1;
                    Block block = p_149674_1_.getBlock(i1, j1 + 1, k1);

                    if (p_149674_1_.getBlockLightValue(i1, j1 + 1, k1) >= 4 && p_149674_1_.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                    	if(p_149674_1_.getBlock(i1, j1, k1) == EnhancedBiomesBlocks.dirtEB) p_149674_1_.setBlock(i1, j1, k1, EnhancedBiomesBlocks.grassEB, p_149674_1_.getBlockMetadata(i1, j1, k1), 2);
                    	else if(p_149674_1_.getBlock(i1, j1, k1) == Blocks.dirt && p_149674_1_.getBlockMetadata(i1, j1, k1) == 0) p_149674_1_.setBlock(i1, j1, k1, Blocks.grass, 0, 2);
                    }
                }
            }
        }
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return EnhancedBiomesBlocks.dirtEB.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
    {
        int meta = p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_);
        
        if (p_149673_5_ == 1)
        {
            return meta < this.topIcon.length ? this.topIcon[meta] : this.topIcon[0];
        }
        else if (p_149673_5_ == 0)
        {
            return EnhancedBiomesBlocks.dirtEB.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_);
        }
        else
        {
            Material material = p_149673_1_.getBlock(p_149673_2_, p_149673_3_ + 1, p_149673_4_).getMaterial();
            return material != Material.snow && material != Material.craftedSnow ? (meta < this.sideIcon.length ? this.sideIcon[meta] : this.sideIcon[0]) : meta < this.sideSnowIcon.length ? this.sideSnowIcon[meta] : this.sideSnowIcon[0];
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        for(int a = 0; a < EnhancedBiomesBlocks.dirtEB.soils.length; a++)
        {
        	String soil = EnhancedBiomesBlocks.dirtEB.soils[a];
        	if(soil != "entisol") {
            	this.sideIcon[a] = p_149651_1_.registerIcon("enhancedbiomes:grass_" + soil);
                this.topIcon[a] = p_149651_1_.registerIcon("grass_top");
                this.sideSnowIcon[a] = p_149651_1_.registerIcon("enhancedbiomes:snow_" + soil);
                this.sideOverlayIcon[a] = p_149651_1_.registerIcon("grass_side_overlay");
                this.grassUnderlayIcon[a] = p_149651_1_.registerIcon("enhancedbiomes:grass_" + soil + "_underlay");
        	}
        }
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.getGrassColor(d0, d1);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return this.getBlockColor();
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
    	int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1)
        {
            for (int l1 = -1; l1 <= 1; ++l1)
            {
                int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeGrassColor(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }

    @SideOnly(Side.CLIENT)
    public static IIcon getIconSideOverlay(int meta)
    {
    	return meta < sideOverlayIcon.length ? sideOverlayIcon[meta] : sideOverlayIcon[0];
    }

    @SideOnly(Side.CLIENT)
    public static IIcon getGrassUnderlayIcon(int meta)
    {
        return meta < grassUnderlayIcon.length ? grassUnderlayIcon[meta] : grassUnderlayIcon[0];
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        int l = 0;

        while (l < 128)
        {
            int i1 = p_149853_3_;
            int j1 = p_149853_4_ + 1;
            int k1 = p_149853_5_;
            int l1 = 0;

            while (true)
            {
                if (l1 < l / 16)
                {
                    i1 += p_149853_2_.nextInt(3) - 1;
                    j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
                    k1 += p_149853_2_.nextInt(3) - 1;

                    if ((p_149853_1_.getBlock(i1, j1 - 1, k1) == Blocks.grass || p_149853_1_.getBlock(i1, j1 - 1, k1) == EnhancedBiomesBlocks.grassEB) && !p_149853_1_.getBlock(i1, j1, k1).isNormalCube())
                    {
                        ++l1;
                        continue;
                    }
                }
                else if (p_149853_1_.getBlock(i1, j1, k1).getMaterial() == Material.air)
                {
                    if (p_149853_2_.nextInt(8) != 0)
                    {
                        if (Blocks.tallgrass.canBlockStay(p_149853_1_, i1, j1, k1))
                        {
                            p_149853_1_.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
                        }
                    }
                    else
                    {
                        p_149853_1_.getBiomeGenForCoords(i1, k1).plantFlower(p_149853_1_, p_149853_2_, i1, j1, k1);
                    }
                }

                ++l;
                break;
            }
        }
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

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
    	for(int a = 0; a < EnhancedBiomesBlocks.dirtEB.soils.length; a++)
        {
    		if(a != 2) p_149666_3_.add(new ItemStack(this, 1, a));
        }
    }

	@Override
	public int getRenderType() {
		return BlockGrassRenderer.renderID;
	}

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int meta)
    {
        return meta;
    }
}