package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSilkglades;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSilkglades;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSilkglades extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.silkglades;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSilkglades()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPSilkglades(),
			new SurfaceBOPSilkglades(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Silkglades");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigBOP.weightBOPsilkglades;
	}
}
