package rtg.world.biome.realistic.ridiculousworld;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWMurica;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWMurica;

public class RealisticBiomeRWMurica extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWMurica(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome,
            BiomeGenBase.river,
            new TerrainRWMurica(76f, 35f),
            new SurfaceRWMurica(config, rwBiome.topBlock, rwBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
