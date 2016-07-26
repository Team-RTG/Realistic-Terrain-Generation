package teamrtg.rtg.modules.abyssalcraft;

import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.world.biome.RTGBiome;

public abstract class RTGBiomeAC extends RTGBiome {

    public RTGBiomeAC(Biome b, Biome riverbiome) {

        super(Mods.ABYSSALCRAFT, b, riverbiome);
        config.WATER_POND_CHANCE.setDefault(0);
        config.LAVA_POND_CHANCE.setDefault(0);
    }
}
