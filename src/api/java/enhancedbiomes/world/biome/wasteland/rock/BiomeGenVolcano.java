package enhancedbiomes.world.biome.wasteland.rock;

import java.util.List;
import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.biome.base.BiomeGenRockBase;
import enhancedbiomes.world.gen.WorldGenMagmaSpout;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.*;

public class BiomeGenVolcano extends BiomeGenRockBase
{
	Block ground = EnhancedBiomesMod.obsidianVolcano ? Blocks.obsidian : EnhancedBiomesBlocks.stoneEB;
	
    public BiomeGenVolcano(int par1)
    {
        super(par1);
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
        this.topBlock = ground;
        this.fillerBlock = ground;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;
        
        for(int c = 100; c > 0; c--)
    	{
        	int posX = par3 + par2Random.nextInt(16);
    		int posY = par2Random.nextInt(120);
        	int posZ = par4 + par2Random.nextInt(16);
        	if(par1World.getBlock(posX, posY, posZ) == Blocks.air && par1World.getBlock(posX, posY - 1, posZ) == ground)
        	{
        		(new WorldGenMagmaSpout(Blocks.flowing_lava, 2 + par2Random.nextInt(3), ground)).generate(par1World, par1World.rand, posX, posY, posZ);
        	}
    	}

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