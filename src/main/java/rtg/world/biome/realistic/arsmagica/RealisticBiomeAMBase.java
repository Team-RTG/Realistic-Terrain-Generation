package rtg.world.biome.realistic.arsmagica;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeAMBase extends RealisticBiomeBase {

    public static RealisticBiomeBase amWitchwoodForest;

    public RealisticBiomeAMBase(Biome b, Biome river) {

        super(b, river);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
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
        return "arsmagica2";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("arsmagica2")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("WitchwoodForest") && biomeClass.equals("am2.world.BiomeWitchwoodForest")) {
                    amWitchwoodForest = new RealisticBiomeAMWitchwoodForest(biome);
                }
            }
        }
    }

    @Override
    public boolean generatesEmeralds() {
        return false;
    }
}
