package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHighlandsGroundcover extends WorldGenerator
{
    /** Stores Block for WorldGenTallGrass */
    private BlockTallGrass tallGrass;
    private int tallGrassMetadata;
    
    //Type 1 is Cliff (tall grass + cobble boulders), Type 2 is Outback (tall grass + grass blocks underneath on sand)
    private int type;

    public WorldGenHighlandsGroundcover(BlockTallGrass tallgrass, int par2, int type)
    {
        this.tallGrass = tallgrass;
        this.tallGrassMetadata = par2;
        this.type = type;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {

        for (boolean var6 = false; (par1World.getBlock(par3, par4, par5) == Blocks.leaves || par1World.isAirBlock(par3, par4, par5) ) && par4 > 0; --par4)
        {
            ;
        }

        for (int var7 = 0; var7 < 128; ++var7)
        {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if(type == 1){
	            if (par1World.isAirBlock(var8, var9, var10) && this.tallGrass.canBlockStay(par1World, var8, var9, var10))
	            {
	            	if(par2Random.nextInt(2) == 0){
	            		par1World.setBlock(var8, var9, var10, this.tallGrass, this.tallGrassMetadata, 2);
	            	}
	            	else{
	            		par1World.setBlock(var8, var9, var10, (par2Random.nextInt(8) == 0 ? Blocks.mossy_cobblestone : Blocks.cobblestone), 0, 2);
	            	}
	            }
            }
            if(type == 2){
            	if (par1World.getBlock(var8, var9-1, var10) == Blocks.sand){
            		par1World.setBlock(var8, var9-1, var10, Blocks.grass, 0, 2);
            		if (par1World.isAirBlock(var8, var9, var10)){
                		par1World.setBlock(var8, var9, var10, this.tallGrass, this.tallGrassMetadata, 2);
                	}
            	}
            }
        }

        return true;
    }
}
