package rtg.world.biome.realistic.chromaticraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.chromaticraft.SurfaceCCEnderForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCEnderForest;

public class RealisticBiomeCCEnderForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCEnderForest(BiomeGenBase ccBiome, BiomeConfig config)
	{
		super(config, 
			ccBiome, BiomeGenBase.river,
			new TerrainCCEnderForest(),
			new SurfaceCCEnderForest(config, ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock, 0.05f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
