package rtg.world.biome.realistic.vampirism;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeVAMPBase extends RealisticBiomeBase {

    private static final String VAMPIRISM_MODID   = "vampirism";
    private static final String VAMP_FOREST_BIOME = "vampireForest";

    public static RealisticBiomeBase vampireForest;

    public RealisticBiomeVAMPBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    @Override public String modSlug() { return VAMPIRISM_MODID; }

    public static void addBiomes() {

        if (Loader.isModLoaded(VAMPIRISM_MODID)) {

            for (Biome biome : Biome.REGISTRY) {

                ResourceLocation loc = biome.getRegistryName();
                if (loc == null) {
                    Logger.error("ResourceLocation is NULL for: \"{}\"[{}]", biome.getBiomeName(), biome.getBiomeClass().getName());
                    continue;
                }

                if (loc.getResourceDomain().equals(VAMPIRISM_MODID) && loc.getResourcePath().equals(VAMP_FOREST_BIOME)) { setVampireForest(biome); }
            }
        }
    }
    private static void setVampireForest(Biome biome) { vampireForest = new RealisticBiomeVAMPVampireForest(biome); }
}
