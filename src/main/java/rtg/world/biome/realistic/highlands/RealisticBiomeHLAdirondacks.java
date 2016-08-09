package rtg.world.biome.realistic.highlands;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLAdirondacks;
import rtg.world.gen.terrain.highlands.TerrainHLAdirondacks;

public class RealisticBiomeHLAdirondacks extends RealisticBiomeHLBase {

    public RealisticBiomeHLAdirondacks(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLAdirondacks(230f, 60f, 88f),
            new SurfaceHLAdirondacks(
                config,
                biome.topBlock,
                biome.fillerBlock,
                Blocks.grass.getDefaultState(),
                Blocks.dirt.getDefaultState(),
                60f, -0.14f, 14f, 0.25f
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
