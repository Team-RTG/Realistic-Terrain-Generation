package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDeadBush2 extends WorldGenerator
{
    /** stores the Block for WorldGenDeadBush */
    private Block deadBush;

    public WorldGenDeadBush2(Block par1)
    {
        this.deadBush = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var11;

        Block block = null;
        do 
        {
            block = par1World.getBlock(par3,  par4, par5);
            if (block != null && !block.isLeaves(par1World, par3, par4, par5))
            {
                break;
            }
            par4--;
        } while (par4 > 0);

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var8, var9, var10) && this.deadBush.canBlockStay(par1World, var8, var9, var10))
            {
                par1World.setBlock(var8, var9, var10, this.deadBush, 0, 2);
            }
        }

        return true;
    }
}
