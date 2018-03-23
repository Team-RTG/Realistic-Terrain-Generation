package rtg.world.biome.realistic.morechinesemc;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMCMBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mcmBlackPlain;
    public static RealisticBiomeBase mcmBog;
    public static RealisticBiomeBase mcmLoessPlateau;
    public static RealisticBiomeBase mcmMudFlat;
    public static RealisticBiomeBase mcmWarmTaiga;

    public RealisticBiomeMCMBase(Biome b, Biome riverbiome) {

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
        return Mods.morechinesemc.getPrettyName();
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

        if (Mods.morechinesemc.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Black Plain") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeBlackPlain")) {
                    mcmBlackPlain = new RealisticBiomeMCMBlackPlain(biome);
                }
                else if (biomeName.equals("Bog") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeBog")) {
                    mcmBog = new RealisticBiomeMCMBog(biome);
                }
                else if (biomeName.equals("Loess Plateau") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeWarmTaiga")) {
                    mcmLoessPlateau = new RealisticBiomeMCMLoessPlateau(biome);
                }
                else if (biomeName.equals("Mud Flat") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeMudFlat")) {
                    mcmMudFlat = new RealisticBiomeMCMMudFlat(biome);
                }
                else if (biomeName.equals("Warm Taiga") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeWarmTaiga")) {
                    mcmWarmTaiga = new RealisticBiomeMCMWarmTaiga(biome);
                }
            }
        }
    }
}
