package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

public abstract class RealisticBiomeVanillaBase extends RealisticBiomeBase {
    public static final int MUTATION_ADDEND = 128;

    public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome, ChunkProviderRTG chunkProvider) {
        super(Mods.VANILLA, b, riverbiome, chunkProvider);
        config.LAVA_POND_CHANCE.setDefault(0);
    }
}
