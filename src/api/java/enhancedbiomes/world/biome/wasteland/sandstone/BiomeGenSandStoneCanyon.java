package enhancedbiomes.world.biome.wasteland.sandstone;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenTreesEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSandStoneCanyon extends BiomeGenSandstoneBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenSandStoneCanyon(int par1)
    {
        super(par1);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.topBlock = Blocks.sandstone;
        this.fillerBlock = Blocks.sandstone;
        this.theBiomeDecorator.treesPerChunk = 30;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)TreeGen.birch();
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for(int c = 5; c > 0; c--)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
    		int l3 = par2Random.nextInt(120);
        	int j5 = par4 + par2Random.nextInt(16) + 8;
        	(new WorldGenMinableEnhancedBiomes(EnhancedBiomesMod.soilList[biomeID], EnhancedBiomesMod.soilMetaList[biomeID], 50, Blocks.sandstone)).generate(par1World, par2Random, j2, l3, j5);
    	}
        
        for(int c = 20; c > 0; c--)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
    		int l3 = par2Random.nextInt(120);
        	int j5 = par4 + par2Random.nextInt(16) + 8;
        	(new WorldGenShrub(0, 0)).generate(par1World, par2Random, j2, l3, j5);
    	}
        
        for(int c = 500; c > 0; c--)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
    		int l3 = par2Random.nextInt(120);
        	int j5 = par4 + par2Random.nextInt(16) + 8;
        	(new WorldGenTallGrass(Blocks.tallgrass, 1)).generate(par1World, par2Random, j2, l3, j5);
    	}
        
        for (var6 = 0; var6 < var5; ++var6)
        {
            var7 = par3 + par2Random.nextInt(16);
            var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            Block var10 = par1World.getBlock(var7, var8, var9);

            if (var10.isReplaceableOreGen(par1World, var7, var8, var9, Blocks.stone))
            {
                EnhancedBiomesBlocks.setStoneBlock(var7, var8, var9, Blocks.iron_ore, EnhancedBiomesBlocks.oreIronEB, 0, 2, par1World);
            }
        }

        for (var5 = 0; var5 < 7; ++var5)
        {
            var6 = par3 + par2Random.nextInt(16);
            var7 = par2Random.nextInt(64);
            var8 = par4 + par2Random.nextInt(16);
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
}
