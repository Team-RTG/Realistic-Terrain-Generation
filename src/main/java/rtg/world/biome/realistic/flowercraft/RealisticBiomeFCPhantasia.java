package rtg.world.biome.realistic.flowercraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.flowercraft.SurfaceFCPhantasia;
import rtg.world.gen.terrain.flowercraft.TerrainFCPhantasia;

public class RealisticBiomeFCPhantasia extends RealisticBiomeFCBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeFCPhantasia(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainFCPhantasia(),
            new SurfaceFCPhantasia(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
