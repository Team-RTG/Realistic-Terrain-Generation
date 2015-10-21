package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLavenderFields;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLavenderFields;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPLavenderFields extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.lavenderFields;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLavenderFields()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPLavenderFields(),
			new SurfaceBOPLavenderFields(topBlock, fillerBlock, false, null, 1.2f)
		);
		
		this.setRealisticBiomeName("BOP Lavender Fields");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigBOP.weightBOPlavenderFields;
	}
}
