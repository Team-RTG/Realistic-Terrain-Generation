package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAspenHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAspenHills;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAspenHills extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.SLATE, (byte)0),
        EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.SLATE, (byte)0),
        EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0)
    };
    
    public static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    public static byte ebTopByte = EBAPI.ebGrassify(EBAPI.ALFISOL, (byte)0);
    public static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    public static byte ebFillByte = EBAPI.ebGrassify(EBAPI.ALFISOL, (byte)0);
    
	public RealisticBiomeEBAspenHills(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeGenBase.river,
			new TerrainEBAspenHills(230f, 120f, 0f),
			new SurfaceEBAspenHills(ebTopBlock, ebTopByte, ebFillBlock, ebFillByte, false, null, 0.95f)
		);
		
		this.config = config;
        
    }
}
