package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.util.ModCompat;
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
        return ModCompat.abyssalcraft.getPrettyName();
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

        if (ModCompat.abyssalcraft.isLoaded()) {

            ResourceLocation rl;

            rl = ACBiomes.coralium_infested_swamp.getRegistryName();
            if (rl != null && Biome.REGISTRY.containsKey(rl)) {
                acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp();
            }

            rl = ACBiomes.darklands.getRegistryName();
            if (rl != null && Biome.REGISTRY.containsKey(rl)) {
                acDarklands = new RealisticBiomeACDarklands();
            }

            rl = ACBiomes.darklands_forest.getRegistryName();
            if (rl != null && Biome.REGISTRY.containsKey(rl)) {
                acDarklandsForest = new RealisticBiomeACDarklandsForest();
            }

            rl = ACBiomes.darklands_hills.getRegistryName();
            if (rl != null && Biome.REGISTRY.containsKey(rl)) {
                acDarklandsHighland = new RealisticBiomeACDarklandsHighland();
            }

            rl = ACBiomes.darklands_mountains.getRegistryName();
            if (rl != null && Biome.REGISTRY.containsKey(rl)) {
                acDarklandsMountains = new RealisticBiomeACDarklandsMountains();
            }

            rl = ACBiomes.darklands_plains.getRegistryName();
            if (rl != null && Biome.REGISTRY.containsKey(rl)) {
                acDarklandsPlains = new RealisticBiomeACDarklandsPlains();
            }
        }
    }
}
