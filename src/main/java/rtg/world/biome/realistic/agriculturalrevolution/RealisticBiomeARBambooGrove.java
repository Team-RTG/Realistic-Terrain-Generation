package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.agriculturalrevolution.SurfaceARBambooGrove;
import rtg.world.gen.terrain.agriculturalrevolution.TerrainARBambooGrove;

public class RealisticBiomeARBambooGrove extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeARBambooGrove(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainARBambooGrove(),
            new SurfaceARBambooGrove(config, biome.topBlock, biome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
