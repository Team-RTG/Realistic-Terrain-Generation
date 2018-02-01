package rtg.util;

import java.util.Arrays;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.UtilityClass;

// TODO: 1.12 Verify mods in this enum. If 1.12 versions do not exist, remove support.
// enum entries must match mod ids
// optional 'friendly name' used for configs
@UtilityClass
public enum ModCompat {

    abyssalcraft(),
    agriculturalrevolution(),
    arsmagica2(),
    atg(),
    betteragriculture(),
    biomesoplenty(),
    biomesyougo(),
    cofhcore(),
    floricraft(),
    flowercraftmod("flowercraft"),
    iceandfire(),
    jikou(),
    mithwoodforest(),
    morechinesemc(),
    mw("mineworld"),
    reccomplex(),
    rockhounding_surface(),
    sugiforest(),
    vampirism();

    private boolean loaded;
    private final String prettyName;

    ModCompat() {
        this(null);
    }

    ModCompat(String name) {
        this.prettyName = (name != null) ? name : name();
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public String getPrettyName() {
        return this.prettyName;
    }

    public static void init() {
        Arrays.stream(values()).forEach(mod -> mod.loaded = Loader.isModLoaded(mod.name()));
    }
}
