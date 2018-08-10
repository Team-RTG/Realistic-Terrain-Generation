package teamrtg.rtg.modules.bop;

import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.world.biome.RTGBiome;

public abstract class RTGBiomeBOP extends RTGBiome {

    public RTGBiomeBOP(Biome b, Biome riverbiome) {

        super(Mods.BOP, b, riverbiome);
        config.LAVA_POND_CHANCE.setDefault(0);
    }
}
