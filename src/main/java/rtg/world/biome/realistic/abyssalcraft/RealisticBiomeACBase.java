package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.world.biome.realistic.RealisticBiomeBase;

public abstract class RealisticBiomeACBase extends RealisticBiomeBase {

    public static RealisticBiomeBase acCoraliumInfestedSwamp;
    public static RealisticBiomeBase acDarklands;
    public static RealisticBiomeBase acDarklandsForest;
    public static RealisticBiomeBase acDarklandsHighland;
    public static RealisticBiomeBase acDarklandsMountains;
    public static RealisticBiomeBase acDarklandsPlains;

    public RealisticBiomeACBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;

        this.getConfig().ALLOW_VILLAGES.set(false);
    }

    @Override
    public String modSlug() {
        return "abyssalcraft";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("abyssalcraft")) {

            if (null != ACBiomes.coralium_infested_swamp) {
                acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp();
            }
            if (null != ACBiomes.darklands) {
                acDarklands = new RealisticBiomeACDarklands();
            }
            if (null != ACBiomes.darklands_forest) {
                acDarklandsForest = new RealisticBiomeACDarklandsForest();
            }
            if (null != ACBiomes.darklands_hills) {
                acDarklandsHighland = new RealisticBiomeACDarklandsHighland();
            }
            if (null != ACBiomes.darklands_mountains) {
                acDarklandsMountains = new RealisticBiomeACDarklandsMountains();
            }
            if (null != ACBiomes.darklands_plains) {
                acDarklandsPlains = new RealisticBiomeACDarklandsPlains();
            }
        }
    }
}
