package highlands.biome;

import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeFir;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTallPineForest extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.7F);

	public BiomeGenTallPineForest(int par1)
	    {
	        super(par1);
	        
	        int trees = 4;
		    int grass = 2;
		    int flowers = 0;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
		    this.setHeight(biomeHeight);
	        this.temperature = 0.1F;
	        this.rainfall = 0.8F;
	        
	    }

	/**
	* Gets a WorldGen appropriate for this biome.
	*
	*/
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	return (WorldGenAbstractTree)(par1Random.nextInt(8) == 0 ?
    			new WorldGenTaiga1() : par1Random.nextInt(3) == 0 ?
    			new WorldGenHighlandsShrub(0, 0) : 
    			par1Random.nextInt(4) == 0? new WorldGenTreeFir(15, 10, false, true) : new WorldGenTreeFir(15, 10, false, false));
	}

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	    {
	        return new WorldGenTallGrass(Blocks.tallgrass, 1);
	    }

	@Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.coalGen, 0, 128);
	}
}
