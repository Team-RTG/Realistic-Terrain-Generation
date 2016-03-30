package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.thaumcraft.SurfaceTCEerie;
import rtg.world.gen.terrain.thaumcraft.TerrainTCEerie;

public class RealisticBiomeTCEerie extends RealisticBiomeTCBase {
    public RealisticBiomeTCEerie(BiomeGenBase tcBiome, BiomeConfig config) {
        super(
                tcBiome, Biomes.river,
                new TerrainTCEerie(),
                new SurfaceTCEerie(config, tcBiome.topBlock, tcBiome.fillerBlock)
        );
    }
}