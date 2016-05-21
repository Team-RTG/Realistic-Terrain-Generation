package highlands.biome;

import highlands.Highlands;
import highlands.worldgen.WorldGenTreePalm;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenDunes extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.2F, 1.0F);

	public BiomeGenDunes(int par1){
			super(par1);
			int trees = -999;
		    int grass = -999;
		    int flowers = -999;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
	        this.topBlock = Blocks.sand;
	        this.fillerBlock = Blocks.sand;
	        this.spawnableCreatureList.clear();
	        this.setHeight(biomeHeight);
	        this.temperature = 2.0F;
	        this.rainfall = 0.0F;
	        
	        this.setDisableRain();
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
	        return (WorldGenAbstractTree)new WorldGenTreePalm(8, 3, false);
	    }

	    @Override
		public void decorate(World world, Random random, int x, int z) {
	    	BiomeGenBaseHighlands biome = this;
			this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
			((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
			((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
	    }
	    
	    
	    @SideOnly(Side.CLIENT)
	    public int getSkyColorByTemp(float par1)
	    {
	    	if(Highlands.skyColorFlag)return 0xFFEC9B;
	    	else return super.getSkyColorByTemp(par1);
	    }
	    
	    
}
