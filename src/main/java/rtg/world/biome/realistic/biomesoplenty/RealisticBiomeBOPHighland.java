package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHighland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPHighland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPHighland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.highland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPHighland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPHighland(0f, 140f, 68f, 150f),
			new SurfaceBOPHighland(topBlock, fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
		
		this.setRealisticBiomeName("BOP Highland");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPhighland;
	}
}
