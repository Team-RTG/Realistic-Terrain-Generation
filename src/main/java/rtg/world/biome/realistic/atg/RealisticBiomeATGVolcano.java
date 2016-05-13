package rtg.world.biome.realistic.atg;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.gen.surface.atg.SurfaceATGVolcano;
import rtg.world.gen.terrain.atg.TerrainATGVolcano;

public class RealisticBiomeATGVolcano extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGVolcano(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGVolcano(),
			new SurfaceATGVolcano(config, atgBiome.topBlock, atgBiome.fillerBlock, true, Blocks.gravel, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.gravel, 0.08f)
		);
		this.waterSurfaceLakeChance = 0;
		this.lavaSurfaceLakeChance = 1;
		this.noLakes = true;
		this.noWaterFeatures = true;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
		
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
	}
}
