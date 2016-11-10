package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.COLD_BEACH;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaColdBeach(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaColdBeach(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, (byte) 0, 1)
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaColdBeach();
    }

    public class TerrainVanillaColdBeach extends TerrainBase {

        public TerrainVanillaColdBeach() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainBeach(x, y, simplex, river, 180f, 35f, 63f);
        }
    }
}
