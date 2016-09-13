package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.agriculturalrevolution.config.BiomeConfigAR;
import rtg.util.BiomeUtils;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeARBase extends RealisticBiomeBase {

    public static RealisticBiomeBase arBambooGrove;
    public static RealisticBiomeBase arCoralReef;
    public static RealisticBiomeBase arDeepReef;
    public static RealisticBiomeBase arKelpForest;
    public static RealisticBiomeBase arOrchard;
    public static RealisticBiomeBase arTropicalHills;

    public RealisticBiomeARBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("CookingPlus")) {

            Biome[] b = BiomeUtils.getRegisteredBiomes();

            for (int i = 0; i < 256; i++) {

                if (b[i] != null) {

                    if (BiomeUtils.getName(b[i]) == null) {

                        Logger.warn("Biome ID %d has no name.", BiomeUtils.getId(b[i]));
                        continue;
                    }

                    Biome biome = b[i];
                    String biomeName = BiomeUtils.getName(biome);
                    String biomeClass = biome.getBiomeClass().getName();

                    if (biomeName.equals("Orchard") && biomeClass.equals("CookingPlus.generation.CookingPlusOrchardBiome")) {
                        arOrchard = new RealisticBiomeAROrchard(biome, BiomeConfigAR.biomeConfigAROrchard);
                    }
                    else if (biomeName.equals("Bamboo Grove") && biomeClass.equals("CookingPlus.generation.CookingPlusBambooBiome")) {
                        arBambooGrove = new RealisticBiomeARBambooGrove(biome, BiomeConfigAR.biomeConfigARBambooGrove);
                    }
                    else if (biomeName.equals("Kelp Forest") && biomeClass.equals("CookingPlus.generation.CookingPlusKelpForestBiome")) {
                        arKelpForest = new RealisticBiomeARKelpForest(biome, BiomeConfigAR.biomeConfigARKelpForest);
                    }
                    else if (biomeName.equals("Coral Reef") && biomeClass.equals("CookingPlus.generation.CookingPlusCoralReefBiome")) {
                        arCoralReef = new RealisticBiomeARCoralReef(biome, BiomeConfigAR.biomeConfigARCoralReef);
                    }
                    else if (biomeName.equals("Tropical Hills") && biomeClass.equals("CookingPlus.generation.CookingPlusTropicalBiome")) {
                        arTropicalHills = new RealisticBiomeARTropicalHills(biome, BiomeConfigAR.biomeConfigARTropicalHills);
                    }
                    else if (biomeName.equals("Deep Reef") && biomeClass.equals("CookingPlus.generation.CookingPlusDeepReefBiome")) {
                        arDeepReef = new RealisticBiomeARDeepReef(biome, BiomeConfigAR.biomeConfigARDeepReef);
                    }
                }
            }
        }
    }
}