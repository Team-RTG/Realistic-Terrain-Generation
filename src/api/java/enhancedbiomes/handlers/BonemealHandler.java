package enhancedbiomes.handlers;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.blocks.BlockGrassEB;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BonemealHandler
{
	/** Used to make the sapling grow the tree **/
	@SubscribeEvent
	public void bonemealUsed(BonemealEvent event)
	{
		if(event.block == Blocks.grass) {
			if (!event.world.isRemote)
            {
				int l = 0;

		        while (l < 128)
		        {
		            int i1 = event.x;
		            int j1 = event.y + 1;
		            int k1 = event.z;
		            int l1 = 0;

		            while (true)
		            {
		                if (l1 < l / 16)
		                {
		                    i1 += event.world.rand.nextInt(3) - 1;
		                    j1 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
		                    k1 += event.world.rand.nextInt(3) - 1;

		                    if ((event.world.getBlock(i1, j1 - 1, k1) == Blocks.grass || event.world.getBlock(i1, j1 - 1, k1) == EnhancedBiomesBlocks.grassEB) && !event.world.getBlock(i1, j1, k1).isNormalCube())
		                    {
		                        ++l1;
		                        continue;
		                    }
		                }
		                else if (event.world.getBlock(i1, j1, k1).getMaterial() == Material.air)
		                {
		                    if (event.world.rand.nextInt(8) != 0)
		                    {
		                        if (Blocks.tallgrass.canBlockStay(event.world, i1, j1, k1))
		                        {
		                            event.world.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
		                        }
		                    }
		                    else
		                    {
		                        event.world.getBiomeGenForCoords(i1, k1).plantFlower(event.world, event.world.rand, i1, j1, k1);
		                    }
		                }

		                ++l;
		                break;
		            }
		        }

                event.setResult(Result.ALLOW);
            }
		}
	}
}