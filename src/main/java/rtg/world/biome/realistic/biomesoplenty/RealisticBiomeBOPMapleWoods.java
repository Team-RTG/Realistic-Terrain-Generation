package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMapleWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMapleWoods;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMapleWoods extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.mapleWoods;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMapleWoods()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPMapleWoods(63f, 80f, 30f),
			new SurfaceBOPMapleWoods(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Maple Woods");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPMapleWoods;
		this.generateVillages = ConfigBOP.villageBOPMapleWoods;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (rand.nextInt((int) (8f / strength)) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            if (y22 < 100)
            {
                if (rand.nextBoolean()) {
                    (new WorldGenLog(Blocks.log, (byte)0, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                }
                else {
                    (new WorldGenLog(Blocks.log, (byte)1, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                }
            }
        }
        
//        for (int f24 = 0; f24 < 2f * strength; f24++)
//        {
//            int i1 = chunkX + rand.nextInt(16) + 8;
//            int j1 = chunkY + rand.nextInt(16) + 8;
//            int k1 = world.getHeightValue(i1, j1);
//            
//            if (k1 < 110)
//            {
//                if (rand.nextBoolean()) {
//                    
//                    (new WorldGenTreeShrubCustom(rand.nextInt(4) + 1, Blocks.log, (byte)0, Blocks.leaves, (byte)2)).generate(world, rand, i1, k1, j1);
//                }
//                else {
//                    
//                    (new WorldGenTreeShrubCustom(rand.nextInt(4) + 1, Blocks.log, (byte)1, Blocks.leaves, (byte)1)).generate(world, rand, i1, k1, j1);
//                }
//            }
//        }
        
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
