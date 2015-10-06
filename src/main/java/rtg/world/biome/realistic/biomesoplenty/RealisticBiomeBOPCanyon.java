package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlps;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPAlps;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCanyon extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.alps;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCanyon()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
			new TerrainBOPAlps(),
			new SurfaceBOPAlps(topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("BOP Alps");
		this.biomeWeight = ConfigBOP.weightBOPalps;
	}
}
