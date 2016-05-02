package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCliffs extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.0F, 0.5F);

	public BiomeGenCliffs(int par1)
    {
    	super(par1);
	 	int trees = 2;
	    int grass = 4;
	    int flowers = 0;
	    int plants = 1;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
        this.setHeight(biomeHeight);
        this.temperature = 0.4F;
        this.rainfall = 0.4F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.thornbush)
				: new WorldGenSmallPlants(HighlandsBlocks.blueberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(12) == 0 ? new WorldGenTaiga2(false) : new WorldGenHighlandsShrub(1, 1));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenHighlandsGroundcover(Blocks.tallgrass, 1, 1);
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
        
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 64, 128);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 8, this.theBiomeDecorator.redstoneGen, 16, 32);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.lapisGen, 32, 64);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 32, 64);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 16, 32);
    }
}






