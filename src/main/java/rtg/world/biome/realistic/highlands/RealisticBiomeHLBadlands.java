package rtg.world.biome.realistic.highlands;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLBadlands;
import rtg.world.gen.terrain.highlands.TerrainHLBadlands;

public class RealisticBiomeHLBadlands extends RealisticBiomeHLBase {

    public RealisticBiomeHLBadlands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLBadlands(40f, 140f, 13f, 70f, 76f),
            new SurfaceHLBadlands(
                config,
                Blocks.grass.getDefaultState(),
                Blocks.dirt.getDefaultState(),
                Blocks.stained_hardened_clay.getStateFromMeta(8),
                Blocks.hardened_clay.getDefaultState(),
                60f, -0.14f, 14f, 0.25f
            )
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
