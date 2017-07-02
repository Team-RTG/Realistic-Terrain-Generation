package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.eccentricbiomes.SurfaceECCDeathForest;
import rtg.world.gen.terrain.eccentricbiomes.TerrainECCDeathForest;

public class RealisticBiomeECCDeathForest extends RealisticBiomeECCBase
{

    public RealisticBiomeECCDeathForest(BiomeGenBase eccBiome, BiomeConfig config)
    {
    
        super(config, 
            eccBiome, BiomeGenBase.river,
            new TerrainECCDeathForest(58f, 80f, 30f),
            new SurfaceECCDeathForest(config, eccBiome.topBlock, eccBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
