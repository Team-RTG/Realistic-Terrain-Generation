package rtg.world.biome.realistic.vampirism;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vampirism.SurfaceVAMPVampireForest;
import rtg.world.gen.terrain.vampirism.TerrainVAMPVampireForest;

public class RealisticBiomeVAMPVampireForest extends RealisticBiomeVAMPBase
{
    
    public RealisticBiomeVAMPVampireForest(BiomeGenBase vampBiome, BiomeConfig config)
    {
    
        super(config,
            vampBiome, BiomeGenBase.river,
            new TerrainVAMPVampireForest(),
            new SurfaceVAMPVampireForest(config, vampBiome.topBlock, vampBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
