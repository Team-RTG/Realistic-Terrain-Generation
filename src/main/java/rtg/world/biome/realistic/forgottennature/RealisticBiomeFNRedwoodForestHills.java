package rtg.world.biome.realistic.forgottennature;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNRedwoodForestHills;
import rtg.world.gen.terrain.forgottennature.TerrainFNRedwoodForestHills;

public class RealisticBiomeFNRedwoodForestHills extends RealisticBiomeFNBase {

    public RealisticBiomeFNRedwoodForestHills(BiomeGenBase fnBiome, BiomeConfig config) {

        super(
                fnBiome, Biomes.river,
                new TerrainFNRedwoodForestHills(),
                new SurfaceFNRedwoodForestHills(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f));
    }
}
