package rtg.world.biome.realistic.morechinesemc;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.morechinesemc.SurfaceMCMMudFlat;
import rtg.world.gen.terrain.morechinesemc.TerrainMCMMudFlat;

public class RealisticBiomeMCMMudFlat extends RealisticBiomeMCMBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMCMMudFlat(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMCMMudFlat(),
            new SurfaceMCMMudFlat(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
