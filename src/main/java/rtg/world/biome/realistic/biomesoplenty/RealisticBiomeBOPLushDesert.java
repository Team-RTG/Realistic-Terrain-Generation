package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushDesert;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenJungleCacti;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushDesert;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushDesert;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

public class RealisticBiomeBOPLushDesert extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.lush_desert.get();
	
	public static IBlockState topBlock = bopBiome.topBlock;
	public static IBlockState fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLushDesert(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPLushDesert(65f, 40f, 10f),
			new SurfaceBOPLushDesert(config,
                topBlock, //Block top
                fillerBlock, //Block filler,
                topBlock, //IBlockState mixTop,
                fillerBlock, //IBlockState mixFill,
                40f, //float mixWidth, 
                -0.15f, //float mixHeight,
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
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
        
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            
            if (rand.nextInt(16) == 0) {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
//        for (int b1 = 0; b1 < l * strength; b1++)
//        {
//            if (rand.nextInt(2) == 0)
//            {
//                int j6 = chunkX + rand.nextInt(16) + 8;
//                int k10 = chunkY + rand.nextInt(16) + 8;
//                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
//                
//                if (z52 < 110)
//                {
//                    if (rand.nextBoolean()) {
//                        WorldGenerator worldgenerator =
//                            rand.nextInt(2) == 0 ? this.worldGeneratorTrees :
//                                rand.nextInt(18) == 0 ? new WorldGenDeadTree() :
//                                    rand.nextInt(4) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 0, 0, false, 7, 10, 2)
//                                        : new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
//                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
//                    }
//                    else {
//                        WorldGenerator worldgenerator =
//                            rand.nextInt(2) == 0 ? this.worldGeneratorTrees :
//                                rand.nextInt(18) == 0 ? new WorldGenDeadTree() :
//                                    rand.nextInt(4) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 0, 0, false, 7, 10, 2)
//                                        : new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
//                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
//                    }
//                }
//            }
//        }

        if (this.config.getPropertyById(BiomeConfigBOPLushDesert.decorationLogsId).valueBoolean) {
        
            if (l > 0f && rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                
                Block log;
                byte logMeta;
                int intLogLength;
                
                int intLogRand = rand.nextInt(12);
                
                if (intLogRand < 3) {
                    
                    log = Blocks.log2;
                    logMeta = (byte)1;
                    intLogLength = 3 + rand.nextInt(4);
                }
                else if (intLogRand < 9) {
                    
                    log = BOPBlocks.log_3;
                    logMeta = (byte)2;
                    intLogLength = 3 + rand.nextInt(2);
                }
                else {
                    
                    log = Blocks.log;
                    logMeta = (byte)0;
                    intLogLength = 3 + rand.nextInt(2);
                }
    
                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
        
        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), CACTUS)) {
            
            for (int k18 = 0; k18 < 8f * strength; k18++)
            {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = rand.nextInt(160);
                int k24 = chunkY + rand.nextInt(16) + 8;
                
                if (j23 < 120f)
                {
                    (new WorldGenJungleCacti(false, rand.nextInt(5), (byte)1)).generate(world, rand, new BlockPos(k21, j23, k24));
                }
            }
        }
    }
}
