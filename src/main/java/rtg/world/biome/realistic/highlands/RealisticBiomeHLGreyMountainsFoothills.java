package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLGreyMountainsFoothills;
import rtg.world.gen.terrain.highlands.TerrainHLGreyMountainsFoothills;

public class RealisticBiomeHLGreyMountainsFoothills extends RealisticBiomeHLBase {

    public RealisticBiomeHLGreyMountainsFoothills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLGreyMountainsFoothills(200f, 90f, 68f),
            new SurfaceHLGreyMountainsFoothills(config, biome.topBlock, biome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
