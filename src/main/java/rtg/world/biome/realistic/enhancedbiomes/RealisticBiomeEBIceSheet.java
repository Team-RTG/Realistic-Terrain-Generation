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
    public static Block[] ebDominantStoneBlock = new Block[]{EnhancedBiomesBlocks.stoneEB, EnhancedBiomesBlocks.stoneEB};
    public static byte[] ebDominantStoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    public static Block[] ebDominantCobblestoneBlock = new Block[]{EnhancedBiomesBlocks.stoneCobbleEB, EnhancedBiomesBlocks.stoneCobbleEB};
    public static byte[] ebDominantCobblestoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    
	public RealisticBiomeEBIceSheet(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBIceSheet(),
			new SurfaceEBIceSheet(Blocks.ice, Blocks.packed_ice)
		);
		
		this.setRealisticBiomeName("EB Ice Sheet");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBIceSheet;
		this.generateVillages = ConfigEB.villageEBIceSheet;
        
    }
}
