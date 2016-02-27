package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMysticGrove;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMysticGrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMysticGrove;

import java.util.Random;

public class RealisticBiomeBOPMysticGrove extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.mystic_grove.get();
	
	public static IBlockState topBlock = bopBiome.topBlock;
	public static IBlockState fillerBlock = bopBiome.fillerBlock;
	
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
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (this.config.getPropertyById(BiomeConfigBOPMysticGrove.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt(8) == 0) {
                
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                            
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
                            
                            logBlock = BOPBlocks.log_4;
                            logByte = (byte)2;
                            logLength = 3 + rand.nextInt(2);
                        }
                        else {
                            if (rand.nextInt(4) == 0) {
                                
                                logBlock = BOPBlocks.log_2;
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
                    (new WorldGenLog(logBlock, logByte, Blocks.leaves, -1, logLength)).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
