package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.eccentricbiomes.SurfaceECCFlamage;
import rtg.world.gen.terrain.eccentricbiomes.TerrainECCFlamage;

public class RealisticBiomeECCFlamage extends RealisticBiomeECCBase
{

    public RealisticBiomeECCFlamage(BiomeGenBase eccBiome, BiomeConfig config)
    {
    
        super(config, 
            eccBiome, BiomeGenBase.river,
            new TerrainECCFlamage(58f, 80f, 30f),
            new SurfaceECCFlamage(config, eccBiome.topBlock, eccBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
