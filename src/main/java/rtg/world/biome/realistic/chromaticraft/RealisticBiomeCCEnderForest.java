package rtg.world.biome.realistic.chromaticraft;

import rtg.config.chromaticraft.ConfigCC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.chromaticraft.SurfaceCCEnderForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCEnderForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCEnderForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCEnderForest(BiomeGenBase ccBiome)
	{
		super(
			ccBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainCCEnderForest(),
			new SurfaceCCEnderForest(ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone, 0.05f)
		);
		
		this.setRealisticBiomeName("CC Ender Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigCC.weightCCEnderForest;
	}
}
