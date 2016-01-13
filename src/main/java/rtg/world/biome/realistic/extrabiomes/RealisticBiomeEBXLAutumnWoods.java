package rtg.world.biome.realistic.extrabiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLAutumnWoods;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLAutumnWoods;
import cpw.mods.fml.common.registry.GameData;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLAutumnWoods extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.autumnwoods.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	public static Block logBlock = GameData.getBlockRegistry().getObject("ExtrabiomesXL:log2");
	
	public RealisticBiomeEBXLAutumnWoods(BiomeConfig config)
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLAutumnWoods(),
			new SurfaceEBXLAutumnWoods(topBlock, fillerBlock, false, null, 0f, 1.5f, 62f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigEBXL.weightEBXLAutumnWoods;
		this.generateVillages = ConfigEBXL.villageEBXLAutumnWoods;
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
        
        if (rand.nextInt(24) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            if (y22 < 100)
            {
                if (rand.nextBoolean()) {
                    (new WorldGenLog(logBlock, 1, Blocks.leaves, -1, 2 + rand.nextInt(2))).generate(world, rand, x22, y22, z22);
                }
                else {
                    (new WorldGenLog(Blocks.log, 0, Blocks.leaves, -1, 2 + rand.nextInt(2))).generate(world, rand, x22, y22, z22);
                }
            }
        }
        

//        for (int f23 = 0; f23 < 8f * strength; f23++)
//        {
//            int j15 = chunkX + rand.nextInt(16) + 8;
//            int j17 = rand.nextInt(128);
//            int j20 = chunkY + rand.nextInt(16) + 8;
//            (new WorldGenFlowers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})).generate(world, rand, j15, j17, j20);
//        }
//        
//        for (int l14 = 0; l14 < 12f * strength; l14++)
//        {
//            int l19 = chunkX + rand.nextInt(16) + 8;
//            int k22 = rand.nextInt(128);
//            int j24 = chunkY + rand.nextInt(16) + 8;
//            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
//        }
    }
}
