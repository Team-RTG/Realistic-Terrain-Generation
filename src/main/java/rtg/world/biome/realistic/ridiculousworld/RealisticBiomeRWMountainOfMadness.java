package rtg.world.biome.realistic.ridiculousworld;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWMountainOfMadness;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWMountainOfMadness;

public class RealisticBiomeRWMountainOfMadness extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWMountainOfMadness(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.frozenRiver,
            new TerrainRWMountainOfMadness(10f, 120f, 68f, 200f),
            new SurfaceRWMountainOfMadness(config, rwBiome.topBlock, rwBiome.fillerBlock)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
