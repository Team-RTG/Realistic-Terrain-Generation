package rtg.world.biome.realistic.chromaticraft;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.chromaticraft.SurfaceCCLuminousCliffs;
import rtg.world.gen.terrain.chromaticraft.TerrainCCLuminousCliffs;

public class RealisticBiomeCCLuminousCliffs extends RealisticBiomeCCBase
{
	public RealisticBiomeCCLuminousCliffs(BiomeGenBase ccBiome, BiomeConfig config)
	{
		super(config,
			ccBiome, BiomeGenBase.river,
			new TerrainCCLuminousCliffs(350f, 100f, 63f,70f),
			new SurfaceCCLuminousCliffs(config, ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 2.5f, 80f, 65f, 2.5f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
