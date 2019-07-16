package rtg.world.biome.realistic.vampirism;

import javax.annotation.Nonnull;

import net.minecraft.world.biome.Biome;
import rtg.api.world.biome.RealisticBiomeBase;


public abstract class RealisticBiomeVAMPBase extends RealisticBiomeBase {

    public RealisticBiomeVAMPBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeVAMPBase(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeVAMPBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeVAMPBase(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public void initDecos() {
    }
}
