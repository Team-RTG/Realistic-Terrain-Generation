package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeciduousForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeciduousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeciduousForest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class RealisticBiomeBOPDeciduousForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.deciduousForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPDeciduousForest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPDeciduousForest(),
			new SurfaceBOPDeciduousForest(config,
                topBlock, //Block top 
                (byte)0, //byte topByte
                fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                Blocks.dirt, //Block mixTop, 
                (byte)2, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.config = config;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (rand.nextInt(12) == 0) {
                
                if (rand.nextBoolean()) {
                    (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
                }
                else {
                    (new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
                }
            }
        }

        if (this.config.getPropertyById(BiomeConfigBOPDeciduousForest.decorationLogsId).valueBoolean) {
        
            if (l > 0f && rand.nextInt(16) == 0)
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
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
