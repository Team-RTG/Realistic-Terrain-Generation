package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenBaldHill extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.0F, 0.5F);
	
	public BiomeGenBaldHill(int par1){
		super(par1);
		
		int trees = 0;
	    int grass = 4;
	    int flowers = 1;
	    int plants = 2;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 0.5F;
        this.rainfall = 0.7F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.whiteFlower)
				: (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.cotton)));
	}

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
		return (WorldGenAbstractTree)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
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
        
        ((BiomeDecoratorHighlands) this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 0, 64);
        
    }
	    
}

