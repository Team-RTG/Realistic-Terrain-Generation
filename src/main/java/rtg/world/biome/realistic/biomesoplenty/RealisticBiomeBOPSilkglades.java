package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSilkglades;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSilkglades;

public class RealisticBiomeBOPSilkglades extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.alps.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPSilkglades(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSilkglades(),
			new SurfaceBOPSilkglades(config, topBlock, fillerBlock)
		);
	}
}
