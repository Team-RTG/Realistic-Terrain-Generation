package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACCoraliumInfestedSwamp;
import rtg.world.gen.terrain.abyssalcraft.TerrainACCoraliumInfestedSwamp;

public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeACBase {

    public RealisticBiomeACCoraliumInfestedSwamp(Biome acBiome, BiomeConfig config) {

        super(config,
            acBiome,
            Biome.river,
            new TerrainACCoraliumInfestedSwamp(),
            new SurfaceACCoraliumInfestedSwamp(config, acBiome.topBlock, acBiome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
