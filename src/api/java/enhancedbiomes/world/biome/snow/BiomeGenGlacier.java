package enhancedbiomes.world.biome.snow;

import java.util.List;
import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import enhancedbiomes.world.gen.WorldGenLakesEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.*;

public class BiomeGenGlacier extends BiomeGenSnowBase
{
    public BiomeGenGlacier(int par1)
    {
        super(par1);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        //spawnableWaterCreatureList.clear();
        //field_82914_M.clear();
        this.topBlock = Blocks.ice;
        this.fillerBlock = Blocks.snow;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        
        for(int c = 100; c > 0; c--)
    	{
    		int posX = par3 + par2Random.nextInt(16);
    		int posY = par2Random.nextInt(120);
        	int posZ = par4 + par2Random.nextInt(16);
        	if(par1World.getBlock(posX, posY, posZ) == Blocks.air && par1World.getBlock(posX, posY - 1, posZ) == Blocks.ice)
        	{
        		(new WorldGenLakesEnhancedBiomes(Blocks.flowing_water, Blocks.snow)).generate(par1World, par1World.rand, posX, posY, posZ);
        	}
    	}
    }
    
    /**
	 * takes temperature, returns color
	 */
	/*@Override
	public int getSkyColorByTemp(float par1)
	{
		return 0xBBBBFF;
	}*/
}