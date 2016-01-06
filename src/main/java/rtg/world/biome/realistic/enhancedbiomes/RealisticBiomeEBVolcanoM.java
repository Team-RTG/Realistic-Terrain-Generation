package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBVolcanoM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBVolcanoM;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBVolcanoM extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.CHERT, (byte)0),
        EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.CHERT, (byte)0),
        EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.ANDISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.ANDISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebMixTopByte = EBAPI.ebStonify(EBAPI.BASALT, (byte)0);
    private static Block ebMixFillBlock = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebMixFillByte = EBAPI.ebStonify(EBAPI.BASALT, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.BASALT, (byte)0);
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.BASALT, (byte)0);
    
    private static SurfaceBase surface = new SurfaceEBVolcanoM(
        ebTopBlock, //Block top 
        ebTopByte, //byte topByte
        ebFillBlock, //Block filler, 
        ebFillByte, //byte fillerByte
        ebMixTopBlock, //Block mixTop, 
        ebMixTopByte, //byte mixTopByte, 
        ebMixFillBlock, //Block mixFill, 
        ebMixFillByte, //byte mixFillByte, 
        ebCliff1Block, //Block cliff1, 
        ebCliff1Byte, //byte cliff1Byte, 
        ebCliff2Block, //Block cliff2, 
        ebCliff2Byte, //byte cliff2Byte, 
        80f, //float mixWidth, 
        -0.15f, //float mixHeight, 
        10f, //float smallWidth, 
        0.5f //float smallStrength
    );
    
    private static SurfaceBase riverSurface = new SurfaceRiverOasis();
    
    public RealisticBiomeEBVolcanoM(BiomeGenBase ebBiome)
    {
        super(
            ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainEBVolcanoM(),
            surface
        );
        
        this.setRealisticBiomeName("EB Volcano M");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigEB.weightEBVolcanoM;
        this.generateVillages = ConfigEB.villageEBVolcanoM;
        
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
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
    public void rMapGen(Block[] blocks, byte[] metadata, World world, WorldChunkManagerRTG cmr, Random mapRand, int baseX, int baseY,
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
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        riverSurface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
