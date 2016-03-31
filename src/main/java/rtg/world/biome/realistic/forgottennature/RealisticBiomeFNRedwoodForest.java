package rtg.world.biome.realistic.forgottennature;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.config.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNRedwoodForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNRedwoodForest;

public class RealisticBiomeFNRedwoodForest extends RealisticBiomeFNBase {

    public RealisticBiomeFNRedwoodForest(BiomeGenBase fnBiome, BiomeConfig config) {

        super(
                fnBiome, Biomes.river,
                new TerrainFNRedwoodForest(),
                new SurfaceFNRedwoodForest(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.10f));
    }
}
