package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMangrove;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPMangrove extends RealisticBiomeBOPBase {

	public static Biome biome = BOPBiomes.mangrove.get();
	public static Biome river = Biomes.RIVER;
	
	public RealisticBiomeBOPMangrove(BiomeConfig config)
	{
		super(config, biome, river,
			new SurfaceBOPMangrove(config, biome.topBlock, biome.fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}

	@Override
	public TerrainBase initTerrain() {

		return new TerrainBOPMangrove();
	}

	public class TerrainBOPMangrove extends TerrainBase
	{
		public TerrainBOPMangrove()
		{
		}

		@Override
		public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
		{
			return terrainBeach(x, y, simplex, river, 180f, 35f, 60f);
		}
	}
}
