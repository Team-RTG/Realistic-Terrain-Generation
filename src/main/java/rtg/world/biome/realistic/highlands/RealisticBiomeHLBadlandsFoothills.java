package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLBadlandsFoothills;
import rtg.world.gen.terrain.highlands.TerrainHLBadlandsFoothills;

public class RealisticBiomeHLBadlandsFoothills extends RealisticBiomeHLBase {

    public RealisticBiomeHLBadlandsFoothills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLBadlandsFoothills(),
            new SurfaceHLBadlandsFoothills(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
