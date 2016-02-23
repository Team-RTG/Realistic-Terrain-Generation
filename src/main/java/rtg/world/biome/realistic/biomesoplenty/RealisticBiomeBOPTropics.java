package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTropics;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropics;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropics;

import java.util.Random;

public class RealisticBiomeBOPTropics extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.tropical_island.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPTropics(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTropics(),
			new SurfaceBOPTropics(config,
                topBlock, //Block top
                (byte)0, //byte topByte
                fillerBlock, //Block filler,
                (byte)0, //byte fillerByte
                Blocks.sand, //Block mixTop,
                (byte)0, //byte mixTopByte,
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                10f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                5f, //float smallWidth, 
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

        if (this.config.getPropertyById(BiomeConfigBOPTropics.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt(12) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                
                Block log;
                byte logMeta;
                int intLogLength;
    
                log = BOPBlocks.log_2;
                logMeta = (byte)3;
                intLogLength = 3 + rand.nextInt(2);
    
                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
    }
}
