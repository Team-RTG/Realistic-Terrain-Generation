package highlands.biome;

import highlands.api.HighlandsBiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BiomeGenVolcanoIsland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.7F, 1.7F);

	public BiomeGenVolcanoIsland(int par1)
    {
        super(par1);
        
        int trees = -100;
	    int grass = 0;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
        this.spawnableCreatureList.clear();
        
        this.topBlock = Blocks.gravel;
        this.fillerBlock = Blocks.gravel;
        this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 0.4F;
    }
    
	@Override
	public void decorate(World world, Random random, int x, int z) {
		this.theBiomeDecorator.decorateChunk(world, random, this, x, z);
    	
    	for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
    			if(world.getBiomeGenForCoords(x+i, z+j) == HighlandsBiomes.volcanoIsland){
	    			int topY = 128;
	    			Block var11;
	    	        for (boolean var6 = false; (world.isAirBlock(x+i, topY, z+j) || world.getBlock(x+i, topY, z+j) == Blocks.leaves) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    	        if(topY > 65){
		    			if(world.isAirBlock(x+i, topY, z+j))topY--;
		    			
		    			//TODO: huge problem with lava lake generation
		    			// constantly generates chunks
		    			/**
		    			//chance to generate a lava lake on top of the volcano
		    			if(world.getBiomeGenForCoords(x+i + 8, z+j) == HighlandsBiomes.volcanoIsland &&
		    					world.getBiomeGenForCoords(x+i - 8, z+j) == HighlandsBiomes.volcanoIsland &&
		    					world.getBiomeGenForCoords(x+i, z+j + 8) == HighlandsBiomes.volcanoIsland &&
		    					world.getBiomeGenForCoords(x+i, z+j - 8) == HighlandsBiomes.volcanoIsland &&
		    					random.nextInt(10) == 0){
		    				new WorldGenLakes(Blocks.lava).generate(world, random, x+i, topY, z+j);
		    			}
		    			*/
		    			
		    			int a = random.nextInt(10);
		    			if(a == 9 && random.nextInt(5) == 0){
		    				world.setBlock(x+i, topY, z+j, Blocks.flowing_lava, 0, 3);
		    				world.setBlock(x+i, topY+1, z+j, Blocks.air, 0, 3);
		    			}
		    			else if(a == 7 || a == 8) world.setBlock(x+i, topY, z+j, Blocks.cobblestone, 0, 2);
		    			else if(a == 6) world.setBlock(x+i, topY, z+j, Blocks.obsidian, 0, 2);
		    			else if(a == 5 || a == 4) world.setBlock(x+i, topY, z+j, Blocks.stone, 0, 2);
	    	        }
	    		}
    		}
    	}
    	
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 0, 64);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 12, this.theBiomeDecorator.redstoneGen, 0, 16);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.lapisGen, 0, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 4, this.theBiomeDecorator.goldGen, 0, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.diamondGen, 0, 16);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 12, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLlava, 0, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 25, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLobsidian, 0, 64);
    }
}
