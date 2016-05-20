package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePoplar;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenMeadow extends BiomeGenBaseHighlands
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);

	public BiomeGenMeadow(int par1)
    {
        super(par1);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
	    int trees = 0;
	    int grass = 15;
	    int flowers = 8;
	    int plants = 4;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.theBiomeDecorator.generateLakes = false;
        this.setHeight(biomeHeight);
        
        this.temperature = 0.6F;
        this.rainfall = 0.8F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cotton)
				: new WorldGenSmallPlants(HighlandsBlocks.lavender));
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
        return (WorldGenAbstractTree)new WorldGenTreePoplar(10, 4, false);
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 0, 16);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLwater, 0, 64);
    }

}
