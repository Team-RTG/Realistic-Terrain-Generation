package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCanyonRavine;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCanyonRavine;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCanyonRavine extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.canyonRavine;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCanyonRavine()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPCanyonRavine(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceBOPCanyonRavine(topBlock, fillerBlock, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("BOP Canyon Ravine");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPcanyonRavine;
	}
}
