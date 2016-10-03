package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.agriculturalrevolution.SurfaceARTropicalHills;
import rtg.world.gen.terrain.agriculturalrevolution.TerrainARTropicalHills;

public class RealisticBiomeARTropicalHills extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeARTropicalHills(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainARTropicalHills(10f, 80f, 68f, 200f),
            new SurfaceARTropicalHills(config, Blocks.SAND.getDefaultState(), Blocks.SANDSTONE.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
