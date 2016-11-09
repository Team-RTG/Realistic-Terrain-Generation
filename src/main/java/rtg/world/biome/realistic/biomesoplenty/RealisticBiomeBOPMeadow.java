package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMeadow;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPMeadow extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.meadow.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMeadow(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPMeadow(),
            new SurfaceBOPMeadow(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPMeadow();
    }

    public class TerrainBOPMeadow extends TerrainBase {

        public TerrainBOPMeadow() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
        }
    }
}
