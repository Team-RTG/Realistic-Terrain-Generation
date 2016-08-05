package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLMojave;
import rtg.world.gen.terrain.highlands.TerrainHLMojave;

public class RealisticBiomeHLMojave extends RealisticBiomeHLBase {

    public RealisticBiomeHLMojave(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLMojave(),
            new SurfaceHLMojave(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
