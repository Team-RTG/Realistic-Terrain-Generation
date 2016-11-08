package rtg.world.biome.realistic.morechinesemc;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.morechinesemc.SurfaceMCMBlackPlain;
import rtg.world.gen.terrain.morechinesemc.TerrainMCMBlackPlain;

public class RealisticBiomeMCMBlackPlain extends RealisticBiomeMCMBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMCMBlackPlain(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMCMBlackPlain(),
            new SurfaceMCMBlackPlain(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
