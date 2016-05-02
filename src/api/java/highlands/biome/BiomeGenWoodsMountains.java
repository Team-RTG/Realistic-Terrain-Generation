package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenWoodsMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.8F, 1.0F); // was 10.0F

	public BiomeGenWoodsMountains(int par1)
    {
        super(par1);
	    int trees = 10;
	    int grass = 5;
	    int flowers = 0;
	    int plants = 1;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush));
	}

	/**
    * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	int a = par1Random.nextInt(12);
    	switch(a){
    	case 1: return (WorldGenAbstractTree)this.worldGeneratorTrees;
    	case 2: return (WorldGenAbstractTree)this.worldGeneratorTrees;
    	case 3: return (WorldGenAbstractTree)this.worldGeneratorBigTree;
    	case 4: return (WorldGenAbstractTree)new WorldGenHighlandsBigTree(false, true, 2, 2, 1, 0);
    	case 5: return (WorldGenAbstractTree)new WorldGenHighlandsBigTree(false, true, 0, 0, 2, 16); // bad ??? 20 height
    	default: return (WorldGenAbstractTree)this.worldGeneratorTrees;
    	}
        //return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
	/*
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
    }
    */
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 0, 16);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLwater, 0, 128);
    }
}
