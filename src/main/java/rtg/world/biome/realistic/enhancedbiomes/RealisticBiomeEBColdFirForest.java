package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBColdFirForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBColdFirForest;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBColdFirForest extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{EnhancedBiomesBlocks.stoneEB, EnhancedBiomesBlocks.stoneEB};
    public static byte[] ebDominantStoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    public static Block[] ebDominantCobblestoneBlock = new Block[]{EnhancedBiomesBlocks.stoneCobbleEB, EnhancedBiomesBlocks.stoneCobbleEB};
    public static byte[] ebDominantCobblestoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    
	public RealisticBiomeEBColdFirForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBColdFirForest(160f, 80f, 60f),
			new SurfaceEBColdFirForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Cold Fir Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBColdFirForest;
		this.generateVillages = ConfigEB.villageEBColdFirForest;
        
    }
}
