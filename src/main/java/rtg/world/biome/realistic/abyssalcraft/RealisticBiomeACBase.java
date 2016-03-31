package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.Loader;
import rtg.api.config.BiomeConfig;
import rtg.api.config.abyssalcraft.config.BiomeConfigAC;
import rtg.util.BiomeUtils;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeACBase extends RealisticBiomeBase {

    public static RealisticBiomeBase acCoraliumInfestedSwamp;
    public static RealisticBiomeBase acDarklands;
    public static RealisticBiomeBase acDarklandsForest;
    public static RealisticBiomeBase acDarklandsHighland;
    public static RealisticBiomeBase acDarklandsMountains;
    public static RealisticBiomeBase acDarklandsPlains;

    public RealisticBiomeACBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {
        super(b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {
        if (Loader.isModLoaded("abyssalcraft")) {
            BiomeGenBase[] b = BiomeUtils.getRegisteredBiomes();

            for (int i = 0; i < 256; i++) {
                if (b[i] != null) {
                    if (b[i].getBiomeName() == null) {
                        Logger.warn("Biome ID %d has no name.", getIdForBiome(b[i]));
                        continue;
                    }

                    BiomeGenBase acBiome = b[i];
                    String biomeName = b[i].getBiomeName();
                    String biomeClass = b[i].getBiomeClass().getName();

                    if (biomeName == "Coralium Infested Swamp" && biomeClass == "com.shinoow.abyssalcraft.common.world.config.BiomeGenCorSwamp") {
                        acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp(acBiome, BiomeConfigAC.biomeConfigACCoraliumInfestedSwamp);
                    } else if (biomeName == "Darklands" && biomeClass == "com.shinoow.abyssalcraft.common.world.config.BiomeGenDarklands") {
                        acDarklands = new RealisticBiomeACDarklands(acBiome, BiomeConfigAC.biomeConfigACDarklands);
                    } else if (biomeName == "Darklands Forest" && biomeClass == "com.shinoow.abyssalcraft.common.world.config.BiomeGenDarklandsForest") {
                        acDarklandsForest = new RealisticBiomeACDarklandsForest(acBiome, BiomeConfigAC.biomeConfigACDarklandsForest);
                    } else if (biomeName == "Darklands Highland" && biomeClass == "com.shinoow.abyssalcraft.common.world.config.BiomeGenDarklandsHills") {
                        acDarklandsHighland = new RealisticBiomeACDarklandsHighland(acBiome, BiomeConfigAC.biomeConfigACDarklandsHighland);
                    } else if (biomeName == "Darklands Mountains" && biomeClass == "com.shinoow.abyssalcraft.common.world.config.BiomeGenDarklandsMountains") {
                        acDarklandsMountains = new RealisticBiomeACDarklandsMountains(acBiome, BiomeConfigAC.biomeConfigACDarklandsMountains);
                    } else if (biomeName == "Darklands Plains" && biomeClass == "com.shinoow.abyssalcraft.common.world.config.BiomeGenDarklandsPlains") {
                        acDarklandsPlains = new RealisticBiomeACDarklandsPlains(acBiome, BiomeConfigAC.biomeConfigACDarklandsPlains);
                    }
                }
            }
        }
    }
}
