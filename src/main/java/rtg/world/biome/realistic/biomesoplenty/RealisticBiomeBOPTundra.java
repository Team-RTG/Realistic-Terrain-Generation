package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTundra;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTundra;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPTundra extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.tundra;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPTundra()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainBOPTundra(),
			new SurfaceBOPTundra(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Tundra");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPTundra;
		this.generateVillages = ConfigBOP.villageBOPTundra;
	}
}
