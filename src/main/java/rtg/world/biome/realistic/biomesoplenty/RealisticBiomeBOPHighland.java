package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHighland;
import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPHighland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.highland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPHighland(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPHighland(),
            new SurfaceBOPHighland(config, biome.topBlock, biome.fillerBlock)
        );

        this.generatesEmeralds = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPHighland();
    }

    public class TerrainBOPHighland extends TerrainBase {

        private float baseHeight = 90f;
        private BumpyHillsEffect onTop = new BumpyHillsEffect();
        private JitterEffect withJitter;

        public TerrainBOPHighland() {

            onTop.hillHeight = 30;
            onTop.hillWavelength = 60;
            onTop.spikeHeight = 20;
            onTop.spikeWavelength = 10;

            withJitter = new JitterEffect();
            withJitter.amplitude = 2;
            withJitter.wavelength = 5;
            withJitter.jittered = onTop;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return riverized(baseHeight + withJitter.added(simplex, cell, x, y) + this.groundNoise(x, y, 6, simplex), river);
            //return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
        }
    }
}
