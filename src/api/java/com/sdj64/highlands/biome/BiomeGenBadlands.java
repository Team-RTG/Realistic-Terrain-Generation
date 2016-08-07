package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.HighlandsMod;
import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenBadlands extends BiomeGenBaseHighlands
{

    private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeGenBadlands(int par1)
    {
        super(par1);
        
        theBiomeDecorator.treesPerChunk = 1;
        theBiomeDecorator.grassPerChunk = 6;
        theBiomeDecorator.flowersPerChunk = 1;
        
        this.maxHeight = 0.6F;
        this.minHeight = 0.8F;
        this.temperature = 0.6F;
        this.rainfall = 0.1F;
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.mcOTulip);
        plants.add(HighlandsGenerators.mcBluet);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random random)
    {
        return (random.nextInt(3) != 0 ? HighlandsGenerators.shrub2Gen : this.worldGeneratorTrees);
    }

    public void decorate(World world, Random random, BlockPos pos)
    {
        //genStandardOre(6, HighlandsGenerators.stoneInDirt, 64, 128, world, random, pos);
    	
        super.decorate(world, random, pos);
        
        int i = 3 + random.nextInt(6);
        int j;
        int k;
        int l;

        for (j = 0; j < i; ++j)
        {
            k = random.nextInt(16);
            l = random.nextInt(28) + 4;
            int i1 = random.nextInt(16);
            BlockPos blockpos1 = pos.add(k, l, i1);

            if (world.getBlockState(blockpos1).getBlock().isReplaceableOreGen(world, blockpos1, net.minecraft.block.state.pattern.BlockHelper.forBlock(Blocks.stone)))
            {
                world.setBlockState(blockpos1, Blocks.emerald_ore.getDefaultState(), 2);
            }
        }
    }
    
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();

        if ((whatisthis < -1.0D || whatisthis > 2.0D) && this.terrainInt4 == this.terrainInt3)
        {
            this.topBlock = Blocks.dirt.getStateFromMeta(1);
            this.fillerBlock = Blocks.dirt.getStateFromMeta(1);
        }
        else if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.stained_hardened_clay.getStateFromMeta(8);
            this.fillerBlock = Blocks.hardened_clay.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
    
    
    public int getModdedBiomeGrassColor(int original)
    {
        return 0xCCB978;
    }
    
    /*
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0xFFCC8E;
    	else return super.getSkyColorByTemp(par1);
    }
    */
}




