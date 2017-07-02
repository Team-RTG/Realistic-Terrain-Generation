package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.eccentricbiomes.SurfaceECCObsidianForest;
import rtg.world.gen.terrain.eccentricbiomes.TerrainECCObsidianForest;

public class RealisticBiomeECCObsidianForest extends RealisticBiomeECCBase
{

    public RealisticBiomeECCObsidianForest(BiomeGenBase eccBiome, BiomeConfig config)
    {
    
        super(config, 
            eccBiome, BiomeGenBase.river,
            new TerrainECCObsidianForest(),
            new SurfaceECCObsidianForest(config, eccBiome.topBlock, eccBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
