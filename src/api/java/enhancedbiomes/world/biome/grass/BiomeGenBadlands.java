package enhancedbiomes.world.biome.grass;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.EnhancedBiomesWorldHelper;
import enhancedbiomes.world.biome.base.BiomeGenGrassBase;
import enhancedbiomes.world.gen.WorldGenBadlands;
import enhancedbiomes.world.gen.WorldGenInselberg;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.util.ForgeDirection;

public class BiomeGenBadlands extends BiomeGenGrassBase
{
    public BiomeGenBadlands(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        this.theBiomeDecorator.cactiPerChunk = 10;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {    	
    	int var5 = par3 + par2Random.nextInt(16);
    	int y = par2Random.nextInt(128);
        int var6 = par4 + par2Random.nextInt(16);
        if(y >= 55 && y <= 65)
        {
            new WorldGenInselberg(16 + par2Random.nextInt(9), 0.7F, EnhancedBiomesMod.getDominantStone(var5, var6, par1World), EnhancedBiomesMod.getDominantStoneMeta(var5, var6, par1World)).generate(par1World, par2Random, var5, EnhancedBiomesWorldHelper.getTopStoneBlock(var5, var6, par1World) - 2, var6);
        }
        
    	for(int c = 2; c > 0; c--)
    	{
        	var5 = par3 + par2Random.nextInt(16);
            var6 = par4 + par2Random.nextInt(16);
            if(EnhancedBiomesBlocks.isGrass(par1World.getBlock(var5, par1World.getTopSolidOrLiquidBlock(var5, var6) - 1, var6)))
            {
                new WorldGenBadlands(3 + par2Random.nextInt(2), 2, EnhancedBiomesMod.getDominantStone(var5, var6, par1World), EnhancedBiomesMod.getDominantStoneMeta(var5, var6, par1World)).generate(par1World, par2Random, var5, EnhancedBiomesWorldHelper.getTopStoneBlock(var5, var6, par1World), var6);
            }
    	}
    	
        super.decorate(par1World, par2Random, par3, par4);
    }
}
