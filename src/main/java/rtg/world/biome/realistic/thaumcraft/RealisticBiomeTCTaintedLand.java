package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase {
    public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome, BiomeConfig config) {
        super(
                tcBiome, Biomes.river,
                new TerrainTCTaintedLand(),
                new SurfaceTCTaintedLand(config, tcBiome.topBlock, tcBiome.fillerBlock)
        );
    }
}
