package teamrtg.rtg.api.util;

import net.minecraftforge.fml.common.Loader;

/**
 * @author Zeno410
 */
public class ModPresenceTester {
    public final String name;
    private boolean unknown = true;
    private boolean present;

    public ModPresenceTester(String name) {
        this.name = name;
    }

    public boolean present() {
        if (unknown) {
            present = Loader.isModLoaded(name);
            unknown = false;
        }
        return present;
    }
}
