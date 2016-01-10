package rtg.world.biome.realistic.chromaticraft;

import rtg.api.biomes.chromaticraft.config.BiomeConfigCCEnderForest;
import rtg.config.chromaticraft.ConfigCC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.chromaticraft.SurfaceCCEnderForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCEnderForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCEnderForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCEnderForest(BiomeGenBase ccBiome)
	{
		super(
			ccBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainCCEnderForest(),
			new SurfaceCCEnderForest(ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock, 0.05f)
		);
		
		this.biomeConfig = new BiomeConfigCCEnderForest();
		this.biomeWeight = ConfigCC.weightCCEnderForest;
		this.generateVillages = ConfigCC.villageCCEnderForest;
	}
}
