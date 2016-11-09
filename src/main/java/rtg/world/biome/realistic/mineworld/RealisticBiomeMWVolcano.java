package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWVolcano;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMWVolcano extends RealisticBiomeMWBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMWVolcano(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.mineworld.TerrainMWVolcano(),
            new SurfaceMWVolcano(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 1;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMWVolcano();
    }

    public class TerrainMWVolcano extends TerrainBase {

        public TerrainMWVolcano() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainVolcano(x, y, simplex, cell, border, 70f);
        }
    }
}
