package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSacredSprings;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSacredSprings;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.sacredSprings;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSacredSprings()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPSacredSprings(),
			new SurfaceBOPSacredSprings(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Sacred Springs");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPSacredSprings;
		this.generateVillages = ConfigBOP.villageBOPSacredSprings;
	}
}
