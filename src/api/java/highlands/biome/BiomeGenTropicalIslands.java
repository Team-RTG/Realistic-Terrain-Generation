package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePalm;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTropicalIslands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.3F, 0.5F);

	public BiomeGenTropicalIslands(int par1)
    {
        super(par1);
	    int trees = 5;
	    int grass = 8;
	    int flowers = 4;
	    int plants = 4;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    
	    this.setHeight(biomeHeight);
        
        this.temperature = 1.0F;
        this.rainfall = 1.2F;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
	    return new WorldGenTallGrass(Blocks.tallgrass, 2);
	}
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)new WorldGenTreePalm(8, 3, false);
    }
    
    public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.leafyFern)
				: new WorldGenSmallPlants(HighlandsBlocks.whiteFlower));
	}
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.lapisGen, 0, 32);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLwater, 0, 64);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
        
        WorldGenVines var5 = new WorldGenVines();
        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = x + random.nextInt(16) + 8;
            byte var8 = 36;
            int var9 = z + random.nextInt(16) + 8;
            var5.generate(world, random, var7, var8, var9);
        }
    }

}
