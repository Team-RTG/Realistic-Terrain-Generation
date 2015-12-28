package enhancedbiomes.world.biome.woodland;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenShield extends BiomeGenWoodlandBase
{
    public BiomeGenShield(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.fillerBlock = Blocks.stone;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return par1Random.nextInt(3) == 0 ? TreeGen.pine(par1Random) : TreeGen.fir(par1Random);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	if(par2Random.nextInt(10) == 0)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
        	int j5 = par4 + par2Random.nextInt(16) + 8;
    		int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5) - 1;
        	(new WorldGenMinableEnhancedBiomes(Blocks.air, 0, 125, EnhancedBiomesMod.grassList[biomeID], true)).generate(par1World, par2Random, j2, l3, j5);
    	}
    	
    	super.decorate(par1World, par2Random, par3, par4);
    }
}
