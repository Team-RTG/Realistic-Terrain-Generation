package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLTropicalHills;
import rtg.world.gen.terrain.highlands.TerrainHLTropicalHills;

public class RealisticBiomeHLTropicalHills extends RealisticBiomeHLBase {

    public RealisticBiomeHLTropicalHills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLTropicalHills(),
            new SurfaceHLTropicalHills(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
