package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLAlps;
import rtg.world.gen.terrain.highlands.TerrainHLAlps;

public class RealisticBiomeHLAlps extends RealisticBiomeHLBase {

    public RealisticBiomeHLAlps(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.frozenRiver,
            new TerrainHLAlps(),
            new SurfaceHLAlps(config, biome.topBlock, biome.fillerBlock, false, null, 0.45f)
        );

        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
        this.addDeco(decoBaseBiomeDecorations);
    }
}
