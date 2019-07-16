package rtg.world.biome.realistic.plants;

import javax.annotation.Nonnull;

import net.minecraft.world.biome.Biome;
import rtg.api.world.biome.RealisticBiomeBase;


public abstract class RealisticBiomePLANTSBase extends RealisticBiomeBase {

    public RealisticBiomePLANTSBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomePLANTSBase(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomePLANTSBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomePLANTSBase(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public void initDecos() {
    }
}
