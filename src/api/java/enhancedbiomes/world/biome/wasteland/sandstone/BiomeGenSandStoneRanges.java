package enhancedbiomes.world.biome.wasteland.sandstone;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenSpikedBush;
import enhancedbiomes.world.gen.WorldGenTallShrub;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSandStoneRanges extends BiomeGenSandstoneBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenSandStoneRanges(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 7;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.topBlock = Blocks.dirt;
        this.fillerBlock = Blocks.sandstone;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(2) == 0 ? 
        			new WorldGenSpikedBush(Blocks.log, 0, Blocks.leaves, 0, EnhancedBiomesMod.soilList[biomeID]) : par1Random.nextInt(6) == 0 ? 
        			TreeGen.cypress(par1Random) : par1Random.nextInt(12) == 0 ? 
        			TreeGen.eucalyptus(par1Random) : 
        			TreeGen.eucalyptus_shrub(par1Random));
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for (var6 = 0; var6 < var5; ++var6)
        {
            var7 = par3 + par2Random.nextInt(16);
            var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            Block var10 = par1World.getBlock(var7, var8, var9);

            if (var10.isReplaceableOreGen(par1World, var7, var8, var9, Blocks.stone))
            {
                EnhancedBiomesBlocks.setStoneBlock(var7, var8, var9, Blocks.emerald_ore, EnhancedBiomesBlocks.oreEmeraldEB, 0, 2, par1World);
            }
        }

        for(int c = 50; c > 0; c--)
       	{
    		int j2 = par3 + par2Random.nextInt(16) + 8;
    		int l3 = 64 + par2Random.nextInt(64);
        	int j5 = par4 + par2Random.nextInt(16) + 8;
        	(new WorldGenMinableEnhancedBiomes(EnhancedBiomesMod.getDominantStone(j2, j5, par1World), EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), 50, EnhancedBiomesMod.soilList[biomeID], true)).generate(par1World, par2Random, j2, l3, j5);
    	}
    }
}
