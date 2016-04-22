package highlands.biome;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeCanopy;

import java.util.Random;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTropics extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.4F);

	public BiomeGenTropics(int par1)
	{
        super(par1);
        int trees = 20;
	    int grass = 25;
	    int flowers = 4;
	    int plants = 3;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);

	    this.theBiomeDecorator.generateLakes = true;
	    
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.setHeight(biomeHeight);
        this.temperature = 1.2F;
        this.rainfall = 1.0F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.leafyFern);
	}


	 
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(2) == 0 ? 
        		(par1Random.nextInt(4) == 0 ? new WorldGenTreeCanopy(13, 6, false, true) :
        			new WorldGenTreeCanopy(13, 6, false, false)) :
        			new WorldGenHighlandsShrub(3, 0));
    }
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }

    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
        WorldGenVines var5 = new WorldGenVines();

        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = x + random.nextInt(16) + 8;
            byte var8 = 64;
            int var9 = z + random.nextInt(16) + 8;
            var5.generate(world, random, var7, var8, var9);
        }
        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = x + random.nextInt(16) + 8;
            byte var8 = 36;
            int var9 = z + random.nextInt(16) + 8;
            var5.generate(world, random, var7, var8, var9);
        }
        
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 8, this.theBiomeDecorator.redstoneGen, 0, 16);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 1, this.theBiomeDecorator.lapisGen, 0, 32);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
    }
    
}
