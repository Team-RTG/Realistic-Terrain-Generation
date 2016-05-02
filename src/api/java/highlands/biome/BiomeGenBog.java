package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenBog extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.2F, 0.1F);

	public BiomeGenBog(int par1){
		super(par1);
		int trees = 3;
	    int grass = 2;
	    int flowers = 0;
	    int plants = 6;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    
	    this.theBiomeDecorator.generateLakes = true;
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 0.7F;
        this.rainfall = 1.2F;
        this.waterColorMultiplier = 0x84FF38;
	        
	}
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cattail);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	return (WorldGenAbstractTree)(par1Random.nextInt(3) == 0? this.worldGeneratorSwamp : 
        	(par1Random.nextInt(3) == 0? new WorldGenHighlandsBigTree(false, false, 0, 0, 1, 0) : this.worldGeneratorBigTree));
    }

    @Override
	public void decorate(World world, Random random, int x, int z) {
    	BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.coalGen, 0, 128);
    }
    
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return getModdedBiomeGrassColor(0x545B33);
    }

    /*
    @SideOnly(Side.CLIENT)

    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
    }
    */
}











