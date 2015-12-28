package enhancedbiomes.blocks;

import java.util.Random;

import org.lwjgl.Sys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.tileentity.TileEntityTide;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import static enhancedbiomes.EnhancedBiomesMod.getTideHeight;

public class BlockTidalLiquid extends BlockLiquid implements ITileEntityProvider
{

	public BlockTidalLiquid(Material p_i45413_1_) 
	{
		super(p_i45413_1_);
		this.setTickRandomly(true);
	}

    /**
     * Ticks the block if it's been scheduled
     */
    //TODO Tides
	/*public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	long tod = MinecraftServer.getServer().worldServers[0].getWorldTime();
    	getTideHeight(tod);
    	if(y > 65) 
    	{	
    		world.setBlock(x, y, z, Blocks.flowing_water, 0, 3);
    		return;
    	}
    	else if(y == (int) getTideHeight(tod))
    	{
    		if(y == getTideHeight(tod)) world.setBlockToAir(x, y, z);
    		else world.setBlockMetadataWithNotify(x, y, z, (int) (9 - (8 * (getTideHeight(tod) % y))), 3);
    	}
    	else if (y + 1 <= getTideHeight(tod) && world.getBlock(x, y + 1, z) == Blocks.air) 
    	{
    		this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y + 1, z), 0);
    		world.setBlock(x, y + 1, z, EnhancedBiomesBlocks.waterTidal, 8, 3);
		}
    	else
    	{
    		world.setBlockMetadataWithNotify(x, y, z, 1, 3);
    	}
    	
    	if(world.getBlock(x, y + 1, z) == EnhancedBiomesBlocks.waterTidal) world.setBlockMetadataWithNotify(x, y, z, 0, 3);
    }*/

    private boolean canWaterOverride(World p_149807_1_, int p_149807_2_, int p_149807_3_, int p_149807_4_)
    {
        Block block = p_149807_1_.getBlock(p_149807_2_, p_149807_3_, p_149807_4_);
        return block != Blocks.wooden_door && block != Blocks.iron_door && block != Blocks.standing_sign && block != Blocks.ladder && block != Blocks.reeds ? (block.getMaterial() == Material.portal ? true : block.getMaterial().blocksMovement()) : true;
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new TileEntityTide();
	}
}
