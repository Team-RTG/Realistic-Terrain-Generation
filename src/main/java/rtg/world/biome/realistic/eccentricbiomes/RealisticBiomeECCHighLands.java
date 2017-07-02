package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.eccentricbiomes.SurfaceECCHighLands;
import rtg.world.gen.terrain.eccentricbiomes.TerrainECCHighLands;

public class RealisticBiomeECCHighLands extends RealisticBiomeECCBase
{

    public RealisticBiomeECCHighLands(BiomeGenBase eccBiome, BiomeConfig config)
    {
    
        super(config, 
            eccBiome, BiomeGenBase.river,
            new TerrainECCHighLands(),
            new SurfaceECCHighLands(config, eccBiome.topBlock, eccBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
