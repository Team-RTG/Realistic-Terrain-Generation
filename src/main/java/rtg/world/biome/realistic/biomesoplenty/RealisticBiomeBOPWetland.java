package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWetland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWetland;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPWetland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.wetland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPWetland(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPWetland(),
			new SurfaceBOPWetland(topBlock, fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigBOP.weightBOPWetland;
		this.generateVillages = ConfigBOP.villageBOPWetland;
	}
}
