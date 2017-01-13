package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
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

            if (ForgeRegistries.BIOMES.containsValue(ACBiomes.coralium_infested_swamp)) {
                acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp();
            }
            if (ForgeRegistries.BIOMES.containsValue(ACBiomes.darklands)) {
                acDarklands = new RealisticBiomeACDarklands();
            }
            if (ForgeRegistries.BIOMES.containsValue(ACBiomes.darklands_forest)) {
                acDarklandsForest = new RealisticBiomeACDarklandsForest();
            }
            if (ForgeRegistries.BIOMES.containsValue(ACBiomes.darklands_hills)) {
                acDarklandsHighland = new RealisticBiomeACDarklandsHighland();
            }
            if (ForgeRegistries.BIOMES.containsValue(ACBiomes.darklands_mountains)) {
                acDarklandsMountains = new RealisticBiomeACDarklandsMountains();
            }
            if (ForgeRegistries.BIOMES.containsValue(ACBiomes.darklands_plains)) {
                acDarklandsPlains = new RealisticBiomeACDarklandsPlains();
            }
        }
    }
}
