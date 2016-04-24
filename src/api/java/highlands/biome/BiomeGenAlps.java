package highlands.biome;

import highlands.Highlands;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeFir;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenAlps extends BiomeGenBaseHighlands{

	private static final Height biomeHeight = new Height(1.1F, 0.5F);
	
	public BiomeGenAlps(int par1)
	{
	    super(par1);
	    int trees = 1;
	    int grass = 0;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
	    this.setHeight(biomeHeight);
	    this.spawnableCreatureList.clear();
	    this.topBlock = Blocks.snow;
	    this.fillerBlock = Blocks.snow;
	    this.temperature = 0.0F;
	    this.rainfall = 0.7F;
	        
	    this.setEnableSnow();
	}
	    
	@Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
	    return (WorldGenAbstractTree)(par1Random.nextInt(5) == 0 ? new WorldGenTreeFir(10, 5, false, false) : new WorldGenHighlandsShrub(1, 1));
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
        
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 0, 64);
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(Highlands.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
    
    /*
    public void setSpawns(List hostile, List creature, List water){
    	this.spawnableMonsterList = hostile;
    	this.spawnableCreatureList = creature;
    	this.spawnableWaterCreatureList = water;
    }
    */
}
