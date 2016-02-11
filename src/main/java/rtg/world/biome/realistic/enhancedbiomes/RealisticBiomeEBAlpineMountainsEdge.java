package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountainsEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountainsEdge;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAlpineMountainsEdge extends RealisticBiomeEBBase
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
    
    public static Block ebTopBlock = Blocks.grass;
    public static byte ebTopByte = (byte)0;
    public static Block ebFillBlock = Blocks.dirt;
    public static byte ebFillByte = (byte)0;
    
	public RealisticBiomeEBAlpineMountainsEdge(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeGenBase.river,
			new TerrainEBAlpineMountainsEdge(300f, 80f, 30f),
			new SurfaceEBAlpineMountainsEdge(config, ebTopBlock, ebFillBlock, false, null, 0.45f)
		);
		
		this.config = config;
        
    }
}
