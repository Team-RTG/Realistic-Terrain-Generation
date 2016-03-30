package rtg.world.biome.realistic.buildcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.buildcraft.SurfaceBCDesertOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCDesertOilField;

public class RealisticBiomeBCDesertOilField extends RealisticBiomeBCBase {

    public RealisticBiomeBCDesertOilField(BiomeGenBase bcBiome, BiomeConfig config) {

        super(
                bcBiome, Biomes.river,
                new TerrainBCDesertOilField(),
                new SurfaceBCDesertOilField(config, bcBiome.topBlock, bcBiome.fillerBlock));
    }
}
