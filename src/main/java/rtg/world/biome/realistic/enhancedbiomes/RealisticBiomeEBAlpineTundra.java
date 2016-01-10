package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biomes.BiomeConfig;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineTundra;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineTundra;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAlpineTundra extends RealisticBiomeEBBase
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
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone);
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0);
    
	public RealisticBiomeEBAlpineTundra(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineTundra(),
			new SurfaceEBAlpineTundra(
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
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigEB.weightEBAlpineTundra;
		this.generateVillages = ConfigEB.villageEBAlpineTundra;
		
        this.generatesEmeralds = true;
        this.emeraldEmeraldBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.oreEmeraldEB, Blocks.emerald_ore);
        this.emeraldEmeraldMeta = EBAPI.ebStonify(EBAPI.CHERT, (byte)0);
        this.emeraldStoneBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
        this.emeraldStoneMeta = EBAPI.ebStonify(EBAPI.CHERT, (byte)0);
    }
}
