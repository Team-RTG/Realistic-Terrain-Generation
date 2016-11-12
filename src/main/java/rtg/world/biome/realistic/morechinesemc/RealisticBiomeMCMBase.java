package rtg.world.biome.realistic.morechinesemc;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.morechinesemc.config.BiomeConfigMCM;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMCMBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mcmBlackPlain;
    public static RealisticBiomeBase mcmBog;
    public static RealisticBiomeBase mcmLoessPlateau;
    public static RealisticBiomeBase mcmMudFlat;
    public static RealisticBiomeBase mcmWarmTaiga;

    public RealisticBiomeMCMBase(BiomeConfig config, Biome b, Biome riverbiome) {

        super(config, b, riverbiome);

        this.waterSurfaceLakeChance = 30;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("morechinesemc")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Black Plain") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeBlackPlain")) {
                    mcmBlackPlain = new RealisticBiomeMCMBlackPlain(biome, BiomeConfigMCM.biomeConfigMCMBlackPlain);
                }
                else if (biomeName.equals("Bog") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeBog")) {
                    mcmBog = new RealisticBiomeMCMBog(biome, BiomeConfigMCM.biomeConfigMCMBog);
                }
                else if (biomeName.equals("Loess Plateau") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeWarmTaiga")) {
                    mcmLoessPlateau = new RealisticBiomeMCMLoessPlateau(biome, BiomeConfigMCM.biomeConfigMCMLoessPlateau);
                }
                else if (biomeName.equals("Mud Flat") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeMudFlat")) {
                    mcmMudFlat = new RealisticBiomeMCMMudFlat(biome, BiomeConfigMCM.biomeConfigMCMMudFlat);
                }
                else if (biomeName.equals("Warm Taiga") && biomeClass.equals("com.mcbbs.shp241.morechinesemc.world.BiomeWarmTaiga")) {
                    mcmWarmTaiga = new RealisticBiomeMCMWarmTaiga(biome, BiomeConfigMCM.biomeConfigMCMWarmTaiga);
                }
            }
        }
    }
}
