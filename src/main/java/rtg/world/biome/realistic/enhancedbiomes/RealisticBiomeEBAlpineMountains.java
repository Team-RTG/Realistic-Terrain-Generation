package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountains;
import enhancedbiomes.api.EBStoneMeta;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAlpineMountains extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{EnhancedBiomesBlocks.stoneEB, EnhancedBiomesBlocks.stoneEB};
    public static byte[] ebDominantStoneMeta = new byte[]{EBStoneMeta.CHERT, EBStoneMeta.LIMESTONE};
    public static Block[] ebDominantCobblestoneBlock = new Block[]{EnhancedBiomesBlocks.stoneCobbleEB, EnhancedBiomesBlocks.stoneCobbleEB};
    public static byte[] ebDominantCobblestoneMeta = new byte[]{EBStoneMeta.CHERT, EBStoneMeta.LIMESTONE};
    
    public static Block ebTopBlock = Blocks.snow;
    public static byte ebTopByte = (byte)0;
    public static Block ebFillerBlock = EnhancedBiomesBlocks.stoneEB;
    public static byte ebFillerByte = EBStoneMeta.CHERT;
    
	public RealisticBiomeEBAlpineMountains(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineMountains(),
			new SurfaceEBAlpineMountains(ebTopBlock, ebFillerBlock, ebFillerByte, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Mountains");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAlpineMountains;
		this.generateVillages = ConfigEB.villageEBAlpineMountains;
	}
}
