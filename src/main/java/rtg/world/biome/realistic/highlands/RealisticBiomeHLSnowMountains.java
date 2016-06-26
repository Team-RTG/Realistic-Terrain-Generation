package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLSnowMountains;
import rtg.world.gen.terrain.highlands.TerrainHLSnowMountains;

public class RealisticBiomeHLSnowMountains extends RealisticBiomeHLBase
{	
	public static BiomeGenBase hlBiome = HighlandsBiomes.snowMountains;

	public RealisticBiomeHLSnowMountains(BiomeConfig config)
	{
		super(config, 
			hlBiome, BiomeGenBase.frozenRiver,
			new TerrainHLSnowMountains(120f, 100f),
			new SurfaceHLSnowMountains(config, hlBiome.topBlock, hlBiome.fillerBlock, false, null, 1.2f)
		);

		this.generatesEmeralds = true;
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        this.noLakes = true;
        this.noWaterFeatures = true;

	}

}
