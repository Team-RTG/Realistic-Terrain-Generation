package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.eccentricbiomes.SurfaceECCSnowyDesert;
import rtg.world.gen.terrain.eccentricbiomes.TerrainECCSnowyDesert;

public class RealisticBiomeECCSnowyDesert extends RealisticBiomeECCBase
{

    public RealisticBiomeECCSnowyDesert(BiomeGenBase eccBiome, BiomeConfig config)
    {
    
        super(config, 
            eccBiome, BiomeGenBase.frozenRiver,
            new TerrainECCSnowyDesert(),
            new SurfaceECCSnowyDesert(config, eccBiome.topBlock, eccBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
