package rtg.world.biome.realistic.atg;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.atg.SurfaceATGVolcano;
import rtg.world.gen.terrain.atg.TerrainATGVolcano;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGVolcano extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGVolcano(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeGenBase.river,
			new TerrainATGVolcano(),
			new SurfaceATGVolcano(config, atgBiome.topBlock, atgBiome.fillerBlock, true, Blocks.gravel, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.gravel, 0.08f)
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
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        for (int l14 = 0; l14 < 15; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(3) == 0)
            {
                (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
            }
            else
            {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
            }
        }
    }
    
    @Override
    public void rMapGen(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY,
        int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[])
    {
    
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(6) == 0)
        {
            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16))
            {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());
                
                WorldGenVolcano.build(blocks, metadata, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
}
