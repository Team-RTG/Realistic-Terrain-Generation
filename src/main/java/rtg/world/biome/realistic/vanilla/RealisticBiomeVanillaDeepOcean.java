package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.DEEP_OCEAN;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaDeepOcean(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaDeepOcean(config, Blocks.GRAVEL.getDefaultState(), Blocks.GRAVEL.getDefaultState(), Blocks.CLAY.getDefaultState(), 20f, 0.1f)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaDeepOcean();
    }

    public class TerrainVanillaDeepOcean extends TerrainBase {

        public TerrainVanillaDeepOcean() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainOcean(x, y, simplex, river, 40f);
        }
    }
}
