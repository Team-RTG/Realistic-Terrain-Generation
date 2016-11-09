package rtg.world.biome.realistic.flowercraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.flowercraft.SurfaceFCPhantasia;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeFCPhantasia extends RealisticBiomeFCBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeFCPhantasia(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.flowercraft.TerrainFCPhantasia(),
            new SurfaceFCPhantasia(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainFCPhantasia();
    }

    public class TerrainFCPhantasia extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainFCPhantasia() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }
}
