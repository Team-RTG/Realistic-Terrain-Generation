package highlands.biome;

import highlands.Highlands;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenFlyingMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-1.5F, 1.8F);//was 7.0F
	
	public BiomeGenFlyingMountains(int par1)
    {
        super(par1);
	    int trees = 20;
	    int grass = 10;
	    int flowers = 0;
	    int plants = 4;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
        this.setHeight(biomeHeight);
        this.temperature = 0.7F;
        this.rainfall = 1.2F;
        this.spawnableCreatureList.clear();
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueFlower);
	}

	/**
    * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	return (WorldGenAbstractTree)new WorldGenHighlandsShrub(0, 0);
    }
	
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.diamondGen, 0, 16);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLwater, 0, 128);
        
        //random water sources on top of the mountains
        for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
    			if(world.getBiomeGenForCoords(x+i, z+j) == HighlandsBiomes.flyingMountains){
	    			int topY = 128;
	    			Block var11;
	    	        for (boolean var6 = false; (world.isAirBlock(x+i, topY, z+j) || world.getBlock(x+i, topY, z+j) == Blocks.leaves) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    	        if(topY > 65){
		    			if(world.isAirBlock(x+i, topY, z+j))topY--;
		    			int a = random.nextInt(10);
		    			if(a == 9 && random.nextInt(10) == 0){
		    				world.setBlock(x+i, topY, z+j, Blocks.water, 0, 3);
		    				world.setBlock(x+i, topY+1, z+j, Blocks.air, 0, 3);
		    			}
	    	        }
	    		}
    		}
    	}
    }
    
    public int getBiomeFoliageColor(){
    	return 0x00BA78;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return getModdedBiomeGrassColor(0x00BA78);
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(Highlands.skyColorFlag)return 0x6868FF;
    	else return super.getSkyColorByTemp(par1);
    }
}
