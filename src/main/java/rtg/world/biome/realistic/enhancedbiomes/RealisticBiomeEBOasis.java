package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBOasis;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBOasis;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBOasis extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{EnhancedBiomesBlocks.stoneEB, EnhancedBiomesBlocks.stoneEB};
    public static byte[] ebDominantStoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    public static Block[] ebDominantCobblestoneBlock = new Block[]{EnhancedBiomesBlocks.stoneCobbleEB, EnhancedBiomesBlocks.stoneCobbleEB};
    public static byte[] ebDominantCobblestoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    
	public RealisticBiomeEBOasis(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBOasis(),
			new SurfaceEBOasis(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Oasis");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBOasis;
		this.generateVillages = ConfigEB.villageEBOasis;
        
    }
}
