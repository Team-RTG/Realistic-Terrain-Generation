package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLPoplarHills;
import rtg.world.gen.terrain.highlands.TerrainHLPoplarHills;

public class RealisticBiomeHLPoplarHills extends RealisticBiomeHLBase {

    public RealisticBiomeHLPoplarHills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLPoplarHills(),
            new SurfaceHLPoplarHills(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
