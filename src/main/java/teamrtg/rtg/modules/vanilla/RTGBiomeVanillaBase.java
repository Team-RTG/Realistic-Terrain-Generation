package teamrtg.rtg.modules.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.world.biome.RTGBiomeBase;

public abstract class RTGBiomeVanillaBase extends RTGBiomeBase {
    public static final int MUTATION_ADDEND = 128;

    public RTGBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome) {
        super(Mods.VANILLA, b, riverbiome);
        config.LAVA_POND_CHANCE.setDefault(0);
    }
}
