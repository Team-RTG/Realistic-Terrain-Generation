package rtg.world.biome.realistic.biomesoplenty;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenJungleCacti;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushDesert;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushDesert;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

import net.minecraftforge.event.terraingen.TerrainGen;

public class RealisticBiomeBOPLushDesert extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.lushDesert;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLushDesert(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainBOPLushDesert(63f, 69f, 40f),
			new SurfaceBOPLushDesert(
                topBlock, //Block top 
                (byte)0, //byte topByte
                fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                40f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigBOP.weightBOPLushDesert;
		this.generateVillages = ConfigBOP.villageBOPLushDesert;
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
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }
        
//        for (int b1 = 0; b1 < l * strength; b1++)
//        {
//            if (rand.nextInt(2) == 0)
//            {
//                int j6 = chunkX + rand.nextInt(16) + 8;
//                int k10 = chunkY + rand.nextInt(16) + 8;
//                int z52 = world.getHeightValue(j6, k10);
//                
//                if (z52 < 110)
//                {
//                    if (rand.nextBoolean()) {
//                        WorldGenerator worldgenerator =
//                            rand.nextInt(2) == 0 ? this.worldGeneratorTrees :
//                                rand.nextInt(18) == 0 ? new WorldGenDeadTree() :
//                                    rand.nextInt(4) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 0, 0, false, 7, 10, 2)
//                                        : new WorldGenShrub(0, 0);
//                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
//                        worldgenerator.generate(world, rand, j6, z52, k10);
//                    }
//                    else {
//                        WorldGenerator worldgenerator =
//                            rand.nextInt(2) == 0 ? this.worldGeneratorTrees :
//                                rand.nextInt(18) == 0 ? new WorldGenDeadTree() :
//                                    rand.nextInt(4) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 0, 0, false, 7, 10, 2)
//                                        : new WorldGenShrub(0, 0);
//                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
//                        worldgenerator.generate(world, rand, j6, z52, k10);
//                    }
//                }
//            }
//        }

        if (l > 0f && rand.nextInt(6) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
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
                
                log = BOPCBlocks.logs3;
                logMeta = (byte)2;
                intLogLength = 3 + rand.nextInt(2);
            }
            else {
                
                log = Blocks.log;
                logMeta = (byte)0;
                intLogLength = 3 + rand.nextInt(2);
            }

            (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, x22, y22, z22);            
        }
        
        if (TerrainGen.decorate(world, rand, chunkX, chunkY, CACTUS)) {
            
            for (int k18 = 0; k18 < 8f * strength; k18++)
            {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = rand.nextInt(160);
                int k24 = chunkY + rand.nextInt(16) + 8;
                
                if (j23 < 120f)
                {
                    (new WorldGenJungleCacti(false, rand.nextInt(5), (byte)1)).generate(world, rand, k21, j23, k24);
                }
            }
        }
    }
}
