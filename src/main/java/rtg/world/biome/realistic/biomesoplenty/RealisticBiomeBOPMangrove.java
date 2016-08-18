package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMangrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMangrove;

public class RealisticBiomeBOPMangrove extends RealisticBiomeBOPBase {

	public static Biome biome = BOPBiomes.mangrove.get();
	public static Biome river = Biomes.RIVER;
	
	public RealisticBiomeBOPMangrove(BiomeConfig config)
	{
		super(config, biome, river,
			new TerrainBOPMangrove(),
			new SurfaceBOPMangrove(config, biome.topBlock, biome.fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
