package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrassland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPGrassland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.grassland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPGrassland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPGrassland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPGrassland();
    }

    public class TerrainBOPGrassland extends TerrainBase {

        public TerrainBOPGrassland() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
        }
    }
}
