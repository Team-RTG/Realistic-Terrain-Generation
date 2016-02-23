package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPVolcano;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPVolcano;

import java.util.Random;

public class RealisticBiomeBOPVolcano extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.volcanic_island.get();
	
    private static Block bopTopBlock = Block.getBlockFromName("BiomesOPlenty:ashStone");
    private static byte bopTopByte = (byte)0;
    private static Block bopFillBlock = Block.getBlockFromName("BiomesOPlenty:ashStone");
    private static byte bopFillByte = (byte)0;
    private static Block bopMixTopBlock = Block.getBlockFromName("BiomesOPlenty:ash");
    private static byte bopMixTopByte = (byte)0;
    private static Block bopMixFillBlock = Block.getBlockFromName("BiomesOPlenty:ashStone");
    private static byte bopMixFillByte = (byte)0;
    private static Block bopCliff1Block = Block.getBlockFromName("BiomesOPlenty:ashStone");
    private static byte bopCliff1Byte = (byte)0;
    private static Block bopCliff2Block = Block.getBlockFromName("BiomesOPlenty:ash");
    private static byte bopCliff2Byte = (byte)0;

	public RealisticBiomeBOPVolcano(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPVolcano(),
			new SurfaceBOPVolcano(config, 
		        bopTopBlock, //Block top 
		        bopTopByte, //byte topByte
		        bopFillBlock, //Block filler, 
		        bopFillByte, //byte fillerByte
		        bopMixTopBlock, //Block mixTop, 
		        bopMixTopByte, //byte mixTopByte, 
		        bopMixFillBlock, //Block mixFill, 
		        bopMixFillByte, //byte mixFillByte,
		        80f, //float mixWidth, 
		        -0.15f, //float mixHeight, 
		        10f, //float smallWidth, 
		        0.5f //float smallStrength
		    )
		);
		
		this.waterSurfaceLakeChance = 0;
		this.lavaSurfaceLakeChance = 1;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        for (int l14 = 0; l14 < 15; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(3) == 0)
            {
                (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, new BlockPos(l19, k22, j24));
            }
            else
            {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
            }
        }
    }
    
    @Override
    public void rMapGen(ChunkPrimer primer, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY,
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
                
                WorldGenVolcano.build(primer, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
    
    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        this.getSurface().paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        
        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
