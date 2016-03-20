package rtg.world.biome.realistic.forgottennature;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNTropicalForestHills;
import rtg.world.gen.terrain.forgottennature.TerrainFNTropicalForestHills;

public class RealisticBiomeFNTropicalForestHills extends RealisticBiomeFNBase {

    public RealisticBiomeFNTropicalForestHills(BiomeGenBase fnBiome, BiomeConfig config) {

        super(config,
                fnBiome, Biomes.river,
                new TerrainFNTropicalForestHills(),
                new SurfaceFNTropicalForestHills(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f)
        );
    }
}
