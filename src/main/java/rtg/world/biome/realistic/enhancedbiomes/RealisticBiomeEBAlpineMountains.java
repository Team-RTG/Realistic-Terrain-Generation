package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biomes.enhancedbiomes.config.BiomeConfigEBAlpineMountains;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountains;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAlpineMountains extends RealisticBiomeEBBase
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
    public static byte ebFillByte = EBAPI.ebStonify(EBAPI.CHERT, (byte)0);
    
	public RealisticBiomeEBAlpineMountains(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineMountains(),
			new SurfaceEBAlpineMountains(ebTopBlock, ebFillBlock, ebFillByte, false, null, 0.45f)
		);
		
		this.biomeConfig = new BiomeConfigEBAlpineMountains();
		this.biomeWeight = ConfigEB.weightEBAlpineMountains;
		this.generateVillages = ConfigEB.villageEBAlpineMountains;
	}
}
