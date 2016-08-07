package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLHighlands;
import rtg.world.gen.terrain.highlands.TerrainHLHighlands;

public class RealisticBiomeHLHighlands extends RealisticBiomeHLBase {

    public RealisticBiomeHLHighlands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLHighlands(),
            new SurfaceHLHighlands(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
