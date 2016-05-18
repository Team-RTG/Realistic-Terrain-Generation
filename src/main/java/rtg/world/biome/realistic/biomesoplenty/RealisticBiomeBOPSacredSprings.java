package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSacredSprings;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSacredSprings;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.sacredSprings;

	public RealisticBiomeBOPSacredSprings(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSacredSprings(150f, 30f, 68f),
			new SurfaceBOPSacredSprings(config, bopBiome.topBlock, bopBiome.fillerBlock)
		);
        noWaterFeatures = true;
        this.waterSurfaceLakeChance = 2;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
