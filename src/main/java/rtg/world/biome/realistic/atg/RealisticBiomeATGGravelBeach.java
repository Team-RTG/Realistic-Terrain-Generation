package rtg.world.biome.realistic.atg;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.atg.SurfaceATGGravelBeach;
import rtg.world.gen.terrain.atg.TerrainATGGravelBeach;

public class RealisticBiomeATGGravelBeach extends RealisticBiomeATGBase
{
	public RealisticBiomeATGGravelBeach(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGGravelBeach(),
			new SurfaceATGGravelBeach(config, atgBiome.topBlock, atgBiome.fillerBlock, atgBiome.topBlock, atgBiome.fillerBlock, (byte)0, 1)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
