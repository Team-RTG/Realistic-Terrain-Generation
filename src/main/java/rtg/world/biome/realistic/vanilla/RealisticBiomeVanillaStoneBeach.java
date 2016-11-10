package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.STONE_BEACH;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaStoneBeach(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaStoneBeach(config, Blocks.GRAVEL.getDefaultState(), biome.fillerBlock, 1f, 1.5f, 85f, 20f, 4f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaStoneBeach();
    }

    public class TerrainVanillaStoneBeach extends TerrainBase {

        public TerrainVanillaStoneBeach() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainBeach(x, y, simplex, river, 180f, 35f, 63f);
        }
    }
}
