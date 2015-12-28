package enhancedbiomes.blocks.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTide extends TileEntity 
{
	int c = 0;
	
	//TODO Tides
	/*public void updateEntity() 
	{
		c++; c %= 20;
		if(c == 0 || worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.air)
		{
			this.worldObj.getBlock(xCoord, yCoord, zCoord).updateTick(worldObj, xCoord, yCoord, zCoord, worldObj.rand);
		}
	}*/
}
