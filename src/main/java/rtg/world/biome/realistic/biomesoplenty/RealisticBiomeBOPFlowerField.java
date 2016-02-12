package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFlowerField;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFlowerField;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPFlowerField extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.flowerField;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPFlowerField(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPFlowerField(),
			new SurfaceBOPFlowerField(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, topBlock, 0.05f)
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
    
        for (int f23 = 0; f23 < 16f * strength; f23++)
        {
            int j15 = chunkX + rand.nextInt(16) + 8;
            int j20 = chunkY + rand.nextInt(16) + 8;
            int j17 = world.getHeightValue(j15, j20);
            
            (new WorldGenFlowers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15})).generate(world, rand, j15, j17, j20);
        }

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int f24 = 0; f24 < 3f * strength; f24++)
        {
            if (rand.nextInt(12) == 0) {
                
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeightValue(i1, j1);
                if (k1 < 110)
                {
                    (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
                }
            }
        }
        
        for (int l14 = 0; l14 < 6f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
