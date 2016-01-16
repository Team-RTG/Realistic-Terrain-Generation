package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyWastelands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyWastelands;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBSnowyWastelands extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.DACITE, (byte)0),
        EBAPI.ebStonify(EBAPI.RHYOLITE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.DACITE, (byte)0),
        EBAPI.ebStonify(EBAPI.RHYOLITE, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.GELISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.GELISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.GELISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.GELISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(Blocks.stone, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify((byte)0, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(Blocks.cobblestone, Blocks.cobblestone);
    private static byte ebCliff2Byte = EBAPI.ebStonify((byte)0, (byte)0);
    
	public RealisticBiomeEBSnowyWastelands(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
			new TerrainEBSnowyWastelands(),
			new SurfaceEBSnowyWastelands(
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
            )
		);
		
		this.config = config;
		this.biomeWeight = ConfigEB.weightEBSnowyWastelands;
		this.generateVillages = ConfigEB.villageEBSnowyWastelands;
        
    }
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        /*for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 95 && (k1 < 64 || rand.nextInt(8) == 0))
            {
                (new WorldGenBlob(Blocks.packed_ice, (byte)0, rand.nextInt(1))).generate(world, rand, i1, k1, j1, true);
            }
        }*/
    }
}
