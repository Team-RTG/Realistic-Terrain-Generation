package rtg.world.biome.realistic.tofucraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuExtremeHillsEdge;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuExtremeHillsEdge;

public class RealisticBiomeTOFUTofuExtremeHillsEdge extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuExtremeHillsEdge(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuExtremeHillsEdge(10f, 20f, 68f, 200f),
			new SurfaceTOFUTofuExtremeHillsEdge(config, tofuBiome.topBlock, tofuBiome.fillerBlock, tofuBiome.topBlock, tofuBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
		);
		this.waterSurfaceLakeChance = 0;
		this.noLakes = true;
		this.noWaterFeatures = true;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
