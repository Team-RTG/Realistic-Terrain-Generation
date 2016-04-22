package highlands.block;

import highlands.Logs;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

public class BlockCocoaPlant2 extends BlockCocoa
{
    public BlockCocoaPlant2()
    {
        super();
        this.setBlockTextureName("cocoa");
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int l = getDirection(par1World.getBlockMetadata(par2, par3, par4));
        par2 += Direction.offsetX[l];
        par4 += Direction.offsetZ[l];
        Block log = par1World.getBlock(par2, par3, par4);
        Logs.log(Level.INFO, "BlockCocoaPlant2 attached to "+log);
        //TODO-                              limitToValidMetadata
        return (log == Blocks.log && BlockLog.func_150165_c(par1World.getBlockMetadata(par2, par3, par4)) == 3)
        		|| (log == HighlandsBlocks.palmWood);
    }
}
