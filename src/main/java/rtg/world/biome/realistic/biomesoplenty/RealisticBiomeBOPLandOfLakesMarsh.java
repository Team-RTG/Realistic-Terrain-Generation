package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLandOfLakesMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLandOfLakesMarsh;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPLandOfLakesMarsh extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.landOfLakesMarsh;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLandOfLakesMarsh()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPLandOfLakesMarsh(),
			new SurfaceBOPLandOfLakesMarsh(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Land of Lakes Marsh");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPlandOfLakesMarsh;
		this.generateVillages = ConfigBOP.villageBOPlandOfLakesMarsh;
	}
}
