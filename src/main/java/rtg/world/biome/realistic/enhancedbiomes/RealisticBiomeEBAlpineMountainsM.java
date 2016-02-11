package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountainsM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountainsM;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAlpineMountainsM extends RealisticBiomeEBBase
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
    
    public static Block ebTopBlock = Blocks.snow;
    public static byte ebTopByte = (byte)0;
    public static Block ebFillBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    public static byte ebFillByte = EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0);
    
	public RealisticBiomeEBAlpineMountainsM(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeGenBase.river,
			new TerrainEBAlpineMountainsM(),
			new SurfaceEBAlpineMountainsM(config, ebTopBlock, ebTopByte, ebFillBlock, ebFillByte, false, null, 0.45f)
		);
		
		this.config = config;
        
    }
}
