package rtg.world.biome.realistic.tofucraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuPlains;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuPlains;

public class RealisticBiomeTOFUTofuPlains extends RealisticBiomeTOFUBase
{
	public RealisticBiomeTOFUTofuPlains(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuPlains(),
			new SurfaceTOFUTofuPlains(config, tofuBiome.topBlock, tofuBiome.fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
