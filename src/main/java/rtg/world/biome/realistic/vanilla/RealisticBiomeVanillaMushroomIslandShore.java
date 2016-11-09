package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUSHROOM_ISLAND_SHORE;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMushroomIslandShore(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore(),
            new SurfaceVanillaMushroomIslandShore(config, biome.topBlock, biome.fillerBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaMushroomIslandShore();
    }

    public class TerrainVanillaMushroomIslandShore extends TerrainBase {

        public TerrainVanillaMushroomIslandShore() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainMarsh(x, y, simplex, 61.5f);
        }
    }
}
