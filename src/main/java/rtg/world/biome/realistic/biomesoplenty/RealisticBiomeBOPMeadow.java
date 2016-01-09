package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMeadow;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMeadow;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMeadow extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.meadow;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMeadow()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPMeadow(),
			new SurfaceBOPMeadow(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Meadow");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPMeadow;
		this.generateVillages = ConfigBOP.villageBOPMeadow;
	}
}
