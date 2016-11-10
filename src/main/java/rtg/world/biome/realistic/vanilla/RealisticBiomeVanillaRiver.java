package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.RIVER;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaRiver(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaRiver();
    }

    public class TerrainVanillaRiver extends TerrainBase {

        public TerrainVanillaRiver() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, simplex, river, 3f, 60f);
        }
    }
}
