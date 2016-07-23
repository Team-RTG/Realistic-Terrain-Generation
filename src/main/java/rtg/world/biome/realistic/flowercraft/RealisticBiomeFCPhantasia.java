package rtg.world.biome.realistic.flowercraft;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.flowercraft.SurfaceFCPhantasia;
import rtg.world.gen.terrain.flowercraft.TerrainFCPhantasia;

public class RealisticBiomeFCPhantasia extends RealisticBiomeFCBase {

    public RealisticBiomeFCPhantasia(BiomeGenBase fcBiome, BiomeConfig config) {

        super(config,
            fcBiome, BiomeGenBase.river,
            new TerrainFCPhantasia(),
            new SurfaceFCPhantasia(config, fcBiome.topBlock, fcBiome.fillerBlock)
        );
    }
}
