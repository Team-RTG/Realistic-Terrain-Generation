package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("unused")
public abstract class RealisticBiomeTCBase extends RealisticBiomeBase
{
    private static final String MAGFOR_BIOME_CLASS = "thaumcraft.common.world.biomes.BiomeGenMagicalForest";
    private static final String EERIE_BIOME_CLASS  = "thaumcraft.common.world.biomes.BiomeGenEerie";

    private static RealisticBiomeBase magicalForest;
    private static RealisticBiomeBase eerie;

    RealisticBiomeTCBase(Biome biome, Biome river) {

        super(biome, river);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    @Override public String modSlug() { return "thaumcraft"; }

    /**
     * Thaumcraft biomes with their default biome Ids:
     * 193: minecraft:magical_forest | Magical Forest
     * 194: minecraft:eerie          | Eerie
     * 195: minecraft:eldritch       | Outer Lands (not an Overworld biome)
     */

    public static void addBiomes() {

        if (Loader.isModLoaded("thaumcraft")) {

            for (Biome biome : Biome.REGISTRY) {

                ResourceLocation loc = biome.getRegistryName();
                if (loc == null) {
                    Logger.error("ResourceLocation is NULL for: \"{}\"[{}]", biome.getBiomeName(), biome.getBiomeClass().getName());
                    continue;
                }

// TODO: Replace check against class names with a check against the ResLoc domain when TC biomes are registered correctly.
                if (loc.getResourcePath().equals("magical_forest") &&
                    biome.getBiomeClass().getName().equals(MAGFOR_BIOME_CLASS)) { setMagicalForest(biome); }

                if (loc.getResourcePath().equals("eerie") &&
                    biome.getBiomeClass().getName().equals(EERIE_BIOME_CLASS)) { setEerie(biome); }
            }
        }
    }

    private static void setMagicalForest(Biome biome) { magicalForest = new RealisticBiomeTCMagicalForest(biome); }
    private static void setEerie(Biome biome)         { eerie = new RealisticBiomeTCEerie(biome); }
}
