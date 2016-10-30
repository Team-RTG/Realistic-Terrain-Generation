package rtg.world.biome.realistic.morechinesemc;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.morechinesemc.SurfaceMCMBog;
import rtg.world.gen.terrain.morechinesemc.TerrainMCMBog;

public class RealisticBiomeMCMBog extends RealisticBiomeMCMBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMCMBog(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMCMBog(),
            new SurfaceMCMBog(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
