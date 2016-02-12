package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMysticGrove;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMysticGrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMysticGrove;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMysticGrove extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.mysticGrove;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMysticGrove(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMysticGrove(),
			new SurfaceBOPMysticGrove(config, topBlock, fillerBlock)
		);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (this.config.getPropertyById(BiomeConfigBOPMysticGrove.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt(8) == 0) {
                
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                            
                Block logBlock;
                byte logByte;
                int logLength;
                
                if (rand.nextInt(5) == 0) {
                    
                    logBlock = Blocks.log;
                    logByte = (byte)0;
                    logLength = 3 + rand.nextInt(4);
                }
                else {
                    
                    if (rand.nextInt(3) == 0) {
                        
                        logBlock = Blocks.log;
                        logByte = (byte)0;
                        logLength = 3 + rand.nextInt(2);
                    }
                    else {
                        if (rand.nextInt(3) == 0) {
                            
                            logBlock = BOPCBlocks.logs4;
                            logByte = (byte)2;
                            logLength = 3 + rand.nextInt(2);
                        }
                        else {
                            if (rand.nextInt(4) == 0) {
                                
                                logBlock = BOPCBlocks.logs2;
                                logByte = (byte)1;
                                logLength = 3 + rand.nextInt(2);
                            }
                            else {                            
                                
                                logBlock = Blocks.log;
                                logByte = (byte)0;
                                logLength = 3 + rand.nextInt(3);
                            }
                        }
                    }
                }
                
                if (y22 < 100)
                {
                    (new WorldGenLog(logBlock, logByte, Blocks.leaves, -1, logLength)).generate(world, rand, x22, y22, z22);
                }
            }
        }

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
