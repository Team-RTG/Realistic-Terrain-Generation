package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWIceHills;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMWIceHills extends RealisticBiomeMWBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeMWIceHills(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.mineworld.TerrainMWIceHills(230f, 60f, 68f),
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

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMWIceHills(230f, 60f, 68f);
    }

    public class TerrainMWIceHills extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;

        public TerrainMWIceHills(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
        }
    }
}
