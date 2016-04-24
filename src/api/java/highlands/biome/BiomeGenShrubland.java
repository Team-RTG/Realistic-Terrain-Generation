package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenShrubland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);

	public BiomeGenShrubland(int par1)
    {
        super(par1);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
	    int trees = 2;
	    int grass = 12;
	    int flowers = 2;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
	    this.theBiomeDecorator.generateLakes = true;
	    this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		int i = rand.nextInt(9);
		switch(i){
		case 0: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueFlower);
		case 1: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.leafyFern);
		case 2: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.whiteFlower);
		case 3: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cattail);
		case 4: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.lavender);
		case 5: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.raspberryBush);
		case 6: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueberryBush);
		case 7: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.thornbush);
		case 8: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cotton);
		default: return (WorldGenerator)new WorldGenSmallPlants(Blocks.air);
		}
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(3) != 0 ? new WorldGenHighlandsShrub(0, 0) : new WorldGenTrees(false, 2 + par1Random.nextInt(3), 0, 0, false));
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
    }
}
