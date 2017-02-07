package rtg.world.biome.realistic.flowercraft;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;


@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeFCBase extends RealisticBiomeBase {

    public static RealisticBiomeBase fcPhantasia;

    public RealisticBiomeFCBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

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
        return "flowercraft";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("flowercraftmod")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Phantasia") && biomeClass.equals("flowercraftmod.world.biome.BiomeGenFCPhantasia")) {
                    fcPhantasia = new RealisticBiomeFCPhantasia(biome);
                }
            }
        }
    }
}
