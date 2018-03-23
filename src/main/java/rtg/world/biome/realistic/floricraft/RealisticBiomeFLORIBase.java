package rtg.world.biome.realistic.floricraft;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;


@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeFLORIBase extends RealisticBiomeBase {

    public static RealisticBiomeBase floriRoseLand;
    public static RealisticBiomeBase floriTulipLand;

    public RealisticBiomeFLORIBase(Biome b, Biome riverbiome) {

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
        return Mods.floricraft.getPrettyName();
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

        if (Mods.floricraft.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Rose Land") && biomeClass.equals("com.hosta.Floricraft.world.biome.BiomeFlowerLand")) {
                    floriRoseLand = new RealisticBiomeFLORIRoseLand(biome);
                }
                else if (biomeName.equals("Tulip Land") && biomeClass.equals("com.hosta.Floricraft.world.biome.BiomeFlowerLand")) {
                    floriTulipLand = new RealisticBiomeFLORITulipLand(biome);
                }
            }
        }
    }
}
