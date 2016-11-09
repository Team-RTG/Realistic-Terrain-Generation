package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPPrairie;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPPrairie extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.prairie.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPPrairie(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPPrairie(65f, 80f, 25f),
            new SurfaceBOPPrairie(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPPrairie(65f, 80f, 25f);
    }

    public class TerrainBOPPrairie extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;

        // 63f, 80f, 30f

        public TerrainBOPPrairie(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return this.terrainPlains(x, y, simplex, river, 200f, 1f, 30f, 1f, maxHeight);
        }
    }
}
