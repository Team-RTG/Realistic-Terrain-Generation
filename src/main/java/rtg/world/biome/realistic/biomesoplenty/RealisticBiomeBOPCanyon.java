package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCanyon;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCanyon;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCanyon extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.canyon;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCanyon()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPCanyon(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceBOPCanyon(topBlock, fillerBlock, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("BOP Canyon");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPcanyon;
	}
}
