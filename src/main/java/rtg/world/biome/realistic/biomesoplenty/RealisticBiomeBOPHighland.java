package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHighland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPHighland;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPHighland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.highland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPHighland(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPHighland(),
			new SurfaceBOPHighland(topBlock, fillerBlock)
		);
		
		this.config = config;
		this.biomeWeight = ConfigBOP.weightBOPHighland;
		this.generateVillages = ConfigBOP.villageBOPHighland;
		this.generatesEmeralds = true;
	}
}
