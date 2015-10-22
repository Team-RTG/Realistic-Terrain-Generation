package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHeathland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPHeathland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPHeathland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.heathland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPHeathland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainBOPHeathland(300f),
			new SurfaceBOPHeathland()
		);
		
		this.setRealisticBiomeName("BOP Heathland");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigBOP.weightBOPheathland;
	}
}
