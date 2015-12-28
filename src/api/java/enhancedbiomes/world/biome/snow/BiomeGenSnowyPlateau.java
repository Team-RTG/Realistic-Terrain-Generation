package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSnowyPlateau extends BiomeGenSnowBase
{
    public BiomeGenSnowyPlateau(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.grassPerChunk = 5;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(2) == 0 ? new WorldGenShrub(1, 1) : TreeGen.fir(par1Random));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
    	return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }
    
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
    }
}
