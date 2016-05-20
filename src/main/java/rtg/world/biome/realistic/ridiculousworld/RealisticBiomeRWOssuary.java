package rtg.world.biome.realistic.ridiculousworld;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWOssuary;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWOssuary;

public class RealisticBiomeRWOssuary extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWOssuary(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome,
            BiomeGenBase.river,
            new TerrainRWOssuary(),
            new SurfaceRWOssuary(config, rwBiome.topBlock, rwBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
