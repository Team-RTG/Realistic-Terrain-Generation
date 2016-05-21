package rtg.world.biome.realistic.tofucraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuRiver;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuRiver;

public class RealisticBiomeTOFUTofuRiver extends RealisticBiomeTOFUBase
{
	public RealisticBiomeTOFUTofuRiver(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuRiver(),
			new SurfaceTOFUTofuRiver(config)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
