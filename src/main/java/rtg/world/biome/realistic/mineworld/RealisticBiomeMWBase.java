package rtg.world.biome.realistic.mineworld;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMWBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mwAppleForest;
    public static RealisticBiomeBase mwArctic;
    public static RealisticBiomeBase mwDeadForest;
    public static RealisticBiomeBase mwIceHills;
    public static RealisticBiomeBase mwPalms;
    public static RealisticBiomeBase mwVolcano;

    public RealisticBiomeMWBase(Biome b, Biome riverbiome) {

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
        return Mods.mw.getPrettyName();
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 30;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Mods.mw.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Apple Forest") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenAppleForest")) {
                    mwAppleForest = new RealisticBiomeMWAppleForest(biome);
                }
                else if (biomeName.equals("Arctic") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenArctic")) {
                    mwArctic = new RealisticBiomeMWArctic(biome);
                }
                else if (biomeName.equals("Dead Forest") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenDeadForest")) {
                    mwDeadForest = new RealisticBiomeMWDeadForest(biome);
                }
                else if (biomeName.equals("Ice Hills") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenIceHills")) {
                    mwIceHills = new RealisticBiomeMWIceHills(biome);
                }
                else if (biomeName.equals("Palms") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenPalms")) {
                    mwPalms = new RealisticBiomeMWPalms(biome);
                }
                else if (biomeName.equals("Volcano") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenVolcano")) {
                    mwVolcano = new RealisticBiomeMWVolcano(biome);
                }
            }
        }
    }
}
