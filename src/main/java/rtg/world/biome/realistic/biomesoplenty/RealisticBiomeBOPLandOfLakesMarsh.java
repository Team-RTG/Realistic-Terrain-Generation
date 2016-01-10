package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLandOfLakesMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLandOfLakesMarsh;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPLandOfLakesMarsh extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.landOfLakesMarsh;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLandOfLakesMarsh(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPLandOfLakesMarsh(),
			new SurfaceBOPLandOfLakesMarsh(topBlock, fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigBOP.weightBOPLandOfLakesMarsh;
		this.generateVillages = ConfigBOP.villageBOPLandOfLakesMarsh;
	}
}
