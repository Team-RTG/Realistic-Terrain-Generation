package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFungiForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFungiForest;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class RealisticBiomeBOPFungiForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.fungiForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPFungiForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPFungiForest(),
			new SurfaceBOPFungiForest(
                topBlock, //Block top 
                (byte)0, //byte topByte
                fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                Blocks.mycelium, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte, 
                Blocks.stone, //Block cliff1, 
                (byte)0, //byte cliff1Byte, 
                Blocks.cobblestone, //Block cliff2, 
                (byte)0, //byte cliff2Byte, 
                40f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.setRealisticBiomeName("BOP Fungi Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPFungiForest;
		this.generateVillages = ConfigBOP.villageBOPFungiForest;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {

        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;

        if (l > 0f && rand.nextInt(6) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            Block log;
            byte logMeta;

            log = Blocks.log;
            logMeta = (byte)0;
            
            (new WorldGenLog(log, logMeta, Blocks.leaves, -1, 3 + rand.nextInt(3))).generate(world, rand, x22, y22, z22);            
        }

        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
