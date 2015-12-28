/*package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.BiomeGenSnowBase;
import enhancedbiomes.world.gen.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenDrifts extends BiomeGenSnowBase
{
    public BiomeGenDrifts(int par1)
    {
        super(par1);
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.snow;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     *//*
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return TreeGen.fir(par1Random);
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     *//*
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {        
    	for(int x = 0; x < 16; x++)
        {
    		int var5 = par3 + x;
            int var6 = par4 + (var5 / 3) % 16;
            new WorldGenDunesSnow(this, Blocks.snow).generate(par1World, par2Random, var5, par1World.getTopSolidOrLiquidBlock(var5, var6), var6);
        }
    }
}
*/