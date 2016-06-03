package teamrtg.rtg.modules.vanilla;

import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.world.biome.RTGBiomeBase;

public abstract class RTGBiomeVanillaBase extends RTGBiomeBase {
    public static final int MUTATION_ADDEND = 128;

    public RTGBiomeVanillaBase(Biome b, Biome riverbiome) {
        super(Mods.VANILLA, b, riverbiome);
        config.LAVA_POND_CHANCE.setDefault(0);
    }
}
