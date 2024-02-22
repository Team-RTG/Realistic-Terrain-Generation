package rtg.util;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.collect.Lists;
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
            invalidBiomes.addAll(
                Stream.of(
                    Mods.abyssalcraft.getResourceLocation("abyssal_wastelands"),
                    Mods.abyssalcraft.getResourceLocation("dark_realm"),
                    Mods.abyssalcraft.getResourceLocation("dreadlands"),
                    Mods.abyssalcraft.getResourceLocation("dreadlands_forest"),
                    Mods.abyssalcraft.getResourceLocation("dreadlands_mountains"),
                    Mods.abyssalcraft.getResourceLocation("omothol"),
                    Mods.abyssalcraft.getResourceLocation("purified_dreadlands"),
                    Mods.abyssalcraft.getResourceLocation("purged")
                )
                    .map(ForgeRegistries.BIOMES::getValue)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
            );
        }

        if (Mods.biomesoplenty.isLoaded()) {
            invalidBiomes.addAll(
                Stream.of(
                    Mods.biomesoplenty.getResourceLocation("corrupted_sands"),
                    Mods.biomesoplenty.getResourceLocation("fungi_forest"),
                    Mods.biomesoplenty.getResourceLocation("phantasmagoric_inferno"),
                    Mods.biomesoplenty.getResourceLocation("undergarden"),
                    Mods.biomesoplenty.getResourceLocation("visceral_heap")
                )
                    .map(ForgeRegistries.BIOMES::getValue)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
            );
        }

        if (Mods.byg.isLoaded()) {
            invalidBiomes.addAll(
                Stream.of(
                    Mods.byg.getResourceLocation("babyssalbog"),
                    Mods.byg.getResourceLocation("bastralisle"),
                    Mods.byg.getResourceLocation("bcosmicocean"),
                    Mods.byg.getResourceLocation("bshattereddesert")
                )
                    .map(ForgeRegistries.BIOMES::getValue)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
            );
        }

        if (Mods.fyrecraft.isLoaded()) {
            invalidBiomes.addAll(
            Stream.of(
                Mods.fyrecraft.getResourceLocation("miner's caves"),
                Mods.fyrecraft.getResourceLocation("waterfalls")
            )
                .map(Biome.REGISTRY::getObject)
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
            );
        }

        if (Mods.thaumcraft.isLoaded()) {
            final Biome biome;
            if ((biome = ForgeRegistries.BIOMES.getValue(Mods.thaumcraft.getResourceLocation("eldritch"))) != null) {
                invalidBiomes.add(biome);
            }
        }

        //noinspection ConstantConditions (Biome#getRegistryName can never return null here)
        RTGConfig.getBlacklistMods().stream()
            .filter(Loader::isModLoaded)
            .map(modId -> ForgeRegistries.BIOMES.getValuesCollection()
                .stream()
                .filter(biome -> biome.getRegistryName().getNamespace().equals(modId))
                .collect(Collectors.toList()))
            .flatMap(Collection::stream)
            .forEach(invalidBiomes::add);

        // TODO: Add other biome exceptions. AE2 storage biome, Twilight Forest, etc..

        final Collection<Biome> unsupportedBiomes = ForgeRegistries.BIOMES.getValuesCollection()
            .stream()
            .filter(b -> !invalidBiomes.contains(b) && !RTGAPI.RTG_BIOMES.containsKey(b))
            .sorted(Comparator.comparingInt(Biome::getIdForBiome))
            .collect(Collectors.toList());

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

            Collection<Biome> supportedBiomes = ForgeRegistries.BIOMES.getValuesCollection()
                .stream()
                .filter(RTGAPI.RTG_BIOMES::containsKey)
                .sorted(Comparator.comparingInt(Biome::getIdForBiome))
                .collect(Collectors.toList());

            if (!supportedBiomes.isEmpty()) {
                String[] supported = {""};

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

    public static void init() { Mods.init(); }

    // enum entries must match mod ids
    // optional 'friendly name' used for configs
    public enum Mods {
        abyssalcraft,
        auxbiomes,
        betteragriculture,
        biomesoplenty,
        byg("biomesyougo"),
        bionisation3,
        buildcraftenergy,
        candymod,
        defiledlands,
        douglas_forest,
        environs,
        explorercraft,
        floricraft,
        fyrecraft,
        geographicraft,
        gravityfalls,
        minecraft,
        mistbiomes,
        nt("novamterram"),
        odioitamod,
        plants2,
        pvj("vibrantjourneys"),
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

        Mods() { this(""); }

        Mods(String name) { this.prettyName = (!name.isEmpty()) ? name : name(); }

        private static void init() {
            Arrays.stream(Mods.values()).forEach(mod -> mod.loaded = Loader.isModLoaded(mod.name()));
        }

        @Nullable
        public static Mods get(final String modId) {
            return Arrays.stream(values())
                .filter(mod -> mod.name().equals(modId))
                .findFirst()
                .orElse(null);
        }

        public boolean isLoaded() {
            return this.loaded;
        }

        public ResourceLocation getResourceLocation(final String biome)
        {
            return new ResourceLocation(name() + ":" + biome);
        }

        public String getPrettyName() {
            return this.prettyName;
        }
    }
}
