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

    private ModCompat() {
    }

    public static void doBiomeCheck() {

        List<Biome> invalidBiomes = Lists.newArrayList(Biomes.HELL, Biomes.SKY, Biomes.VOID);

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

        // TODO: [1.12] Add other biome exceptions. AE2 storage biome, Twilight Forest, etc..

        Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();

        biomes = biomes.stream()
            .filter(b -> !invalidBiomes.contains(b) && !RTGAPI.RTG_BIOMES.containsKey(b))
            .sorted(Comparator.comparingInt(Biome::getIdForBiome))
            .collect(Collectors.toList());

        // If there are no unsupported biomes, stop here.
        if (biomes.isEmpty()) {
            return;
        }

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

        biomes.forEach(b -> Logger.warn(
            "|| " + String.format(ID_FORMAT, Biome.getIdForBiome(b))
                + " | " + String.format(NAME_FORMAT, b.getBiomeClass().getSimpleName())
                + " | " + String.format(RESLOC_FORMAT, b.getRegistryName())
                + " ||"));

        Logger.warn("`= " + String.format(ID_FORMAT, new String(new char[ID_LENGTH]).replace('\0', '='))
            + " | " + String.format(NAME_FORMAT, new String(new char[NAME_LENGTH]).replace('\0', '='))
            + " | " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
            + " =`");
    }

    public static void init() {
        Mods.init();
    }

    // enum entries must match mod ids
    // optional 'friendly name' used for configs
    public enum Mods {
        abyssalcraft,
        biomesoplenty,
        buildcraftenergy("buildcraft"),
        plants2("plants"),
        realworld,
        thaumcraft,
        traverse,
        vampirism,
        minecraft("vanilla");

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

        public boolean isLoaded() {
            return this.loaded;
        }

        public String getPrettyName() {
            return this.prettyName;
        }
    }
}
