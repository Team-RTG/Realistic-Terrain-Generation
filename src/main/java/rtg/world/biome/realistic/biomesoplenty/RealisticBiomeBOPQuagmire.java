package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPQuagmire;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPQuagmire extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.quagmire.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPQuagmire(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPQuagmire(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPQuagmire();
    }

    public class TerrainBOPQuagmire extends TerrainBase {

        public TerrainBOPQuagmire() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainMarsh(x, y, simplex, 61.5f);
        }
    }
}
