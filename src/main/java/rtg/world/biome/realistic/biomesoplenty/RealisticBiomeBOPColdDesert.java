package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPColdDesert;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPColdDesert extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.cold_desert.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPColdDesert(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPColdDesert(),
            new SurfaceBOPColdDesert(config,
                Blocks.SNOW.getDefaultState(), //Block top
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

        return new TerrainBOPColdDesert();
    }

    public class TerrainBOPColdDesert extends TerrainBase {

        private float ruggedness = 3f;
        private float ruggednessWavelength = 100f;
        private float heightPitch = 35f;// the ruggedness parameter will multiply this by 0.2
        private float heightDivisor = 1f;

        public TerrainBOPColdDesert() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float result = terrainPlains(x, y, simplex, river, ruggednessWavelength, ruggedness, heightPitch, heightDivisor, base);
            // no indentations; cutoff is not noticeable with these low slopes
            return result > base ? result : base;
        }
    }
}
