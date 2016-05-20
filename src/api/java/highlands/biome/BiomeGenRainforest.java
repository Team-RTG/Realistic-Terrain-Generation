package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeAsh;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRainforest extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.4F);

	public BiomeGenRainforest(int par1)
    {
        super(par1);
	    int trees = 12;
	    int grass = 10;
	    int flowers = 0;
	    int plants = 2;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
	    this.theBiomeDecorator.generateLakes = true;
	    this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 1.0F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.leafyFern)
				: new WorldGenSmallPlants(HighlandsBlocks.lavender));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	if(par1Random.nextInt(3) == 0)
        return (WorldGenAbstractTree)new WorldGenTreeAsh(16, 8, false);
    	else if(par1Random.nextInt(2) == 0)
    	return (WorldGenAbstractTree)new WorldGenTrees(false, 5 + par1Random.nextInt(4), 0, 0, false);
    	else return new WorldGenHighlandsShrub(0, 0);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(Blocks.tallgrass, 2);
    }

    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 0, 16);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
    }
}
