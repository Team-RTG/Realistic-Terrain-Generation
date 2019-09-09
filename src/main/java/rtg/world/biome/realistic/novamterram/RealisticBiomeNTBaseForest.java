package rtg.world.biome.realistic.novamterram;

import net.minecraft.world.biome.Biome;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

import javax.annotation.Nonnull;


public abstract class RealisticBiomeNTBaseForest extends RealisticBiomeNTBase {

    public RealisticBiomeNTBaseForest(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeNTBaseForest(@Nonnull final Biome baseBiome) {

        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseForest(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {

        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseForest(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {

        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBrushland();
    }

    public static class TerrainBOPBrushland extends TerrainBase {

        private float baseHeight = 65f;
        private float hillStrength = 12f;

        public TerrainBOPBrushland() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld);

            float m = hills(x, y, hillStrength, rtgWorld);

            return riverized(baseHeight + groundNoise + m, river);
        }
    }
}
