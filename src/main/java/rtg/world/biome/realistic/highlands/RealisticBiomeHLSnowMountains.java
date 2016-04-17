package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLSnowMountains;
import rtg.world.gen.terrain.highlands.TerrainHLSnowMountains;

public class RealisticBiomeHLSnowMountains extends RealisticBiomeHLBase
{	
	public static BiomeGenBase hlBiome = HighlandsBiomes.snowMountains;
	
	public static Block topBlock = hlBiome.topBlock;
	public static Block fillerBlock = hlBiome.fillerBlock;
	
	public RealisticBiomeHLSnowMountains(BiomeConfig config)
	{
		super(config, 
			hlBiome, BiomeGenBase.frozenRiver,
			new TerrainHLSnowMountains(120f, 100f),
			new SurfaceHLSnowMountains(config, topBlock, fillerBlock, false, null, 1.2f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}

    @Override
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // no rivers or lakes
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }

}
