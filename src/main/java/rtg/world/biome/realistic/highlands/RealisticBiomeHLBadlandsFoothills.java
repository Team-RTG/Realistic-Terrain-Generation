package rtg.world.biome.realistic.highlands;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLBadlandsFoothills;
import rtg.world.gen.terrain.highlands.TerrainHLBadlandsFoothills;

public class RealisticBiomeHLBadlandsFoothills extends RealisticBiomeHLBase {

    public RealisticBiomeHLBadlandsFoothills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLBadlandsFoothills(40f, 140f, 13f, 70f, 67f),
            new SurfaceHLBadlandsFoothills(
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
