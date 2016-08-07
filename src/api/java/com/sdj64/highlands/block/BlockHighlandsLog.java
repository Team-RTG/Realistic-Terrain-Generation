package com.sdj64.highlands.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockHighlandsLog extends BlockLog
{

	private HighlandsBlocks.EnumTypeTree treeType;
	
    public BlockHighlandsLog(HighlandsBlocks.EnumTypeTree type, String treeName)
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        setHardness(2.0F);
    	setResistance(0.5F);
    	setStepSound(Block.soundTypeWood);
        setUnlocalizedName(treeName + "_log");
        
        this.setCreativeTab(HighlandsBlocks.tabHighlands);
        
        treeType = type;
        
        if(type.equals(HighlandsBlocks.EnumTypeTree.BAMBOO)){
            this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
            this.setLightOpacity(1);
            setHardness(1.0F);
        }
        
        
    }
    
    public boolean isOpaqueCube()
    {
    	if(treeType == null)return true;
        return !treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO);
    }

    public boolean isFullCube()
    {
    	if(treeType == null)return true;
        return !treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
    	int i = 0;
    	
        switch (BlockHighlandsLog.SwitchEnumAxis.AXIS_LOOKUP[((BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal()])
        {
            case 1:
                i = 4;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                i = 12;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {LOG_AXIS});
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    static final class SwitchEnumAxis
        {
            static final int[] AXIS_LOOKUP = new int[BlockLog.EnumAxis.values().length];

            static
            {
                try
                {
                    AXIS_LOOKUP[BlockLog.EnumAxis.X.ordinal()] = 1;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    AXIS_LOOKUP[BlockLog.EnumAxis.Z.ordinal()] = 2;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    AXIS_LOOKUP[BlockLog.EnumAxis.NONE.ordinal()] = 3;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}