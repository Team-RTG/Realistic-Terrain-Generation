package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLGreyMountains;
import rtg.world.gen.terrain.highlands.TerrainHLGreyMountains;

public class RealisticBiomeHLGreyMountains extends RealisticBiomeHLBase {

    public RealisticBiomeHLGreyMountains(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLGreyMountains(230f, 100f, 68f),
            new SurfaceHLGreyMountains(config, biome.topBlock, biome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
