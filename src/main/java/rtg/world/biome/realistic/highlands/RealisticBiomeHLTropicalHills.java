package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLTropicalHills;
import rtg.world.gen.terrain.highlands.TerrainHLTropicalHills;

public class RealisticBiomeHLTropicalHills extends RealisticBiomeHLBase {

    public RealisticBiomeHLTropicalHills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLTropicalHills(230f, 15f, 0f),
            new SurfaceHLTropicalHills(config, biome.topBlock, biome.fillerBlock)
        );

        this.waterSurfaceLakeChance = 3;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
