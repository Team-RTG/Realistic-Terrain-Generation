package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGravelBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPGravelBeach extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.gravel_beach.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPGravelBeach(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPGravelBeach(
                config,
                biome.topBlock,
                biome.fillerBlock,
                biome.topBlock,
                biome.fillerBlock,
                (byte) 0,
                1
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPGravelBeach();
    }

    public class TerrainBOPGravelBeach extends TerrainBase {

        public TerrainBOPGravelBeach() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainBeach(x, y, simplex, river, 180f, 35f, 60f);
        }
    }
}
