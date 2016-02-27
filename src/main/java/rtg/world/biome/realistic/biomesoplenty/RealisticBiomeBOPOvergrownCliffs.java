package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOvergrownCliffs;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOvergrownCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOvergrownCliffs;
import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPOvergrownCliffs extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.overgrown_cliffs.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPOvergrownCliffs(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPOvergrownCliffs(300f, 100f, 0f),
			new SurfaceBOPOvergrownCliffs(config, topBlock, fillerBlock, false, null, 0.95f)
		);
		this.generatesEmeralds = true;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (this.config.getPropertyById(BiomeConfigBOPOvergrownCliffs.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (12f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                
                if (y22 < 100)
                {
                    (new WorldGenLog(BOPBlocks.log_4, (byte)0, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
