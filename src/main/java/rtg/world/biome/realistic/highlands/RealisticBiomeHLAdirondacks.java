package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLAdirondacks;
import rtg.world.gen.terrain.highlands.TerrainHLAdirondacks;

public class RealisticBiomeHLAdirondacks extends RealisticBiomeHLBase {

    public RealisticBiomeHLAdirondacks(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLAdirondacks(),
            new SurfaceHLAdirondacks(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
