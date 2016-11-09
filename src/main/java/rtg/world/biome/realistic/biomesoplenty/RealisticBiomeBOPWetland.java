package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWetland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPWetland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.wetland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPWetland(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPWetland(),
            new SurfaceBOPWetland(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState())
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPWetland();
    }

    public class TerrainBOPWetland extends TerrainBase {

        public TerrainBOPWetland() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainMarsh(x, y, simplex, 61.5f);
        }
    }
}
