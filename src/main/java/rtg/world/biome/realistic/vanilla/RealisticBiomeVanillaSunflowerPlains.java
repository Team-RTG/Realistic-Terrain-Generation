package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSunflowerPlains;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaSunflowerPlains extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_PLAINS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSunflowerPlains(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaSunflowerPlains(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaSunflowerPlains();
    }

    public class TerrainVanillaSunflowerPlains extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaSunflowerPlains() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 65f);
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }
}
