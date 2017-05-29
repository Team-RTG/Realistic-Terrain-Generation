package rtg.world.biome.realistic.atg;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;


@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeATGBase extends RealisticBiomeBase {

    public static RealisticBiomeBase gravelBeach;
    public static RealisticBiomeBase scrubland;
    public static RealisticBiomeBase shrubland;
    public static RealisticBiomeBase snowyGravelBeach;
    public static RealisticBiomeBase tropicalShrubland;
    public static RealisticBiomeBase tundra;
    public static RealisticBiomeBase woodland;

    public RealisticBiomeATGBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
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
        return "atg";
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

        if (Loader.isModLoaded("atg")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Gravel Beach") && biomeClass.equals("ttftcuts.atg.biome.BiomeGravelBeach")) {
                    gravelBeach = new RealisticBiomeATGGravelBeach(biome);
                }
                else if (biomeName.equals("Scrubland") && biomeClass.equals("ttftcuts.atg.biome.BiomeScrubland")) {
                    scrubland = new RealisticBiomeATGScrubland(biome);
                }
                else if (biomeName.equals("Shrubland") && biomeClass.equals("ttftcuts.atg.biome.BiomeShrubland")) {
                    shrubland = new RealisticBiomeATGShrubland(biome);
                }
                else if (biomeName.equals("Snowy Gravel Beach") && biomeClass.equals("ttftcuts.atg.biome.BiomeSnowyGravelBeach")) {
                    snowyGravelBeach = new RealisticBiomeATGSnowyGravelBeach(biome);
                }
                else if (biomeName.equals("Tropical Shrubland") && biomeClass.equals("ttftcuts.atg.biome.BiomeTropicalShrubland")) {
                    tropicalShrubland = new RealisticBiomeATGTropicalShrubland(biome);
                }
                else if (biomeName.equals("Tundra") && biomeClass.equals("ttftcuts.atg.biome.BiomeTundra")) {
                    tundra = new RealisticBiomeATGTundra(biome);
                }
                else if (biomeName.equals("Woodland") && biomeClass.equals("ttftcuts.atg.biome.BiomeWoodland")) {
                    woodland = new RealisticBiomeATGWoodland(biome);
                }
            }
        }
    }
}
