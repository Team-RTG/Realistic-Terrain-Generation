package rtg.world.biome.realistic.vanilla;

import rtg.api.mods.ModBiomes;

/**
 * @author topisani
 */
public class RealisticVanillaBiomes extends ModBiomes {

    public RealisticBiomeVanillaBeach BEACH;

    @Override
    public void initBiomes() {
        addBiome(BEACH = new RealisticBiomeVanillaBeach());
    }
}
