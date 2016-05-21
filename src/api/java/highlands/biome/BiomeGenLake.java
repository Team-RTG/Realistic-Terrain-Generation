package highlands.biome;

import highlands.worldgen.WorldGenTreePoplar;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenLake extends BiomeGenBaseHighlands
{
    private static final Height biomeHeight = new Height(-0.7F, 0.2F);

	public BiomeGenLake(int par1)
    {
		super(par1);
		
    	int trees = 3;
	    int grass = 12;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
    	this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 0.8F;
	    
        this.spawnableCreatureList.clear();
    }
	
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
		return (WorldGenAbstractTree)new WorldGenTreePoplar(10, 4, false);
    }
}
