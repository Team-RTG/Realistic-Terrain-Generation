package rtg.world.biome.realistic.highlands;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLBambooForest;
import rtg.world.gen.terrain.highlands.TerrainHLBambooForest;

public class RealisticBiomeHLBambooForest extends RealisticBiomeHLBase {

    public RealisticBiomeHLBambooForest(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLBambooForest(0f, 50f, 68f, 200f),
            new SurfaceHLBambooForest(
                config,
                Blocks.grass.getDefaultState(),
                Blocks.dirt.getDefaultState(),
                false,
                null,
                0f, 1.5f, 60f, 65f, 1.5f,
                Blocks.dirt.getStateFromMeta(2),
                0.35f
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
