package rtg.world.biome.realistic.betteragriculture;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeBABase extends RealisticBiomeBase {

    public static RealisticBiomeBase baFarmlandBiome;

    public RealisticBiomeBABase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        this.lavaSurfaceLakeChance = 0;
    }

    @Override
    public String modSlug() {
        return "betteragriculture";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("betteragriculture")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("FarmlandBiome") && biomeClass.equals("betteragriculture.world.biome.FarmlandBiome")) {
                    baFarmlandBiome = new RealisticBiomeBAFarmlandBiome(biome);
                }
            }
        }
    }
}
