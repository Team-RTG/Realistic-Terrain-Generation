package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
//import highlands.worldgen.WorldGenTreeAcacia;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSahel extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.25F, 0.4F);

	public BiomeGenSahel(int par1){
		super(par1);
		
		int trees = 1;
	    int grass = 5;
	    int flowers = 0;
	    int plants = 1;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        
        this.spawnableCreatureList.clear();
        this.setHeight(biomeHeight);
        this.temperature = 1.6F;
        this.rainfall = 0.1F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.whiteFlower)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush));
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
        return (WorldGenAbstractTree)(par1Random.nextInt(6) != 0 ?
        		new WorldGenSavannaTree(false) : new WorldGenHighlandsShrub(0, 0));
        		//new WorldGenTreeAcacia(7, 3, false) : new WorldGenHighlandsShrub(0, 0));
    }

    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlandsNoCheck(world, random, x, z, 2, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLdirt, 62, 80);
    	this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
    }
	    
}
