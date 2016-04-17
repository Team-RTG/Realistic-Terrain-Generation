package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.thaumcraft.SurfaceTCMagicalForest;
import rtg.world.gen.terrain.thaumcraft.TerrainTCMagicalForest;

public class RealisticBiomeTCMagicalForest extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCMagicalForest(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(config, 
			tcBiome, BiomeGenBase.river,
			new TerrainTCMagicalForest(),
			new SurfaceTCMagicalForest(config, tcBiome.topBlock, tcBiome.fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
