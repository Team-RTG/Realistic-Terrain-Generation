package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
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

    public RealisticBiomeACBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("abyssalcraft")) {

            if (null != ACBiomes.coralium_infested_swamp) {
                acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp(BiomeConfigAC.biomeConfigACCoraliumInfestedSwamp);
            }
            if (null != ACBiomes.darklands) {
                acDarklands = new RealisticBiomeACDarklands(BiomeConfigAC.biomeConfigACDarklands);
            }
            if (null != ACBiomes.darklands_forest) {
                acDarklandsForest = new RealisticBiomeACDarklandsForest(BiomeConfigAC.biomeConfigACDarklandsForest);
            }
            if (null != ACBiomes.darklands_hills) {
                acDarklandsHighland = new RealisticBiomeACDarklandsHighland(BiomeConfigAC.biomeConfigACDarklandsHighland);
            }
            if (null != ACBiomes.darklands_mountains) {
                acDarklandsMountains = new RealisticBiomeACDarklandsMountains(BiomeConfigAC.biomeConfigACDarklandsMountains);
            }
            if (null != ACBiomes.darklands_plains) {
                acDarklandsPlains = new RealisticBiomeACDarklandsPlains(BiomeConfigAC.biomeConfigACDarklandsPlains);
            }
        }
    }
}
