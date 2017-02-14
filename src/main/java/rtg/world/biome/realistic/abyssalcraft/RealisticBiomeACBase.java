package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.BiomeDictionary;
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

        this.getConfig().ALLOW_VILLAGES.set(false);
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
        return "abyssalcraft";
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("abyssalcraft")) {

            if (BiomeDictionary.isBiomeRegistered(ACBiomes.coralium_infested_swamp)) {
                acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp();
            }
            if (BiomeDictionary.isBiomeRegistered(ACBiomes.darklands)) {
                acDarklands = new RealisticBiomeACDarklands();
            }
            if (BiomeDictionary.isBiomeRegistered(ACBiomes.darklands_forest)) {
                acDarklandsForest = new RealisticBiomeACDarklandsForest();
            }
            if (BiomeDictionary.isBiomeRegistered(ACBiomes.darklands_hills)) {
                acDarklandsHighland = new RealisticBiomeACDarklandsHighland();
            }
            if (BiomeDictionary.isBiomeRegistered(ACBiomes.darklands_mountains)) {
                acDarklandsMountains = new RealisticBiomeACDarklandsMountains();
            }
            if (BiomeDictionary.isBiomeRegistered(ACBiomes.darklands_plains)) {
                acDarklandsPlains = new RealisticBiomeACDarklandsPlains();
            }
        }
    }
}
