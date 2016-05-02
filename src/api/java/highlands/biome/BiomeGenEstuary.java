package highlands.biome;

import highlands.worldgen.WorldGenTreeMangrove;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenEstuary extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.28F, 0.1F);

	public BiomeGenEstuary(int par1){
			super(par1);	
			int trees = 10;
		    int grass = 0;
		    int flowers = 0;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
	        this.topBlock = Blocks.sand;
	        this.fillerBlock = Blocks.dirt;
	        this.setHeight(biomeHeight);
	        this.temperature = 0.8F;
	        this.rainfall = 0.9F;
	        
	    }

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	    {
	        return new WorldGenTallGrass(Blocks.tallgrass, 1);
	    }
	    
	    @Override
	    public WorldGenAbstractTree func_150567_a(Random par1Random)
	    {
	        return (WorldGenAbstractTree)new WorldGenTreeMangrove(4, 2, false);
	    }

	    @Override
		public void decorate(World world, Random random, int x, int z) {
			BiomeGenBaseHighlands biome = this;
	    	if(random.nextBoolean())this.theBiomeDecorator.treesPerChunk = 0;
	    	else this.theBiomeDecorator.treesPerChunk = 10;
	    	
	    	this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
	    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 0, 16);
	    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 15, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
	    }
	    
}











