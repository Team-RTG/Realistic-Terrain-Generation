package enhancedbiomes.world.biome.wetland;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenWetlandBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenTallGrassEB;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenEphemeralLake extends BiomeGenWetlandBase
{
    public BiomeGenEphemeralLake(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.grassPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = -999;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(5) >= 1 ? new WorldGenShrub(0, 0) : TreeGen.dead(par1Random));/*new WorldGenOak(logJungle, 1, 0, 0, 4 + par1Random.nextInt(3), 5, 16, Blocks.grass));*///new WorldGenBiggerTree(true, 12, logJungle, 0, 4, 0.9D, 0.4D, 1, 0, 1, Blocks.grass, true));
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
    	return (par1Random.nextInt(4) >= 1 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrassEB(Blocks.tallgrass, 0, Blocks.sand));
    }
    
    public void decorate(World worldObj, Random rand, int x, int z)
    {
        super.decorate(worldObj, rand, x, z);
		
        for(int c = 50; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	(new WorldGenMinableEnhancedBiomes(Blocks.sand, 0, 100, EnhancedBiomesMod.grassList[biomeID], true)).generate(worldObj, rand, j2, l3, j5);
    	}
    }        
}

