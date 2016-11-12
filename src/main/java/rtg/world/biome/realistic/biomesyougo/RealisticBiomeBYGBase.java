package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYG;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeBYGBase extends RealisticBiomeBase {

    public static RealisticBiomeBase bygAutumnForest;
    public static RealisticBiomeBase bygBirchPlains;
    public static RealisticBiomeBase bygFrozenTundra;
    public static RealisticBiomeBase bygLushForest;
    public static RealisticBiomeBase bygRedDesert;
    public static RealisticBiomeBase bygRedRockMountains;
    public static RealisticBiomeBase bygWillowSwamps;

    public RealisticBiomeBYGBase(BiomeConfig config, Biome b, Biome riverbiome) {

        super(config, b, riverbiome);

        this.waterSurfaceLakeChance = 30;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("BiomesYouGo")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("autumnForest") && biomeClass.equals("mod.mcreator.mcreator_autumnForest$BiomeGenautumnForest")) {
                    bygAutumnForest = new RealisticBiomeBYGAutumnForest(biome, BiomeConfigBYG.biomeConfigBYGAutumnForest);
                }
                else if (biomeName.equals("birchPlains") && biomeClass.equals("mod.mcreator.mcreator_birchPlains$BiomeGenbirchPlains")) {
                    bygLushForest = new RealisticBiomeBYGLushForest(biome, BiomeConfigBYG.biomeConfigBYGLushForest);
                }
                else if (biomeName.equals("redRockMoutains") && biomeClass.equals("mod.mcreator.mcreator_redRockMoutains$BiomeGenredRockMoutains")) {
                    bygRedRockMountains = new RealisticBiomeBYGRedRockMountains(biome, BiomeConfigBYG.biomeConfigBYGRedRockMountains);
                }
                else if (biomeName.equals("redDesert") && biomeClass.equals("mod.mcreator.mcreator_redDesert$BiomeGenredDesert")) {
                    bygRedDesert = new RealisticBiomeBYGRedDesert(biome, BiomeConfigBYG.biomeConfigBYGRedDesert);
                }
                else if (biomeName.equals("willowSwamps") && biomeClass.equals("mod.mcreator.mcreator_willowSwamps$BiomeGenwillowSwamps")) {
                    bygWillowSwamps = new RealisticBiomeBYGWillowSwamps(biome, BiomeConfigBYG.biomeConfigBYGWillowSwamps);
                }
                else if (biomeName.equals("birchPlains1") && biomeClass.equals("mod.mcreator.mcreator_birchPlains1$BiomeGenbirchPlains1")) {
                    bygBirchPlains = new RealisticBiomeBYGBirchPlains(biome, BiomeConfigBYG.biomeConfigBYGBirchPlains);
                }
                else if (biomeName.equals("tundra") && biomeClass.equals("mod.mcreator.mcreator_tundra$BiomeGentundra")) {
                    bygFrozenTundra = new RealisticBiomeBYGFrozenTundra(biome, BiomeConfigBYG.biomeConfigBYGFrozenTundra);
                }
                else if (biomeName.equals("athuraForest") && biomeClass.equals("mod.mcreator.mcreator_athuraForest$BiomeGenathuraForest")) {
                    bygFrozenTundra = new RealisticBiomeBYGAthuraForest(biome, BiomeConfigBYG.biomeConfigBYGAthuraForest);
                }
                else if (biomeName.equals("mushroomMoutains") && biomeClass.equals("mod.mcreator.mcreator_mushroomMoutains$BiomeGenmushroomMoutains")) {
                    bygFrozenTundra = new RealisticBiomeBYGMushroomMountains(biome, BiomeConfigBYG.biomeConfigBYGMushroomMountains);
                }
                else if (biomeName.equals("shrubs") && biomeClass.equals("mod.mcreator.mcreator_shrubs$BiomeGenshrubs")) {
                    bygFrozenTundra = new RealisticBiomeBYGShrubs(biome, BiomeConfigBYG.biomeConfigBYGShrubs);
                }
            }
        }
    }
}
