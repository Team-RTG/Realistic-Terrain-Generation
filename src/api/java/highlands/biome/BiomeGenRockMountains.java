package highlands.biome;

import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenMountain;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenRockMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.8F, 0.5F);

	public BiomeGenRockMountains(int par1)
    {
        super(par1);
        
        int trees = -999;
	    int grass = 2;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.spawnableCreatureList.clear();
        
        this.fillerBlock = Blocks.stone;
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        
        //this.setEnableSnow();
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(new WorldGenHighlandsShrub(1, 1));
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
    	if(random.nextInt(2) == 0)
    		new WorldGenMountain(15, 15, false, 1).generate(world, random, x+random.nextInt(16), 128, z+random.nextInt(16));
    	
    	this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
        
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 64, 128);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 40, this.theBiomeDecorator.ironGen, 0, 128);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 8, this.theBiomeDecorator.redstoneGen, 16, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.lapisGen, 32, 64);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 32, 64);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 16, 32);
    }
}
