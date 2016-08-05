package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLAlpsFoothills;
import rtg.world.gen.terrain.highlands.TerrainHLAlpsFoothills;

public class RealisticBiomeHLAlpsFoothills extends RealisticBiomeHLBase {

    public RealisticBiomeHLAlpsFoothills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLAlpsFoothills(),
            new SurfaceHLAlpsFoothills(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
