package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.eccentricbiomes.SurfaceECCOcher;
import rtg.world.gen.terrain.eccentricbiomes.TerrainECCOcher;

public class RealisticBiomeECCOcher extends RealisticBiomeECCBase
{

    public RealisticBiomeECCOcher(BiomeGenBase eccBiome, BiomeConfig config)
    {
    
        super(config, 
            eccBiome, BiomeGenBase.river,
            new TerrainECCOcher(),
            new SurfaceECCOcher(config, eccBiome.topBlock, eccBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
