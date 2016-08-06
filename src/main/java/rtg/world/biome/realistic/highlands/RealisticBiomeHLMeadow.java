package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLMeadow;
import rtg.world.gen.terrain.highlands.TerrainHLMeadow;

public class RealisticBiomeHLMeadow extends RealisticBiomeHLBase {

    public RealisticBiomeHLMeadow(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLMeadow(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLMeadow(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
