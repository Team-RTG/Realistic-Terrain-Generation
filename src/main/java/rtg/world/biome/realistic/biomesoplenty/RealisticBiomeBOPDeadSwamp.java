package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeadSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeadSwamp;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPDeadSwamp extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.deadSwamp;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPDeadSwamp()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPDeadSwamp(),
			new SurfaceBOPDeadSwamp(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Dead Swamp");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPDeadSwamp;
		this.generateVillages = ConfigBOP.villageBOPDeadSwamp;
	}
}
