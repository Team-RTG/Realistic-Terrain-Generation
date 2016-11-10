package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUSHROOM_ISLAND;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMushroomIsland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaMushroomIsland(config, biome.topBlock, biome.fillerBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaMushroomIsland();
    }

    public class TerrainVanillaMushroomIsland extends TerrainBase {

        private float heigth;
        private float width;

        public TerrainVanillaMushroomIsland() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainGrasslandFlats(x, y, simplex, river, 40f, 25f, 68f);
        }
    }
}
