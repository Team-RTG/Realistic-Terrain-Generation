package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWIceHills;
import rtg.world.gen.terrain.mineworld.TerrainMWIceHills;

public class RealisticBiomeMWIceHills extends RealisticBiomeMWBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeMWIceHills(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMWIceHills(230f, 60f, 68f),
            new SurfaceMWIceHills(config,
                biome.topBlock,
                biome.fillerBlock,
                Blocks.SNOW.getDefaultState(),
                Blocks.SNOW.getDefaultState(),
                Blocks.PACKED_ICE.getDefaultState(),
                Blocks.ICE.getDefaultState(),
                60f, -0.14f, 14f, 0.25f
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
