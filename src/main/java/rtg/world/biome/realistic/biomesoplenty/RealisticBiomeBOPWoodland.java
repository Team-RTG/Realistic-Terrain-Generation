package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWoodland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWoodland;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPWoodland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.woodland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPWoodland(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPWoodland(0f, 80f, 68f, 120f),
			new SurfaceBOPWoodland(topBlock, fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigBOP.weightBOPWoodland;
		this.generateVillages = ConfigBOP.villageBOPWoodland;
	}
}
