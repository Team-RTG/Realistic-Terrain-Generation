package rtg.world.biome.realistic.iceandfire;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;


@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeIAFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase iafGlacier;

    public RealisticBiomeIAFBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    @Override
    public Biome baseBiome() {
        return this.baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
    }

    @Override
    public String modSlug() {
        return "iceandfire";
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("iceandfire")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Glacier") && biomeClass.equals("com.github.alexthe666.iceandfire.world.BiomeGlacier")) {
                    iafGlacier = new RealisticBiomeIAFGlacier(biome);
                }
            }
        }
    }
}
