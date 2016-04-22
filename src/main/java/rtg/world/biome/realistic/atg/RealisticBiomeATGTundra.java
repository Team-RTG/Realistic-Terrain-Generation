package rtg.world.biome.realistic.atg;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.atg.SurfaceATGTundra;
import rtg.world.gen.terrain.atg.TerrainATGTundra;

public class RealisticBiomeATGTundra extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGTundra(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGTundra(),
			new SurfaceATGTundra(config, atgBiome.topBlock, atgBiome.fillerBlock, false, null, 0.45f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
