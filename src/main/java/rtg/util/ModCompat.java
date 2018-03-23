package rtg.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import biomesoplenty.api.biome.BOPBiomes;
import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import rtg.api.util.Logger;
import rtg.api.util.UtilityClass;
import rtg.world.biome.realistic.RealisticBiomeBase;

@UtilityClass
public final class ModCompat
{
    private ModCompat() {}

    private static final int    ID_LENGTH     = 3;
    private static final String ID_FORMAT     = "%"+ID_LENGTH+"s";
    private static final int    NAME_LENGTH   = 32;
    private static final String NAME_FORMAT   = "%-"+NAME_LENGTH+"s";
    private static final int    RESLOC_LENGTH = 50;
    private static final String RESLOC_FORMAT = "%-"+RESLOC_LENGTH+"s";

    public static void doBiomeCheck() {

        List<Biome> invalidBiomes = Lists.newArrayList(Biomes.HELL, Biomes.SKY, Biomes.VOID);

        if (Mods.biomesoplenty.isLoaded()) {
            Lists.newArrayList(
                BOPBiomes.corrupted_sands.orNull(), BOPBiomes.fungi_forest.orNull(), BOPBiomes.phantasmagoric_inferno.orNull(),
                BOPBiomes.polar_chasm.orNull(), BOPBiomes.undergarden.orNull(), BOPBiomes.visceral_heap.orNull())
                .stream().filter(Objects::nonNull).forEach(invalidBiomes::add);
        }

        if (Mods.abyssalcraft.isLoaded()) {
            invalidBiomes.addAll(Lists.newArrayList(
                ACBiomes.abyssal_wastelands, ACBiomes.dark_realm, ACBiomes.dreadlands, ACBiomes.dreadlands_forest,
                ACBiomes.dreadlands_mountains, ACBiomes.omothol, ACBiomes.purified_dreadlands
            ));
        }

        Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();

        biomes = biomes.stream()
            .filter(b -> !invalidBiomes.contains(b) && !RealisticBiomeBase.isRealisticBiome(Biome.getIdForBiome(b)))
            .sorted(Comparator.comparingInt(Biome::getIdForBiome))
            .collect(Collectors.toList());

        // If there are no unsupported biomes, stop here.
        if (biomes.isEmpty()) { return; }

        Logger.warn(".= " + String.format(ID_FORMAT,     new String(new char[ID_LENGTH])    .replace('\0', '='))
                  + " = " + String.format(NAME_FORMAT,   new String(new char[NAME_LENGTH])  .replace('\0', '='))
                  + " = " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
                  + " =.");

        Logger.warn("|| " + String.format("%-91s",       "                RTG could not find realistic versions of the fallowing biomes")
                  + " ||");

        Logger.warn("|| " + String.format(ID_FORMAT,     new String(new char[ID_LENGTH])    .replace('\0', '='))
                  + " = " + String.format(NAME_FORMAT,   new String(new char[NAME_LENGTH])  .replace('\0', '='))
                  + " = " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
                  + " ||");

        Logger.warn("|| " + String.format(ID_FORMAT,     "ID")
                  + " | " + String.format(NAME_FORMAT,   "Biome Name")
                  + " | " + String.format(RESLOC_FORMAT, "Registry Name")
                  + " ||");

        Logger.warn("|| " + String.format(ID_FORMAT,     new String(new char[ID_LENGTH])    .replace('\0', '-'))
                  + " - " + String.format(NAME_FORMAT,   new String(new char[NAME_LENGTH])  .replace('\0', '-'))
                  + " - " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '-'))
                  + " ||");

        biomes.forEach(b-> Logger.warn(
                    "|| " + String.format(ID_FORMAT,     Biome.getIdForBiome(b))
                  + " | " + String.format(NAME_FORMAT,   b.getBiomeName())
                  + " | " + String.format(RESLOC_FORMAT, b.getRegistryName())
                  + " ||"));

        Logger.warn("`= " + String.format(ID_FORMAT,     new String(new char[ID_LENGTH])    .replace('\0', '='))
                  + " | " + String.format(NAME_FORMAT,   new String(new char[NAME_LENGTH])  .replace('\0', '='))
                  + " | " + String.format(RESLOC_FORMAT, new String(new char[RESLOC_LENGTH]).replace('\0', '='))
                  + " =`");
    }

    public static void init() { Mods.init(); }

    // TODO: 1.12 Verify mods in this enum. If 1.12 versions do not exist, remove support.
    // enum entries must match mod ids
    // optional 'friendly name' used for configs
    public enum Mods
    {
        abyssalcraft, agriculturalrevolution, arsmagica2, atg, betteragriculture, biomesoplenty, biomesyougo,
        buildcraft, cofhcore, floricraft, flowercraftmod("flowercraft"), iceandfire, jikou, mithwoodforest,
        morechinesemc, mw("mineworld"), reccomplex, rockhounding_surface, sugiforest, thaumcraft, vampirism,
        minecraft("vanilla");

        private boolean loaded;
        private final String prettyName;

        Mods() { this(""); }
        Mods(String name) { this.prettyName = (!name.isEmpty()) ? name : name(); }

        public boolean isLoaded() { return this.loaded; }
        public String getPrettyName() { return this.prettyName; }

        public static void init() { Arrays.stream(Mods.values()).forEach(mod -> mod.loaded = Loader.isModLoaded(mod.name())); }
    }
}
