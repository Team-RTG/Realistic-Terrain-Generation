package rtg.world.biome.realistic.chromaticraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.chromaticraft.SurfaceCCEnderForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCEnderForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCEnderForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCEnderForest(BiomeGenBase ccBiome, BiomeConfig config)
	{
		super(config, 
			ccBiome, BiomeGenBase.river,
			new TerrainCCEnderForest(),
			new SurfaceCCEnderForest(config, ccBiome.topBlock.getBlock(), ccBiome.fillerBlock.getBlock(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock.getBlock(), 0.05f)
		);
	}
}
