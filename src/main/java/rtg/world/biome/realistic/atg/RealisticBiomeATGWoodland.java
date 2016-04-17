package rtg.world.biome.realistic.atg;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.atg.SurfaceATGWoodland;
import rtg.world.gen.terrain.atg.TerrainATGWoodland;

public class RealisticBiomeATGWoodland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGWoodland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGWoodland(),
			new SurfaceATGWoodland(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
