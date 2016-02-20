package rtg.world.biome.realistic.chromaticraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.chromaticraft.SurfaceCCRainbowForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCRainbowForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCRainbowForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCRainbowForest(BiomeGenBase ccBiome, BiomeConfig config)
	{
		super(config, 
			ccBiome, BiomeGenBase.river,
			new TerrainCCRainbowForest(),
			new SurfaceCCRainbowForest(config, ccBiome.topBlock.getBlock(), ccBiome.fillerBlock.getBlock(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock.getBlock(), 0.05f)
		);
	}
}
