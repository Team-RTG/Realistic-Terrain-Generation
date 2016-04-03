package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPJadeCliffs;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPJadeCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPJadeCliffs;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPJadeCliffs extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.jadeCliffs;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPJadeCliffs(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPJadeCliffs(300f, 100f, 68f),
			new SurfaceBOPJadeCliffs(config, topBlock, fillerBlock, false, null, 0.95f)
		);
		this.generatesEmeralds = true;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (this.config.getPropertyById(BiomeConfigBOPJadeCliffs.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (12f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                
                if (y22 < 100)
                {
                    (new WorldGenLog(BOPCBlocks.logs4, (byte)0, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                }
            }
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
