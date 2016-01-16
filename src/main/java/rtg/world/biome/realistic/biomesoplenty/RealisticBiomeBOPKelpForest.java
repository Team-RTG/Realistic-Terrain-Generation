package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPKelpForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPKelpForest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPKelpForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.kelpForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPKelpForest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPKelpForest(false, -10f, 0f, 0f, 0f, 30f),
			new SurfaceBOPKelpForest(topBlock, fillerBlock)
		);
		
		this.config = config;
		this.biomeWeight = ConfigBOP.weightBOPKelpForest;
		this.generateVillages = ConfigBOP.villageBOPKelpForest;
	}
}
