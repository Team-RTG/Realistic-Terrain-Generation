package enhancedbiomes.world.biome.woodland.tropical;

import java.util.List;
import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenTropicalBase;
import enhancedbiomes.world.gen.WorldGenLakesEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.*;

public class BiomeGenRainforest extends BiomeGenTropicalBase
{
    public BiomeGenRainforest(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 35;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)/*(par1Random.nextInt(24) == 0 ? TreeGen.kapok(par1Random) : */
        						(par1Random.nextInt(8) == 0 ? new WorldGenMegaJungle(false, 10, 20, 3, 3) : 
        						(par1Random.nextInt(6) == 0 ? TreeGen.fir_large(par1Random) : 
        						(par1Random.nextInt(4) == 0 ? TreeGen.fir(par1Random) : 
        						(par1Random.nextInt(2) == 0 ? new WorldGenSwamp() : 
        						new WorldGenTrees(true, 6, 3, 3, true)))));
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
    	/*BiomeDecoratingHelper bdh = new BiomeDecoratingHelper();
        bdh.currentWorld = par1World;
        bdh.randomGenerator = par2Random;
        bdh.chunk_X = par3;
        bdh.chunk_Z = par4;
        bdh.createWorld(this);
        bdh.currentWorld = null;
        bdh.randomGenerator = null;*/
    	
        for(int c = 150; c > 0; c--)
    	{
    		int posX = par3 + par2Random.nextInt(16);
    		int posY = par2Random.nextInt(120);
        	int posZ = par4 + par2Random.nextInt(16);
        	if(par1World.getBlock(posX, posY, posZ) == Blocks.air && EnhancedBiomesBlocks.isGrass(par1World.getBlock(posX, posY - 1, posZ)))
        	{
        		(new WorldGenLakesEnhancedBiomes(Blocks.flowing_water, Blocks.obsidian)).generate(par1World, par1World.rand, posX, posY, posZ);
        	}
    	}
    }
}