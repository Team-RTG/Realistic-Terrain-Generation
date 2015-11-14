package rtg.world.biome.realistic.chromaticraft;

import rtg.config.chromaticraft.ConfigCC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.chromaticraft.SurfaceCCRainbowForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCRainbowForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCRainbowForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCRainbowForest(BiomeGenBase ccBiome)
	{
		super(
			ccBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainCCRainbowForest(),
			new SurfaceCCRainbowForest(ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone, 0.05f)
		);
		
		this.setRealisticBiomeName("CC Rainbow Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigCC.weightCCRainbowForest;
	}
}
