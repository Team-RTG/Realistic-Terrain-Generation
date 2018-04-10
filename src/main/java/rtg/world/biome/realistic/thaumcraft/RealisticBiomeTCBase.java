package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("unused")
public abstract class RealisticBiomeTCBase extends RealisticBiomeBase
{
    private static RealisticBiomeBase magicalForest;
    private static RealisticBiomeBase eerie;// Add Eerie biome, even though it doesn't generate normally

    RealisticBiomeTCBase(Biome biome, Biome river) {

        super(biome, river);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    @Override public String modSlug() { return "thaumcraft"; }

    public static void addBiomes() {

        if (Loader.isModLoaded("thaumcraft")) {

            for (Biome biome : Biome.REGISTRY) {

                ResourceLocation loc = biome.getRegistryName();
                if (loc == null) {
                    Logger.error("ResourceLocation is NULL for: \"{}\"[{}]", biome.getBiomeName(), biome.getBiomeClass().getName());
                    continue;
                }

                if (loc.getResourceDomain().equals("thaumcraft")) {
                    if (loc.getResourcePath().equals("magical_forest")) { setMagicalForest(biome); }
                    if (loc.getResourcePath().equals("eerie")) { setEerie(biome); }
                }
            }
        }
    }

    private static void setMagicalForest(Biome biome) { magicalForest = new RealisticBiomeTCMagicalForest(biome); }
    private static void setEerie(Biome biome)         { eerie = new RealisticBiomeTCEerie(biome); }
}
