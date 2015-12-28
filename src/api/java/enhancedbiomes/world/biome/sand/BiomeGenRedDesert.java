package enhancedbiomes.world.biome.sand;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenSandBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomesDesert;
import enhancedbiomes.world.gen.WorldGenRockSpire;
import enhancedbiomes.world.gen.WorldGenSaguaro;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeGenRedDesert extends BiomeGenSandBase
{
    int largeCactiPerChunk;
	
	public BiomeGenRedDesert(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 4;
        this.theBiomeDecorator.cactiPerChunk = 0;
        this.largeCactiPerChunk = 1;
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);

        for(int c = 0; c < largeCactiPerChunk; c++)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
        	int j5 = par4 + par2Random.nextInt(16) + 8;
    		int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5);
        	new WorldGenSaguaro().generate(par1World, par2Random, j2, l3, j5);
    	}
    }
}
