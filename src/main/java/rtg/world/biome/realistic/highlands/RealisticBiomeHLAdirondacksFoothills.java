package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLAdirondacksFoothills;
import rtg.world.gen.terrain.highlands.TerrainHLAdirondacksFoothills;

public class RealisticBiomeHLAdirondacksFoothills extends RealisticBiomeHLBase {

    public RealisticBiomeHLAdirondacksFoothills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLAdirondacksFoothills(230f, 15f, 0f),
            new SurfaceHLAdirondacksFoothills(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
