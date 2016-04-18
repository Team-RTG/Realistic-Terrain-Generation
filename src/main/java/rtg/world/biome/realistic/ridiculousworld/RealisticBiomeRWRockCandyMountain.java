package rtg.world.biome.realistic.ridiculousworld;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWRockCandyMountain;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWRockCandyMountain;

public class RealisticBiomeRWRockCandyMountain extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWRockCandyMountain(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWRockCandyMountain(10f, 120f, 68f, 200f),
            new SurfaceRWRockCandyMountain(config, rwBiome.topBlock, rwBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
