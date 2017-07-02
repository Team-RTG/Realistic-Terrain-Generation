package rtg.world.biome.realistic.hotwatermod;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.hotwatermod.SurfaceHWMHotSprings;
import rtg.world.gen.terrain.hotwatermod.TerrainHWMHotSprings;

public class RealisticBiomeHWMHotSprings extends RealisticBiomeHWMBase
{
    
    public RealisticBiomeHWMHotSprings(BiomeGenBase hwmBiome, BiomeConfig config)
    {
    
        super(config, 
            hwmBiome, BiomeGenBase.river,
            new TerrainHWMHotSprings(),
            new SurfaceHWMHotSprings(config, hwmBiome.topBlock, hwmBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
