package highlands.biome;

import highlands.Highlands;
import highlands.worldgen.WorldGenHighlandsShrub;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenSnowMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(2.0F, 1.0F);

	public BiomeGenSnowMountains(int par1)
    {
        super(par1);
        
        int trees = -999;
	    int grass = 2;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.spawnableCreatureList.clear();
        
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.snow;
        this.setHeight(biomeHeight);
        this.temperature = 0.0F;
        this.rainfall = 0.5F;
        
        this.setEnableSnow();
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(new WorldGenHighlandsShrub(1, 1));
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		// WorldType.getTranslateName() doesn't work in bukkit ?
    	//if(random.nextInt(3) == 0 && !world.provider.terrainType.getTranslateName().equals("ATG - Alternate"))
    	//	new WorldGenMountain(15, 25, false, 0).generate(world, random, x+random.nextInt(16), 128, z+random.nextInt(16));
    	
    	this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLice, 0, 128);
        
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 64, 128);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 8, this.theBiomeDecorator.redstoneGen, 16, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.lapisGen, 32, 64);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 32, 64);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 16, 32);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.diamondGen, 0, 32);
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(Highlands.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
}
