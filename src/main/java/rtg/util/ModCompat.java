package rtg.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import rtg.RTGConfig;
import rtg.api.RTGAPI;
import rtg.api.util.Logger;
import rtg.api.util.UtilityClass;

@UtilityClass
public final class ModCompat {

    private static final int ID_LENGTH = 3;
    private static final String ID_FORMAT = "%" + ID_LENGTH + "s";
    private static final int NAME_LENGTH = 32;
    private static final String NAME_FORMAT = "%-" + NAME_LENGTH + "s";
    private static final int RESLOC_LENGTH = 50;
    private static final String RESLOC_FORMAT = "%-" + RESLOC_LENGTH + "s";

    private static final int BIOME_NAME_LENGTH = 24;
    private static final String BIOME_NAME_FORMAT = "%-" + BIOME_NAME_LENGTH + "s";
    private static final int BIOME_CLASS_LENGTH = 32;
    private static final String BIOME_CLASS_FORMAT = "%-" + BIOME_CLASS_LENGTH + "s";
    private static final int BIOME_RESLOC_LENGTH = 44;
    private static final String BIOME_RESLOC_FORMAT = "%-" + BIOME_RESLOC_LENGTH + "s";
    private static final int BEACH_NAME_LENGTH = 24;
    private static final String BEACH_NAME_FORMAT = "%-" + BEACH_NAME_LENGTH + "s";

    private ModCompat() {

    }

    public static void doBiomeCheck() {

        List<Biome> invalidBiomes = Lists.newArrayList(Biomes.HELL, Biomes.SKY, Biomes.VOID);

        if (Mods.abyssalcraft.isLoaded()) {
            invalidBiomes.addAll(Arrays.asList(
                ACBiomes.abyssal_wastelands,
                ACBiomes.dark_realm,
                ACBiomes.dreadlands,
                ACBiomes.dreadlands_forest,
                ACBiomes.dreadlands_mountains,
                ACBiomes.omothol,
                ACBiomes.purified_dreadlands
            ));
        }

        if (Mods.biomesoplenty.isLoaded()) {

            String modid = Mods.biomesoplenty.name();
            Stream.of(
                    new ResourceLocation(modid, "corrupted_sands"),
                    new ResourceLocation(modid, "fungi_forest"),
                    new ResourceLocation(modid, "phantasmagoric_inferno"),
                    new ResourceLocation(modid, "undergarden"),
                    new ResourceLocation(modid, "visceral_heap")
            )
                    .map(Biome.REGISTRY::getObject)
                    .filter(Objects::nonNull)
                    .forEach(invalidBiomes::add);
        }

        if (Mods.byg.isLoaded()) {

            String modid = Mods.byg.name();
            Stream.of(
                    new ResourceLocation(modid, "babyssalbog"),
                    new ResourceLocation(modid, "bastralisle"),
                    new ResourceLocation(modid, "bcosmicocean"),
                    new ResourceLocation(modid, "bshattereddesert")
            )
                    .map(Biome.REGISTRY::getObject)
                    .filter(Objects::nonNull)
                    .forEach(invalidBiomes::add);
        }

        if (Mods.fyrecraft.isLoaded()) {

            String modid = Mods.fyrecraft.name();
            Stream.of(
                new ResourceLocation(modid, "miner's caves"),
                new ResourceLocation(modid, "waterfalls")
            )
                .map(Biome.REGISTRY::getObject)
                .filter(Objects::nonNull)
                .forEach(invalidBiomes::add);
        }

        if (Mods.thaumcraft.isLoaded()) {

            String modid = Mods.thaumcraft.name();
            Stream.of(
                    new ResourceLocation(modid, "eldritch")
            )
                    .map(Biome.REGISTRY::getObject)
                    .filter(Objects::nonNull)
                    .forEach(invalidBiomes::add);
        }

        // TODO: Add other biome exceptions. AE2 storage biome, Twilight Forest, etc..

        Collection<Biome> unsupportedBiomes = ForgeRegistries.BIOMES.getValuesCollection();

        List<Biome> supportedBiomes = Lists.newArrayList(ForgeRegistries.BIOMES.getValuesCollection());
        final String[] supported = {""};

        unsupportedBiomes = unsupportedBiomes.stream()
            .filter(b -> !invalidBiomes.contains(b) && !RTGAPI.RTG_BIOMES.containsKey(b))
            .sorted(Comparator.comparingInt(Biome::getIdForBiome))
            .collect(Collectors.toList());

        supportedBiomes.removeAll(invalidBiomes);
        supportedBiomes.removeAll(unsupportedBiomes);

        if (!unsupportedBiomes.isEmpty()) {

            Logger.warn(".= " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
                    + " = " + String.format(NAME_FORMAT, new String(new char[NAME_LENGTH]).replace('\0', '='))
                    + " = " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
                    + " =.");

            Logger.warn("|| " + String.format("%-91s", "                RTG could not find realistic versions of the following biomes")
                    + " ||");

            Logger.warn("|| " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
                    + " = " + String.format(NAME_FORMAT, new String(new char[NAME_LENGTH]).replace('\0', '='))
                    + " = " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
                    + " ||");

            Logger.warn("|| " + String.format(ID_FORMAT, "ID")
                    + " | " + String.format(NAME_FORMAT, "Biome Class")
                    + " | " + String.format(RESLOC_FORMAT, "Registry Name")
                    + " ||");

            Logger.warn("|| " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '-'))
                    + " - " + String.format(NAME_FORMAT, new String(new char[NAME_LENGTH]).replace('\0', '-'))
                    + " - " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '-'))
                    + " ||");

            unsupportedBiomes.forEach(b -> Logger.warn(
                    "|| " + String.format(ID_FORMAT, Biome.getIdForBiome(b))
                    + " | " + String.format(NAME_FORMAT, b.getBiomeClass().getSimpleName())
                    + " | " + String.format(RESLOC_FORMAT, b.getRegistryName())
                    + " ||"));

            Logger.warn("`= " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
                    + " | " + String.format(NAME_FORMAT, new String(new char[NAME_LENGTH]).replace('\0', '='))
                    + " | " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
                    + " =`");
        }

        if (RTGConfig.additionalBiomeInfo()) {
            if (!supportedBiomes.isEmpty()) {

                supported[0] += System.lineSeparator() + ".= " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_NAME_FORMAT, new String(new char[BIOME_NAME_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_CLASS_FORMAT, new String(new char[BIOME_CLASS_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_RESLOC_FORMAT, new String(new char[BIOME_RESLOC_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BEACH_NAME_FORMAT, new String(new char[BEACH_NAME_LENGTH]).replace('\0', '='))
                    + " =.";

                supported[0] += System.lineSeparator() + "|| " + String.format("%-139s", "                                           RTG found realistic versions of the following biomes")
                    + " ||";

                supported[0] += System.lineSeparator() + "|| " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_NAME_FORMAT, new String(new char[BIOME_NAME_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_CLASS_FORMAT, new String(new char[BIOME_CLASS_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_RESLOC_FORMAT, new String(new char[BIOME_RESLOC_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BEACH_NAME_FORMAT, new String(new char[BEACH_NAME_LENGTH]).replace('\0', '='))
                    + " ||";

                supported[0] += System.lineSeparator() + "|| " + String.format(ID_FORMAT, "ID")
                    + " | " + String.format(BIOME_NAME_FORMAT, "Biome Name")
                    + " | " + String.format(BIOME_CLASS_FORMAT, "Class Name")
                    + " | " + String.format(BIOME_RESLOC_FORMAT, "Registry Name")
                    + " | " + String.format(BEACH_NAME_FORMAT, "Beach Name")
                    + " ||";

                supported[0] += System.lineSeparator() + "|| " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '-'))
                    + " - " + String.format(BIOME_NAME_FORMAT, new String(new char[BIOME_NAME_LENGTH]).replace('\0', '-'))
                    + " - " + String.format(BIOME_CLASS_FORMAT, new String(new char[BIOME_CLASS_LENGTH]).replace('\0', '-'))
                    + " - " + String.format(BIOME_RESLOC_FORMAT, new String(new char[BIOME_RESLOC_LENGTH]).replace('\0', '-'))
                    + " - " + String.format(BEACH_NAME_FORMAT, new String(new char[BEACH_NAME_LENGTH]).replace('\0', '-'))
                    + " ||";

                supportedBiomes.forEach(b -> supported[0] +=
                    System.lineSeparator() + "|| " + String.format(ID_FORMAT, Biome.getIdForBiome(b))
                    + " | " + String.format(BIOME_NAME_FORMAT, b.biomeName)
                    + " | " + String.format(BIOME_CLASS_FORMAT, b.getBiomeClass().getSimpleName())
                    + " | " + String.format(BIOME_RESLOC_FORMAT, b.getRegistryName())
                    + " | " + String.format(BEACH_NAME_FORMAT, RTGAPI.getRTGBiome(b).getBeachBiome().baseBiome().biomeName)
                    + " ||");

                supported[0] += System.lineSeparator() + ".= " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_NAME_FORMAT, new String(new char[BIOME_NAME_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_CLASS_FORMAT, new String(new char[BIOME_CLASS_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BIOME_RESLOC_FORMAT, new String(new char[BIOME_RESLOC_LENGTH]).replace('\0', '='))
                    + " = " + String.format(BEACH_NAME_FORMAT, new String(new char[BEACH_NAME_LENGTH]).replace('\0', '='))
                    + " =." + System.lineSeparator();

                Logger.info(supported[0]);
            }
        }
    }

    public static void init() {
        Mods.init();
    }

    // enum entries must match mod ids
    // optional 'friendly name' used for configs
    public enum Mods {
        abyssalcraft,
        auxbiomes,
        betteragriculture,
        biomesoplenty,
        byg,
        bionisation3,
        buildcraftenergy,
        candymod,
        defiledlands,
        douglas_forest,
        environs,
        explorercraft,
        floricraft,
        fyrecraft,
        gravityfalls,
        iceandfire,
        minecraft("vanilla"),
        mistbiomes,
        nt("novamterram"),
        odioitamod,
        plants2,
        pvj,
        realworld,
        redwoods,
        rockhounding_surface,
        spookybiomes,
        sugiforest,
        terscraft,
        thaumcraft,
        traverse,
        vampirism,
        valoegheses_be("zoesteria");

        private final String prettyName;
        private boolean loaded;

        Mods() {
            this("");
        }

        Mods(String name) {
            this.prettyName = (!name.isEmpty()) ? name : name();
        }

        public static void init() {
            Arrays.stream(Mods.values()).forEach(mod -> mod.loaded = Loader.isModLoaded(mod.name()));
        }

        public static Mods get(final String modId) {
            return Arrays.stream(values())
                .filter(mod -> mod.name().equals(modId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Mod does not exist in Mods enum."));
        }

        public boolean isLoaded() {
            return this.loaded;
        }

        public String getPrettyName() {
            return this.prettyName;
        }
    }
}
