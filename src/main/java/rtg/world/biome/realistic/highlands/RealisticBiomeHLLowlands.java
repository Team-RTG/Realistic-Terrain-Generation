package rtg.world.biome.realistic.highlands;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLLowlands;
import rtg.world.gen.terrain.highlands.TerrainHLLowlands;

public class RealisticBiomeHLLowlands extends RealisticBiomeHLBase {

    public RealisticBiomeHLLowlands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLLowlands(),
            new SurfaceHLLowlands(
                config,
                Blocks.grass.getDefaultState(),
                Blocks.dirt.getDefaultState(),
                false,
                null,
                0f, 1.5f, 60f, 65f, 1.5f,
                Blocks.grass.getDefaultState(),
                0.32f
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
