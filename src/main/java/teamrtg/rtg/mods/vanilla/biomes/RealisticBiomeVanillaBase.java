package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

public abstract class RealisticBiomeVanillaBase extends RealisticBiomeBase {
    public static final int MUTATION_ADDEND = 128;

    public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome) {
        super(Mods.VANILLA, b, riverbiome);
        config.SURFACE_LAVA_LAKE_CHANCE.setDefault(0);
    }
}
