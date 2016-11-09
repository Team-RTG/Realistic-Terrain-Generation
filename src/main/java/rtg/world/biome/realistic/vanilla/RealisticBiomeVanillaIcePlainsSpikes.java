package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_ICE_FLATS;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaIcePlainsSpikes(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaIcePlainsSpikes(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.topBlock)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaIcePlainsSpikes();
    }

    public class TerrainVanillaIcePlainsSpikes extends TerrainBase {

        public TerrainVanillaIcePlainsSpikes() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 65f);
        }
    }
}
