package highlands.biome;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenAutumnBigTree;
import highlands.worldgen.WorldGenAutumnTree;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAutumnForest extends BiomeGenBaseHighlands
{
	public BiomeGenAutumnForest(int par1)
    {
        super(par1);
        
	    int trees = 10;
	    int grass = 2;
	    int flowers = 8;
	    int plants = 2;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    
	    this.theBiomeDecorator.generateLakes = true;
	    
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.blueberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	int a = par1Random.nextInt(6)+1;
    	switch(a){
    	case 1: return (WorldGenAbstractTree)this.worldGeneratorTrees; //birch tree
    	
    	case 2: if(Highlands.vanillaBlocksFlag) return this.worldGeneratorTrees;//birch tree (vanillaBlocks only)
    	
    			else return (WorldGenAbstractTree)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves);// autumn tree yellow
    	
    	case 3: if(Highlands.vanillaBlocksFlag) return (WorldGenAbstractTree)(par1Random.nextInt(2) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));//pine tree(vanillaBlocks only)
				
    			else return (par1Random.nextBoolean() ? (WorldGenAbstractTree)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves) :
					(WorldGenAbstractTree)new WorldGenAutumnBigTree(false, Blocks.log, HighlandsBlocks.autumnYellowLeaves));// autumn tree yellow (chance of BigTree)
    	
    	case 4: if(Highlands.vanillaBlocksFlag) return this.worldGeneratorTrees;//regular trees (vanillaBlocks only)
    	
				else return (WorldGenAbstractTree)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves);// autumn tree orange
    	
    	case 5: if(Highlands.vanillaBlocksFlag) return this.worldGeneratorBigTree;//big trees (vanillaBlocks only)
    	
				else return (par1Random.nextBoolean() ? (WorldGenAbstractTree)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves) :
					(WorldGenAbstractTree)new WorldGenAutumnBigTree(false, Blocks.log, HighlandsBlocks.autumnOrangeLeaves));// autumn tree orange (chance of BigTree)
    	
    	case 6: return (WorldGenAbstractTree)(par1Random.nextInt(2) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false)); //pine tree
    	}
    	return this.worldGeneratorTrees;
    	
        //return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
    }
    
    /*
    @SideOnly(Side.CLIENT)
    
    public int getBiomeGrassColor()
    {
    	if(!HighlandsMain.vanillaBlocksFlag)
        return 0xA7B539;
    	else return super.getBiomeGrassColor();
    }
    
    /*
    @Override
    public BiomeGenBase setColor(int par1)
    {
        this.color = 0xFFA811;
        return this;
    }
    */
    
}
