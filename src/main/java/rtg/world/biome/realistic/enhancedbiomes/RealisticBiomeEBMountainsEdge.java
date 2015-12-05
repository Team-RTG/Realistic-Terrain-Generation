package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMountainsEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMountainsEdge;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBMountainsEdge extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{EnhancedBiomesBlocks.stoneEB, EnhancedBiomesBlocks.stoneEB};
    public static byte[] ebDominantStoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    public static Block[] ebDominantCobblestoneBlock = new Block[]{EnhancedBiomesBlocks.stoneCobbleEB, EnhancedBiomesBlocks.stoneCobbleEB};
    public static byte[] ebDominantCobblestoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    
	public RealisticBiomeEBMountainsEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBMountainsEdge(200f, 100f, 0f),
			new SurfaceEBMountainsEdge(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Mountains Edge");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBMountainsEdge;
		this.generateVillages = ConfigEB.villageEBMountainsEdge;
        
    }
}
