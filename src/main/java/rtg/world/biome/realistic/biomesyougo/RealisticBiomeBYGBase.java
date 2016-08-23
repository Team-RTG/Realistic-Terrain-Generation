package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYG;
import rtg.util.BiomeUtils;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGBase extends RealisticBiomeBase {

    public static RealisticBiomeBase bygAutumnForest;
    public static RealisticBiomeBase bygBirchPlains;
    public static RealisticBiomeBase bygFrozenTundra;
    public static RealisticBiomeBase bygLushForest;
    public static RealisticBiomeBase bygRedDesert;
    public static RealisticBiomeBase bygRedRockMountains;
    public static RealisticBiomeBase bygWillowSwamps;

    public RealisticBiomeBYGBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("BiomesYouGo")) {

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
                    else if (biomeName.equals("birchPlains2") && biomeClass.equals("mod.mcreator.mcreator_birchPlains2$BiomeGenbirchPlains2")) {
                        bygBirchPlains = new RealisticBiomeBYGBirchPlains(biome, BiomeConfigBYG.biomeConfigBYGBirchPlains);
                    }
                    else if (biomeName.equals("frozenTundra") && biomeClass.equals("mod.mcreator.mcreator_frozenTundra$BiomeGenfrozenTundra")) {
                        bygFrozenTundra = new RealisticBiomeBYGFrozenTundra(biome, BiomeConfigBYG.biomeConfigBYGFrozenTundra);
                    }
                }
            }
        }
    }
}