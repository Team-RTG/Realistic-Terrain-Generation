package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenSpikedBush;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAlpineM extends BiomeGenSnowBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenAlpineM(int par1)
    {
        super(par1);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.stone;
        this.theBiomeDecorator.treesPerChunk = 10;
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return TreeGen.pine(par1Random);
    }    
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	if(par2Random.nextInt(5) == 0)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
        	int j5 = par4 + par2Random.nextInt(16) + 8;
    		int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5) - 1;
        	(new WorldGenMinableEnhancedBiomes(EnhancedBiomesMod.grassList[biomeID], EnhancedBiomesMod.grassMetaList[biomeID], 50, Blocks.snow, true)).generate(par1World, par2Random, j2, l3, j5);
    	}
    	
    	super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for (var5 = 0; var5 < 7; ++var5)
        {
            var6 = par3 + par2Random.nextInt(16);
            var7 = par2Random.nextInt(64);
            var8 = par4 + par2Random.nextInt(16);
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
    
    /**
	 * takes temperature, returns color
	 */
	/*@Override
	public int getSkyColorByTemp(float par1)
	{
		return 0xBBBBFF;
	}*/
}
