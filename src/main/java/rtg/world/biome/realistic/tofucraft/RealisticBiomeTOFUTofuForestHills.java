package rtg.world.biome.realistic.tofucraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuForestHills;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuForestHills;

public class RealisticBiomeTOFUTofuForestHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuForestHills(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuForestHills(),
			new SurfaceTOFUTofuForestHills(config, tofuBiome.topBlock, tofuBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, tofuBiome.topBlock, (byte)0, 0.15f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
