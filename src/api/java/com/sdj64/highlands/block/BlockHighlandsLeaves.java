package com.sdj64.highlands.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Predicate;

public class BlockHighlandsLeaves extends BlockLeaves
{
	private HighlandsBlocks.EnumTypeTree treeType;

    public BlockHighlandsLeaves(HighlandsBlocks.EnumTypeTree type, String name)
    {
    	treeType = type;
    	setUnlocalizedName(name + "_leaves");
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
        this.setCreativeTab(HighlandsBlocks.tabHighlands);
        
        
        
        this.setGraphics();
    }
    
    public void setGraphics(){
    	if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
    		this.setGraphicsLevel(Minecraft.getMinecraft().isFancyGraphicsEnabled());
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return this.treeType==HighlandsBlocks.EnumTypeTree.ASPEN ? 0xFFFFFF: ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return this.treeType==HighlandsBlocks.EnumTypeTree.ASPEN ? 0xFFFFFF: ColorizerFoliage.getFoliageColorBasic();
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return this.treeType==HighlandsBlocks.EnumTypeTree.ASPEN ? 0xFFFFFF: BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
    }
    
    public HighlandsBlocks.EnumTypeTree getTreeType()
    {
    	return treeType;
    }

    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
    	//does nothing, since Highlands trees don't drop apples.
    }

    protected int getSaplingDropChance(IBlockState state)
    {
        return treeType.equals(HighlandsBlocks.EnumTypeTree.REDWOOD) ? 80 : 
        	treeType.equals(HighlandsBlocks.EnumTypeTree.BAMBOO) ? 5 : 
        		super.getSaplingDropChance(state);
    }
    
    /**
     * Copied from BlockLeaves, without the part about dropping apples.
     */
    public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
    	java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int chance = this.getSaplingDropChance(state);

        if (fortune > 0)
        {
            chance -= 2 << fortune;
            if (chance < 10) chance = 10;
        }

        if (rand.nextInt(chance) == 0)
            ret.add(new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state)));

        chance = 200;
        if (fortune > 0)
        {
            chance -= 10 << fortune;
            if (chance < 40) chance = 40;
        }

        this.captureDrops(true);
        ret.addAll(this.captureDrops(false));
        return ret;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(HighlandsBlocks.saplings[treeType.getMetadata()]);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(itemIn, 1, 0));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
    	boolean dec = meta < 4;
    	boolean check = meta < 8;
        return this.getDefaultState().withProperty(DECAYABLE, dec).withProperty(CHECK_DECAY, check);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i = 4;
        }

        if (!((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i = 8;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
        if (!worldIn.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears)
        {
            player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
            spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, 0));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        IBlockState state = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1, 0)));
    }

	@Override
	public EnumType getWoodType(int meta) {
		// returns Birch since it doesn't drop any apples. Probably safe, and safer than null.
		return BlockPlanks.EnumType.BIRCH;
	}
	
	
	
}