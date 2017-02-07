package rtg.world.biome.realistic.sugiforest;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeSFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase sfSugiForest;

    public RealisticBiomeSFBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

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
        return "sugiforest";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("sugiforest")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Sugi Forest") && biomeClass.equals("sugiforest.world.BiomeSugiForest")) {
                    sfSugiForest = new RealisticBiomeSFSugiForest(biome);
                }
            }
        }
    }
}
