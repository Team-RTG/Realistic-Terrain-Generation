package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biomes.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSnowyConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSnowyConiferousForest;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class RealisticBiomeBOPSnowyConiferousForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.snowyConiferousForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSnowyConiferousForest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainBOPSnowyConiferousForest(63f, 70f, 25f),
			new SurfaceBOPSnowyConiferousForest(topBlock, fillerBlock, false, null, 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigBOP.weightBOPSnowyConiferousForest;
		this.generateVillages = ConfigBOP.villageBOPSnowyConiferousForest;
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
            
            if (rand.nextInt(16) == 0) {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }

        if (l > 0f && rand.nextInt(16) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            Block log;
            byte logMeta;

            log = BOPCBlocks.logs1;
            logMeta = (byte)3;
            
            (new WorldGenLog(log, logMeta, Blocks.leaves, -1, 3 + rand.nextInt(3))).generate(world, rand, x22, y22, z22);            
        }
        
        if (rand.nextInt(12) != 0) {
            rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        }
        else {
            rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        }
    }
}
