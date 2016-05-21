package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHighlands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.6F, 0.5F);
	
	public BiomeGenHighlands(int par1)
	    {
	        super(par1);
	        
	        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	        
		    int trees = 3;
		    int grass = 12;
		    int flowers = 0;
		    int plants = 4;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
		    this.theBiomeDecorator.generateLakes = true;
		    this.setHeight(biomeHeight);
	        this.temperature = 0.6F;
	        this.rainfall = 0.2F;
	        
	    }
	
	@Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random random){
		return (WorldGenerator)(random.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.thornbush)
				: (random.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.lavender)));
	}

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
    	return (WorldGenAbstractTree)(random.nextInt(3) != 0 ? new WorldGenHighlandsShrub(0, 0) : new WorldGenTrees(false, 2 + random.nextInt(3), 0, 0, false));
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
	    int var5 = 3 + random.nextInt(6);

	        for (int var6 = 0; var6 < var5; ++var6)
	        {
	            int var7 = x + random.nextInt(16);
	            int var8 = random.nextInt(28) + 4;
	            int var9 = z + random.nextInt(16);
	            Block var10 = world.getBlock(var7, var8, var9);

	            if (var10 == Blocks.stone)
	            {
	            	world.setBlock(var7, var8, var9, Blocks.emerald_ore, 0, 2);
	            }
	        }
	        
	        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.coalGen, 0, 128);
	    }
}
