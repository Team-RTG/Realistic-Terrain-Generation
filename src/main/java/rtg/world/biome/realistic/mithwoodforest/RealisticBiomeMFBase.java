package rtg.world.biome.realistic.mithwoodforest;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mfMithwoodForest;

    public RealisticBiomeMFBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        this.lavaSurfaceLakeChance = 0;
    }

    @Override
    public String modSlug() {
        return "mithwoodforest";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("mithwoodforest")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Mithwood Forest") && biomeClass.equals("rainbeau.mithwoodforest.RMFWorldGen.BiomeMithwoodForest")) {
                    mfMithwoodForest = new RealisticBiomeMFMithwoodForest(biome);
                }
            }
        }
    }
}
