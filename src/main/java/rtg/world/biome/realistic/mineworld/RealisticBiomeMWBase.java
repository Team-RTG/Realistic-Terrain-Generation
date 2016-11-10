package rtg.world.biome.realistic.mineworld;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mineworld.BiomeConfigMW;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMWBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mwAppleForest;
    public static RealisticBiomeBase mwArctic;
    public static RealisticBiomeBase mwDeadForest;
    public static RealisticBiomeBase mwIceHills;
    public static RealisticBiomeBase mwPalms;
    public static RealisticBiomeBase mwVolcano;

    public RealisticBiomeMWBase(BiomeConfig config, Biome b, Biome riverbiome, SurfaceBase s) {

        super(config, b, riverbiome, s);

        this.waterSurfaceLakeChance = 30;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("mw")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Apple Forest") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenAppleForest")) {
                    mwAppleForest = new RealisticBiomeMWAppleForest(biome, BiomeConfigMW.biomeConfigMWAppleForest);
                }
                else if (biomeName.equals("Arctic") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenArctic")) {
                    mwArctic = new RealisticBiomeMWArctic(biome, BiomeConfigMW.biomeConfigMWArctic);
                }
                else if (biomeName.equals("Dead Forest") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenDeadForest")) {
                    mwDeadForest = new RealisticBiomeMWDeadForest(biome, BiomeConfigMW.biomeConfigMWDeadForest);
                }
                else if (biomeName.equals("Ice Hills") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenIceHills")) {
                    mwIceHills = new RealisticBiomeMWIceHills(biome, BiomeConfigMW.biomeConfigMWIceHills);
                }
                else if (biomeName.equals("Palms") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenPalms")) {
                    mwPalms = new RealisticBiomeMWPalms(biome, BiomeConfigMW.biomeConfigMWPalms);
                }
                else if (biomeName.equals("Volcano") && biomeClass.equals("com.mineworld.world.biomes.BiomeGenVolcano")) {
                    mwVolcano = new RealisticBiomeMWVolcano(biome, BiomeConfigMW.biomeConfigMWVolcano);
                }
            }
        }
    }
}
