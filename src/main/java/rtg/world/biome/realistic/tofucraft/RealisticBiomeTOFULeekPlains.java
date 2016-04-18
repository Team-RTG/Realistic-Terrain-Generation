package rtg.world.biome.realistic.tofucraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.tofucraft.SurfaceTOFULeekPlains;
import rtg.world.gen.terrain.tofucraft.TerrainTOFULeekPlains;

public class RealisticBiomeTOFULeekPlains extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFULeekPlains(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFULeekPlains(),
			new SurfaceTOFULeekPlains(config, tofuBiome.topBlock, tofuBiome.fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
