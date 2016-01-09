package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBayou;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBayou;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.bayou;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBayou()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPBayou(),
			new SurfaceBOPBayou(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Bayou");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPBayou;
		this.generateVillages = ConfigBOP.villageBOPBayou;
	}
}
