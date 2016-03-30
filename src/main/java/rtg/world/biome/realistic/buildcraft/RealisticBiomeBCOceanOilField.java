package rtg.world.biome.realistic.buildcraft;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.buildcraft.SurfaceBCOceanOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCOceanOilField;

public class RealisticBiomeBCOceanOilField extends RealisticBiomeBCBase {

    public RealisticBiomeBCOceanOilField(BiomeGenBase bcBiome, BiomeConfig config) {

        super(
                bcBiome, Biomes.river,
                new TerrainBCOceanOilField(),
                new SurfaceBCOceanOilField(config, Blocks.sand.getDefaultState(), Blocks.sand.getDefaultState(), Blocks.gravel.getDefaultState(), 20f, 0.2f));
    }
}
