package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenPolarDesert extends BiomeGenSnowBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenPolarDesert(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.stone;
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for(int c = 50; c > 0; c--)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
    		int l3 = 64 + par2Random.nextInt(64);
        	int j5 = par4 + par2Random.nextInt(16) + 8;
        	(new WorldGenMinableEnhancedBiomes(EnhancedBiomesMod.getDominantStone(j2, j5, par1World), EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), 50, Blocks.snow, true)).generate(par1World, par2Random, j2, l3, j5);
    	}
    }
}
