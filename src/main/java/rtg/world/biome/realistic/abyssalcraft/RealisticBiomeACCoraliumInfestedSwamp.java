package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACCoraliumInfestedSwamp;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.coralium_infested_swamp;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACCoraliumInfestedSwamp(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.abyssalcraft.TerrainACCoraliumInfestedSwamp(),
            new SurfaceACCoraliumInfestedSwamp(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACCoraliumInfestedSwamp();
    }

    public class TerrainACCoraliumInfestedSwamp extends TerrainBase {

        public TerrainACCoraliumInfestedSwamp() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainMarsh(x, y, simplex, 61.5f);
        }
    }
}
