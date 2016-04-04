package rtg.world.biome.realistic.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.mods.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

public abstract class RealisticBiomeVanillaBase extends RealisticBiomeBase {
    public static final int MUTATION_ADDEND = 128;

    public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome) {
        super(Mods.VANILLA, b, riverbiome);

        this.lavaSurfaceLakeChance = 0;
    }
}
