package enhancedbiomes.world.biome.sand;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenSandBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenSpikedBush;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenXericShrubland extends BiomeGenSandBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenXericShrubland(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 20;
        this.theBiomeDecorator.cactiPerChunk = 16;
        this.topBlock = Blocks.grass;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return par1Random.nextInt(3) != 0 ? (WorldGenAbstractTree)new WorldGenSpikedBush(Blocks.log, 0, Blocks.leaves, 0, EnhancedBiomesMod.soilList[biomeID]) : new WorldGenShrub(0, 0);
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        for(int c = 4; c > 0; c--)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
        	int j5 = par4 + par2Random.nextInt(16) + 8;
    		int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5);
        	(new WorldGenMinableEnhancedBiomes(Blocks.sand, 0, 50, EnhancedBiomesMod.grassList[biomeID], true)).generate(par1World, par2Random, j2, l3, j5);
    	}
        
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;
    }
}
