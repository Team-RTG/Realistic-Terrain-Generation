package rtg.world.biome.realistic.highlands;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLRedwoodForest;
import rtg.world.gen.terrain.highlands.TerrainHLRedwoodForest;

public class RealisticBiomeHLRedwoodForest extends RealisticBiomeHLBase {

    public RealisticBiomeHLRedwoodForest(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLRedwoodForest(40f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLRedwoodForest(
                config,
                Blocks.grass.getDefaultState(),
                Blocks.dirt.getDefaultState(),
                false,
                null,
                0f, 1.5f, 60f, 65f, 1.5f,
                Blocks.dirt.getStateFromMeta(2),
                0.10f
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
