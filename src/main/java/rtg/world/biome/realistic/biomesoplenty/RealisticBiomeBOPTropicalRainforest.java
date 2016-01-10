package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropicalRainforest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPTropicalRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.tropicalRainforest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPTropicalRainforest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPTropicalRainforest(0f, 140f, 68f, 200f),
			new SurfaceBOPTropicalRainforest(topBlock, fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigBOP.weightBOPTropicalRainforest;
		this.generateVillages = ConfigBOP.villageBOPTropicalRainforest;
	}
}
