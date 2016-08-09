package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLMojave;
import rtg.world.gen.terrain.highlands.TerrainHLMojave;

public class RealisticBiomeHLMojave extends RealisticBiomeHLBase {

    public RealisticBiomeHLMojave(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLMojave(0f, 50f, 67f, 210f),
            new SurfaceHLMojave(config, biome.topBlock, biome.fillerBlock, (byte)1, 1)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
