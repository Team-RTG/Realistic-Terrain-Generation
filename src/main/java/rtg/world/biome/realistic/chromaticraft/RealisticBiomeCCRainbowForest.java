package rtg.world.biome.realistic.chromaticraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.chromaticraft.ConfigCC;
import rtg.world.gen.surface.chromaticraft.SurfaceCCRainbowForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCRainbowForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCRainbowForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCRainbowForest(BiomeGenBase ccBiome, BiomeConfig config)
	{
		super(
			ccBiome, BiomeGenBase.river,
			new TerrainCCRainbowForest(),
			new SurfaceCCRainbowForest(ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock, 0.05f)
		);
		
		this.config = config;
	}
}
