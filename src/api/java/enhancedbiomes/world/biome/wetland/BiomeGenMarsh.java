package enhancedbiomes.world.biome.wetland;

import java.util.List;
import java.util.Random;

import enhancedbiomes.helpers.EnhancedBiomesWorldHelper;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenWetlandBase;
import enhancedbiomes.world.gen.WorldGenLakesEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.*;

public class BiomeGenMarsh extends BiomeGenWetlandBase
{
	public BiomeGenMarsh(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.clayPerChunk = 1;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
        this.waterColorMultiplier = 0x33FF33;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return TreeGen.swamp();
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        
        if(par2Random.nextInt(1) == 0)
    	{
    		int posX = par3 + par2Random.nextInt(16);
        	int posZ = par4 + par2Random.nextInt(16);
    		int posY = EnhancedBiomesWorldHelper.getTopSolidBlock(posX, posZ, par1World);
        	new WorldGenLakesEnhancedBiomes(Blocks.flowing_water, Blocks.obsidian).generate(par1World, par1World.rand, posX, posY, posZ);
    	}
        
        for(int c = 5; c > 0; c--)
    	{
    		int posX = par3 + par2Random.nextInt(16);
        	int posZ = par4 + par2Random.nextInt(16);
    		int posY = EnhancedBiomesWorldHelper.getTopSolidBlock(posX, posZ, par1World);
    		TreeGen.swamp().generate(par1World, par1World.rand, posX, posY, posZ);
    	}
    }
}