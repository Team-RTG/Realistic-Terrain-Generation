package enhancedbiomes.world.biome.woodland;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomesKakadu;
import enhancedbiomes.world.gen.WorldGenSpikedBush;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenKakadu extends BiomeGenWoodlandBase
{
    public BiomeGenKakadu(int par1)
    {
        super(par1);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.theBiomeDecorator.treesPerChunk = 8;
        this.theBiomeDecorator.grassPerChunk = 6;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenShrub(0, 0) : 
        	new WorldGenSpikedBush(Blocks.log, 0, Blocks.leaves, 0, EnhancedBiomesMod.grassList[biomeID]);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	if(par2Random.nextInt(3) == 0)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
        	int j5 = par4 + par2Random.nextInt(16) + 8;
    		int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5) - 1;
        	(new WorldGenMinableEnhancedBiomesKakadu(EnhancedBiomesMod.getDominantStone(j2, j5, par1World), EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), 50, EnhancedBiomesMod.grassList[biomeID], true)).generate(par1World, par2Random, j2, l3, j5);
    	}
    	
    	super.decorate(par1World, par2Random, par3, par4);
    }
}
