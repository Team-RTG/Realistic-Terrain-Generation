package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOminousWoods;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOminousWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOminousWoods;
import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPOminousWoods extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.ominous_woods.get();

    public static Block topBlock = BOPBlocks.grass;
    public static Block fillerBlock = BOPBlocks.dirt;

	public RealisticBiomeBOPOminousWoods(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPOminousWoods(65f, 80f, 48f),
			new SurfaceBOPOminousWoods(config, topBlock, fillerBlock)
		);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        if (this.config.getPropertyById(BiomeConfigBOPOminousWoods.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                
                Block log;
                byte logMeta;
                int intLogLength;
                
                int intLogRand = rand.nextInt(12);
                
                if (intLogRand < 3) {
                    
                    log = BOPBlocks.log_1;
                    logMeta = (byte)2;
                    intLogLength = 3 + rand.nextInt(4);
                }
                else {
                    
                    log = BOPBlocks.log_3;
                    logMeta = (byte)2;
                    intLogLength = 3 + rand.nextInt(2);
                }
    
                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
    }
}
