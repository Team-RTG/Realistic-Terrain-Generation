package highlands.biome;

import highlands.worldgen.WorldGenTreePalm;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDesertIsland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.0F, 0.2F);

	public BiomeGenDesertIsland(int par1){
			super(par1);
			int trees = 1;
		    int grass = 0;
		    int flowers = 0;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
	        this.topBlock = Blocks.sand;
	        this.fillerBlock = Blocks.sand;
	        this.setHeight(biomeHeight);
	        this.temperature = 0.8F;
	        this.rainfall = 0.4F;
	        
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
	        return (WorldGenAbstractTree)new WorldGenTreePalm(8, 3, false);
	    }

	    @Override
		public void decorate(World world, Random random, int x, int z) {
			BiomeGenBaseHighlands biome = this;
			this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
			((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
	    }
	    
}
