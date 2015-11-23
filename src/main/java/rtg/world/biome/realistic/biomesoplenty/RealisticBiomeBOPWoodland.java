package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWoodland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWoodland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPWoodland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.woodland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPWoodland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPWoodland(0f, 140f, 68f, 200f),
			new SurfaceBOPWoodland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Woodland");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPwoodland;
		this.generateVillages = ConfigBOP.villageBOPwoodland;
	}
}
