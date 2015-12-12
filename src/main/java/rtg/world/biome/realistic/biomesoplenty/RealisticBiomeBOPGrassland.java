package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrassland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGrassland;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPGrassland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.grassland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPGrassland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPGrassland(),
			new SurfaceBOPGrassland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Grassland");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPGrassland;
		this.generateVillages = ConfigBOP.villageBOPGrassland;
	}
}
