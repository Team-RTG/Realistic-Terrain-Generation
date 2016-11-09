package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMountainFoothills;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPMountainFoothills extends RealisticBiomeBOPBase {

    //TODO: Decidious
    public static Biome biome = BOPBiomes.mountain_foothills.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMountainFoothills(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPMountainFoothills(),
            new SurfaceBOPMountainFoothills(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.DIRT.getDefaultState(), //IBlockState mixTop,
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

        return new TerrainBOPMountainFoothills();
    }

    public class TerrainBOPMountainFoothills extends TerrainBase {

        private float baseHeight = 76f;
        private float hillStrength = 30f;

        public TerrainBOPMountainFoothills() {

        }

        public TerrainBOPMountainFoothills(float bh, float hs) {

            baseHeight = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

            float m = hills(x, y, hillStrength, simplex, river);

            return riverized(baseHeight + groundNoise, river) + m;
        }
    }
}
