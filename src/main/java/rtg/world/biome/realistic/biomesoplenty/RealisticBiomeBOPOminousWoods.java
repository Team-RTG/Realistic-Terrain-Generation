package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOminousWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOminousWoods;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPOminousWoods extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.ominousWoods;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPOminousWoods()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPOminousWoods(63f, 80f, 48f),
			new SurfaceBOPOminousWoods(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Ominous Woods");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPOminousWoods;
		this.generateVillages = ConfigBOP.villageBOPOminousWoods;
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

        if (rand.nextInt(6) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            Block log;
            byte logMeta;
            int intLogLength;
            
            int intLogRand = rand.nextInt(12);
            
            if (intLogRand < 3) {
                
                log = BOPCBlocks.logs1;
                logMeta = (byte)2;
                intLogLength = 3 + rand.nextInt(4);
            }
            else {
                
                log = BOPCBlocks.logs3;
                logMeta = (byte)2;
                intLogLength = 3 + rand.nextInt(2);
            }

            (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, x22, y22, z22);            
        }
    }
}
