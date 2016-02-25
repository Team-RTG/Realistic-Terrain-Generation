package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPRedwoodForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRedwoodForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRedwoodForest;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class RealisticBiomeBOPRedwoodForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.redwoodForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPRedwoodForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPRedwoodForest(58f, 80f, 30f),
			new SurfaceBOPRedwoodForest(config, topBlock, fillerBlock, false, null, 0.4f)
		);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (rand.nextInt(16) == 0) {
                
                if (rand.nextBoolean()) {
                    
                    (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
                }
                else {
                    
                    (new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
                }
            }
        }

        if (this.config.getPropertyById(BiomeConfigBOPRedwoodForest.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt(12) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                
                Block log;
                byte logMeta;
                int intLogLength;
                
                int intLogRand = rand.nextInt(12);
                
                if (intLogRand < 3) {
                    
                    log = BOPCBlocks.logs3;
                    logMeta = (byte)0;
                    intLogLength = 3 + rand.nextInt(6);
                }
                else if (intLogRand < 9) {
                    
                    log = BOPCBlocks.logs3;
                    logMeta = (byte)0;
                    intLogLength = 3 + rand.nextInt(8);
                }
                else {
                    
                    log = BOPCBlocks.logs3;
                    logMeta = (byte)0;
                    intLogLength = 3 + rand.nextInt(10);
                }
    
                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, x22, y22, z22);            
            }
        }
    }
}
