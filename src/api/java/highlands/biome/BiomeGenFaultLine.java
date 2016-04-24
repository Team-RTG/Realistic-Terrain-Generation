package highlands.biome;

import highlands.worldgen.MapGenCracks;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenFaultLine extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.2F);
	
	private static MapGenCracks mapCracks = new MapGenCracks();

	public BiomeGenFaultLine(int par1){
			super(par1);
			
			this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
			
			int trees = 0;
		    int grass = 18;
		    int flowers = 0;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	        
	        this.topBlock = Blocks.dirt;
	        this.fillerBlock = Blocks.dirt;
	        this.setHeight(biomeHeight);
	        this.temperature = 1.2F;
	        this.rainfall = 0.1F;
	        
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
	        return null;
	    }

	    @Override
		public void decorate(World world, Random random, int x, int z) {
			BiomeGenBaseHighlands biome = this;
			this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
	        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
	        
	        //mapCracks.func_151539_a(world.getChunkProvider(), world, x, z, world);
	    }
}
