package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOriginValley;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOriginValley;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPOriginValley extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.originValley;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPOriginValley()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPOriginValley(63f, 80f, 38f),
			new SurfaceBOPOriginValley(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Origin Valley");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPOriginValley;
		this.generateVillages = ConfigBOP.villageBOPOriginValley;
	}
}
