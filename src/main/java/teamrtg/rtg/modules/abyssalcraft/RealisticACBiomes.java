package teamrtg.rtg.modules.abyssalcraft;

import teamrtg.rtg.api.module.ModBiomes;
import teamrtg.rtg.modules.abyssalcraft.biomes.*;

/**
 * @author WhichOnesPink
 */
public class RealisticACBiomes extends ModBiomes {

    public RTGBiomeACCoraliumInfestedSwamp CORALIUM_INFESTED_SWAMP;
    public RTGBiomeACDarklands DARKLANDS;
    public RTGBiomeACDarklandsForest DARKLANDS_FOREST;
    public RTGBiomeACDarklandsHighland DARKLANDS_HIGHLAND;
    public RTGBiomeACDarklandsMountains DARKLANDS_MOUNTAINS;
    public RTGBiomeACDarklandsPlains DARKLANDS_PLAINS;

    @Override
    public void initBiomes() {
        addBiome(CORALIUM_INFESTED_SWAMP = new RTGBiomeACCoraliumInfestedSwamp());
        addBiome(DARKLANDS = new RTGBiomeACDarklands());
        addBiome(DARKLANDS_FOREST = new RTGBiomeACDarklandsForest());
        addBiome(DARKLANDS_HIGHLAND = new RTGBiomeACDarklandsHighland());
        addBiome(DARKLANDS_MOUNTAINS = new RTGBiomeACDarklandsMountains());
        addBiome(DARKLANDS_PLAINS = new RTGBiomeACDarklandsPlains());
    }
}
