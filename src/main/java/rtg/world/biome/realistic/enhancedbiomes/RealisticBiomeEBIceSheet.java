package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBIceSheet;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBIceSheet;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBIceSheet extends RealisticBiomeEBBase
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
    
    private static Block ebTopBlock = EBAPI.ebGrassify(Blocks.ice, Blocks.ice);
    private static byte ebTopByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(Blocks.ice, Blocks.ice);
    private static byte ebFillByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(Blocks.ice, Blocks.ice);
    private static byte ebMixTopByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(Blocks.ice, Blocks.ice);
    private static byte ebMixFillByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(Blocks.packed_ice, Blocks.packed_ice);
    private static byte ebCliff1Byte = EBAPI.ebStonify((byte)0, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(Blocks.packed_ice, Blocks.packed_ice);
    private static byte ebCliff2Byte = EBAPI.ebStonify((byte)0, (byte)0);
    
	public RealisticBiomeEBIceSheet(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBIceSheet(),
			new SurfaceEBIceSheet(
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
		
		this.setRealisticBiomeName("EB Ice Sheet");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBIceSheet;
		this.generateVillages = ConfigEB.villageEBIceSheet;
        
    }
}
