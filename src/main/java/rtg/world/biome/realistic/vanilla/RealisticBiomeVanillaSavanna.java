package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.biome.deco.collection.DecoCollectionSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavanna;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.SAVANNA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaSavanna(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.vanilla.TerrainVanillaSavanna(),
            new SurfaceVanillaSavanna(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRASS.getDefaultState(), 13f, 0.27f)
        );

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna(this.config._boolean(BiomeConfigVanillaSavanna.decorationLogsId)));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaSavanna();
    }

    public class TerrainVanillaSavanna extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaSavanna() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 100f, 66f);
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }
}
