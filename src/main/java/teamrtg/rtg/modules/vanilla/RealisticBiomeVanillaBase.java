package teamrtg.rtg.modules.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.world.biome.RealisticBiomeBase;

public abstract class RealisticBiomeVanillaBase extends RealisticBiomeBase {
    public static final int MUTATION_ADDEND = 128;

    public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome) {
        super(Mods.VANILLA, b, riverbiome);
        config.LAVA_POND_CHANCE.setDefault(0);
    }
}
