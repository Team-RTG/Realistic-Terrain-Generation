package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;

public class BiomeGenOcean2 extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-1.5F, 0.4F);
	
    public BiomeGenOcean2(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        
        int trees = 0;
	    int grass = 3;
	    int flowers = 1;
        this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.setHeight(biomeHeight);
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
    	/*
    	// makes islands grass
    	for(int i = -16; i < 32; i++){
    		for(int j = -16; j < 32; j++){
    			if(world.getBiomeGenForCoords(par3+i, par4+j) == HighlandsMain.ocean2){
	    			int topY = world.getTopSolidOrLiquidBlock(par3+i, par4+j);//80;
	    			/*
	    			int var11;
	    	        for (boolean var6 = false; ((var11 = world.getBlockId(par3+i, topY, par4+j)) == 0 || var11 == Block.leaves.blockID) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    	        * /
	    			//System.out.println("the top block is id" + world.getBlockId(par3+i, topY, par4+j));
	    			if(world.getBlockId(par3+i, topY, par4+j) == 0)topY--;
	    			if(topY > 62){
	    				world.setBlock(par3+i, topY, par4+j, Block.grass.blockID);
	    			}
	    		}
    		}
    	}
    	*/

		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLwater, 0, 64);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
    }
}
