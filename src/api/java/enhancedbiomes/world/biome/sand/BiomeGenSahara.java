package enhancedbiomes.world.biome.sand;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenSandBase;
import enhancedbiomes.world.gen.WorldGenDunes;
import enhancedbiomes.world.gen.WorldGenRockSpire;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeGenSahara extends BiomeGenSandBase
{
    public BiomeGenSahara(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        this.theBiomeDecorator.cactiPerChunk = 10;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {        
    	for(int x = 0; x < 16; x++)
        {
    		int var5 = par3 + x;
            int var6 = par4 + (var5 / 3) % 16;
            new WorldGenDunes(this, Blocks.sand).generate(par1World, par2Random, var5, par1World.getTopSolidOrLiquidBlock(var5, var6), var6);
        }
        
        super.decorate(par1World, par2Random, par3, par4);

        if (par2Random.nextInt(3) == 0)
        {
    		int j2 = par3 + par2Random.nextInt(16) + 8;
        	int j5 = par4 + par2Random.nextInt(16) + 8;

    		int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5);
        	(new WorldGenRockSpire(new Block[]{EnhancedBiomesMod.getDominantStone(j2, j5, par1World), EnhancedBiomesMod.getCobbleFromStone(EnhancedBiomesMod.getDominantStone(j2, j5, par1World)), Blocks.sandstone}, 
        							new byte[]{EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), 0}, 10)).generate(par1World, par2Random, j2, l3, j5);
    	}
        
        if (par2Random.nextInt(500) == 0)
        {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenDesertWells var7 = new WorldGenDesertWells();
            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
        }
    }
}
