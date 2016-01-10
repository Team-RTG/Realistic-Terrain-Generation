package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.api.biomes.biomesoplenty.config.BiomeConfigBOPSeasonalForestClearing;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSeasonalForestClearing;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSeasonalForestClearing;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class RealisticBiomeBOPSeasonalForestClearing extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.seasonalForestClearing;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSeasonalForestClearing()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPSeasonalForestClearing(63f, 66f, 24f),
			new SurfaceBOPSeasonalForestClearing(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigBOPSeasonalForestClearing();
		this.biomeWeight = ConfigBOP.weightBOPSeasonalForestClearing;
		this.generateVillages = ConfigBOP.villageBOPSeasonalForestClearing;
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
            
            if (rand.nextInt(48) == 0) {
                
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }

        if (rand.nextInt(24) == 0)
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
                intLogLength = 2 + rand.nextInt(2);
            }
            else if (intLogRand < 9) {
                
                log = Blocks.log;
                logMeta = (byte)0;
                intLogLength = 2 + rand.nextInt(2);
            }
            else {
                
                log = Blocks.log;
                logMeta = (byte)2;
                intLogLength = 2 + rand.nextInt(2);
            }

            (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, x22, y22, z22);            
        }
    }
}
