package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCanyon;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCanyon;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPCanyon extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.canyon;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCanyon()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPCanyon(),
			new SurfaceBOPCanyon(topBlock, fillerBlock, topBlock, 20f, 0.2f)
		);
		
		this.setRealisticBiomeName("BOP Canyon");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPCanyon;
		this.generateVillages = ConfigBOP.villageBOPCanyon;
		
		this.waterLakeFrequency = 0;
		this.lavaLakeFrequency = 0;
	}
}
