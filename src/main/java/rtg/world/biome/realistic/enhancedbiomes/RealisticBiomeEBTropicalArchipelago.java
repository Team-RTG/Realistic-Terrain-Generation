package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBTropicalArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBTropicalArchipelago;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBGrassyArchipelago;

public class RealisticBiomeEBTropicalArchipelago extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.GABBRO, (byte)0),
        EBAPI.ebStonify(EBAPI.BASALT, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.GABBRO, (byte)0),
        EBAPI.ebStonify(EBAPI.BASALT, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.ANDISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.ANDISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.ANDISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.ANDISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.GABBRO, (byte)0);
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.GABBRO, (byte)0);
    
	public RealisticBiomeEBTropicalArchipelago(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBGrassyArchipelago(80f, 30f, 0f),
			new SurfaceEBTropicalArchipelago(
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
                1f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                2f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.config = config;
        
    }
}
