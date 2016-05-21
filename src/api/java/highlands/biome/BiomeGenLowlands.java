package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeFir;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLowlands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.2F, 0.5F);

	public BiomeGenLowlands(int par1)
    {
        super(par1);
        
        int trees = 2;
	    int grass = 4;
	    int flowers = 0;
	    int plants = 4;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);

        this.setHeight(biomeHeight);
        this.temperature = 0.5F;
        this.rainfall = 1.2F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cattail)
				: (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cotton)
				: new WorldGenSmallPlants(HighlandsBlocks.blueFlower)));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(8) == 0 ?
        		new WorldGenHighlandsShrub(0, 0) : par1Random.nextInt(4) != 0 ?
        		new WorldGenTrees(false, 3 + par1Random.nextInt(3), 0, 0, false) : new WorldGenTreeFir(10, 5, false, false));
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
