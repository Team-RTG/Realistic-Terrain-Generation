package rtg.world.biome.realistic.biomesoplenty;

import javax.annotation.Nonnull;

import biomesoplenty.api.biome.IExtendedBiome;
import net.minecraft.world.biome.Biome;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.realistic.RealisticBiomeBase;


public abstract class RealisticBiomeBOPBase extends RealisticBiomeBase {

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);

        if (!(baseBiome instanceof IExtendedBiome)) {
            throw new ClassCastException(
                String.format("Tried creating an instance of a IRealisticBiome for a BOPBiome without a %s.",
                    baseBiome.getClass().getName())
            );
        }
    }

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
// TODO: [1.12] Verify that this should be over-ridden for all BoP biomes, and not just for bayou.
    public int waterSurfaceLakeChance() {
        return 0;
    }
}
