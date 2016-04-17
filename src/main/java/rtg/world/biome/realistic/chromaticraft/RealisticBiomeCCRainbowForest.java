package rtg.world.biome.realistic.chromaticraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.chromaticraft.SurfaceCCRainbowForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCRainbowForest;

public class RealisticBiomeCCRainbowForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCRainbowForest(BiomeGenBase ccBiome, BiomeConfig config)
	{
		super(config, 
			ccBiome, BiomeGenBase.river,
			new TerrainCCRainbowForest(),
			new SurfaceCCRainbowForest(config, ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock, 0.05f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
