package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlpsForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPAlpsForest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPAlpsForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.alpsForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPAlpsForest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainBOPAlpsForest(),
			new SurfaceBOPAlpsForest(topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone, 0.15f)
		);

		this.config = config;
		this.biomeWeight = ConfigBOP.weightBOPAlpsForest;
		this.generateVillages = ConfigBOP.villageBOPAlpsForest;
		this.generatesEmeralds = true;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        if (l > 0f && rand.nextInt(12) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);
        }
        
        for (int b = 0; b < 2f * strength; b++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (rand.nextInt(8) == 0) {
                
                if (rand.nextInt(10) == 0)
                {
                    (new WorldGenTreeShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
                }
                else
                {
                    (new WorldGenTreeShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
                }
            }
        }
    }
}
