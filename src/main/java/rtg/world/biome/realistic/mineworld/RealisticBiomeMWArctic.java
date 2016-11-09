package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWArctic;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMWArctic extends RealisticBiomeMWBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeMWArctic(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.mineworld.TerrainMWArctic(),
            new SurfaceMWArctic(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.SNOW.getDefaultState(), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMWArctic();
    }

    public class TerrainMWArctic extends TerrainBase {

        private float ruggedness = 3f;
        private float ruggednessWavelength = 100f;
        private float heightPitch = 35f;// the ruggedness parameter will multiply this by 0.2
        private float heightDivisor = 1f;

        public TerrainMWArctic() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float result = terrainPlains(x, y, simplex, river, ruggednessWavelength, ruggedness, heightPitch, heightDivisor, base);
            // no indentations; cutoff is not noticeable with these low slopes
            return result > base ? result : base;
        }
    }
}
