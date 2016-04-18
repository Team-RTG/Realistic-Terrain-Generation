package rtg.world.biome.realistic.arsmagica;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.arsmagica.SurfaceAMWitchwoodForest;
import rtg.world.gen.terrain.arsmagica.TerrainAMWitchwoodForest;

public class RealisticBiomeAMWitchwoodForest extends RealisticBiomeAMBase
{
    
    public RealisticBiomeAMWitchwoodForest(BiomeGenBase amBiome, BiomeConfig config)
    {
    
        super(config,
            amBiome, BiomeGenBase.river,
            new TerrainAMWitchwoodForest(),
            new SurfaceAMWitchwoodForest(config, amBiome.topBlock, amBiome.fillerBlock)
        );
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
