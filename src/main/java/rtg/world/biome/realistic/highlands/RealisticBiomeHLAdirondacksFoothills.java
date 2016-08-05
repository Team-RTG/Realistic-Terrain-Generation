package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLAdirondacksFoothills;
import rtg.world.gen.terrain.highlands.TerrainHLAdirondacksFoothills;

public class RealisticBiomeHLAdirondacksFoothills extends RealisticBiomeHLBase {

    public RealisticBiomeHLAdirondacksFoothills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLAdirondacksFoothills(),
            new SurfaceHLAdirondacksFoothills(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
