package rtg.world.biome.realistic.tofucraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuExtremeHills;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuExtremeHills;

public class RealisticBiomeTOFUTofuExtremeHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuExtremeHills(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuExtremeHills(10f, 120f, 68f, 200f),
			new SurfaceTOFUTofuExtremeHills(config, tofuBiome.topBlock, tofuBiome.fillerBlock, tofuBiome.topBlock, tofuBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
