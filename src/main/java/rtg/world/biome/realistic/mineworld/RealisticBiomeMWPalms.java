package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWPalms;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMWPalms extends RealisticBiomeMWBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMWPalms(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.mineworld.TerrainMWPalms(),
            new SurfaceMWPalms(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                10f, //float mixWidth,
                -0.15f, //float mixHeight,
                5f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.equalsZeroChance = 5;
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMWPalms();
    }

    public class TerrainMWPalms extends TerrainBase {

        public TerrainMWPalms() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainBeach(x, y, simplex, river, 160f, 30f, 65f);
        }
    }
}
