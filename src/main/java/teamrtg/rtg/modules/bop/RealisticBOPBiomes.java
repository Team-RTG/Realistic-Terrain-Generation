package teamrtg.rtg.modules.bop;

import teamrtg.rtg.api.module.ModBiomes;
import teamrtg.rtg.modules.bop.biomes.*;

/**
 * @author WhichOnesPink
 */
public class RealisticBOPBiomes extends ModBiomes {

    // normal biomes which have weights
    public RTGBiomeBOPAlps ALPS;
    public RTGBiomeBOPBambooForest BAMBOO_FOREST;
    public RTGBiomeBOPBayou BAYOU;
    public RTGBiomeBOPBog BOG;
    public RTGBiomeBOPBorealForest BOREAL_FOREST;
    public RTGBiomeBOPBrushland BRUSHLAND;
    public RTGBiomeBOPChaparral CHAPARRAL;
    public RTGBiomeBOPCherryBlossomGrove CHERRY_BLOSSOM_GROVE;
    public RTGBiomeBOPColdDesert COLD_DESERT;
    public RTGBiomeBOPConiferousForest CONIFEROUS_FOREST;
    public RTGBiomeBOPCrag CRAG;
    public RTGBiomeBOPDeadForest DEAD_FOREST;
    public RTGBiomeBOPDeadSwamp DEAD_SWAMP;
    public RTGBiomeBOPEucalyptusForest EUCALYPTUS_FOREST;
    public RTGBiomeBOPFen FEN;
    public RTGBiomeBOPFlowerField FLOWER_FIELD;
    public RTGBiomeBOPGrassland GRASSLAND;
    public RTGBiomeBOPGrove GROVE;
    public RTGBiomeBOPHeathland HEATHLAND;
    public RTGBiomeBOPHighland HIGHLAND;
    public RTGBiomeBOPLandOfLakes LAND_OF_LAKES;
    public RTGBiomeBOPLavenderFields LAVENDER_FIELDS;
    public RTGBiomeBOPLushDesert LUSH_DESERT;
    public RTGBiomeBOPLushSwamp LUSH_SWAMP;
    public RTGBiomeBOPMapleWoods MAPLE_WOODS;
    public RTGBiomeBOPMarsh MARSH;
    public RTGBiomeBOPMeadow MEADOW;
    public RTGBiomeBOPMoor MOOR;
    public RTGBiomeBOPMountain MOUNTAIN;
    public RTGBiomeBOPMysticGrove MYSTIC_GROVE;
    public RTGBiomeBOPOminousWoods OMINOUS_WOODS;
    public RTGBiomeBOPOrchard ORCHARD;
    public RTGBiomeBOPOutback OUTBACK;
    public RTGBiomeBOPOvergrownCliffs OVERGROWN_CLIFFS;
    public RTGBiomeBOPPrairie PRAIRIE;
    public RTGBiomeBOPQuagmire QUAGMIRE;
    public RTGBiomeBOPRainforest RAINFOREST;
    public RTGBiomeBOPRedwoodForest REDWOOD_FOREST;
    public RTGBiomeBOPSacredSprings SACRED_SPRINGS;
    public RTGBiomeBOPSeasonalForest SEASONAL_FOREST;
    public RTGBiomeBOPShield SHIELD;
    public RTGBiomeBOPShrubland SHRUBLAND;
    public RTGBiomeBOPSnowyConiferousForest SNOWY_CONIFEROUS_FOREST;
    public RTGBiomeBOPSnowyForest SNOWY_FOREST;
    public RTGBiomeBOPSteppe STEPPE;
    public RTGBiomeBOPTemperateRainforest TEMPERATE_RAINFOREST;
    public RTGBiomeBOPTropicalRainforest TROPICAL_RAINFOREST;
    public RTGBiomeBOPTundra TUNDRA;
    public RTGBiomeBOPWasteland WASTELAND;
    public RTGBiomeBOPWetland WETLAND;
    public RTGBiomeBOPWoodland WOODLAND;
    public RTGBiomeBOPXericShrubland XERIC_SHRUBLAND;

    // edge-biomes, sub-biomes and mutated-biomes
    public RTGBiomeBOPMountainFoothills MOUNTAIN_FOOTHILLS;
    public RTGBiomeBOPGlacier GLACIER;
    public RTGBiomeBOPOasis OASIS;
    public RTGBiomeBOPCoralReef CORAL_REEF;
    public RTGBiomeBOPKelpForest KELP_FOREST;
    public RTGBiomeBOPMangrove MANGROVE;
    public RTGBiomeBOPOriginIsland ORIGIN_ISLAND;
    public RTGBiomeBOPTropicalIsland TROPICAL_ISLAND;
    public RTGBiomeBOPVolcanicIsland VOLCANIC_ISLAND;
    public RTGBiomeBOPFlowerIsland FLOWER_ISLAND;
    public RTGBiomeBOPGravelBeach GRAVEL_BEACH;

    @Override
    public void initBiomes() {

        // normal biomes which have weights
        addBiome(ALPS = new RTGBiomeBOPAlps());
        addBiome(BAMBOO_FOREST = new RTGBiomeBOPBambooForest());
        addBiome(BAYOU = new RTGBiomeBOPBayou());
        addBiome(BOG = new RTGBiomeBOPBog());
        addBiome(BOREAL_FOREST = new RTGBiomeBOPBorealForest());
        addBiome(BRUSHLAND = new RTGBiomeBOPBrushland());
        addBiome(CHAPARRAL = new RTGBiomeBOPChaparral());
        addBiome(CHERRY_BLOSSOM_GROVE = new RTGBiomeBOPCherryBlossomGrove());
        addBiome(COLD_DESERT = new RTGBiomeBOPColdDesert());
        addBiome(CONIFEROUS_FOREST = new RTGBiomeBOPConiferousForest());
        addBiome(CRAG = new RTGBiomeBOPCrag());
        addBiome(DEAD_FOREST = new RTGBiomeBOPDeadForest());
        addBiome(DEAD_SWAMP = new RTGBiomeBOPDeadSwamp());
        addBiome(EUCALYPTUS_FOREST = new RTGBiomeBOPEucalyptusForest());
        addBiome(FEN = new RTGBiomeBOPFen());
        addBiome(FLOWER_FIELD = new RTGBiomeBOPFlowerField());
        addBiome(GRASSLAND = new RTGBiomeBOPGrassland());
        addBiome(GROVE = new RTGBiomeBOPGrove());
        addBiome(HEATHLAND = new RTGBiomeBOPHeathland());
        addBiome(HIGHLAND = new RTGBiomeBOPHighland());
        addBiome(LAND_OF_LAKES = new RTGBiomeBOPLandOfLakes());
        addBiome(LAVENDER_FIELDS = new RTGBiomeBOPLavenderFields());
        addBiome(LUSH_DESERT = new RTGBiomeBOPLushDesert());
        addBiome(LUSH_SWAMP = new RTGBiomeBOPLushSwamp());
        addBiome(MAPLE_WOODS = new RTGBiomeBOPMapleWoods());
        addBiome(MARSH = new RTGBiomeBOPMarsh());
        addBiome(MEADOW = new RTGBiomeBOPMeadow());
        addBiome(MOOR = new RTGBiomeBOPMoor());
        addBiome(MOUNTAIN = new RTGBiomeBOPMountain());
        addBiome(MYSTIC_GROVE = new RTGBiomeBOPMysticGrove());
        addBiome(OMINOUS_WOODS = new RTGBiomeBOPOminousWoods());
        addBiome(ORCHARD = new RTGBiomeBOPOrchard());
        addBiome(OUTBACK = new RTGBiomeBOPOutback());
        addBiome(OVERGROWN_CLIFFS = new RTGBiomeBOPOvergrownCliffs());
        addBiome(PRAIRIE = new RTGBiomeBOPPrairie());
        addBiome(QUAGMIRE = new RTGBiomeBOPQuagmire());
        addBiome(RAINFOREST = new RTGBiomeBOPRainforest());
        addBiome(REDWOOD_FOREST = new RTGBiomeBOPRedwoodForest());
        addBiome(SACRED_SPRINGS = new RTGBiomeBOPSacredSprings());
        addBiome(SEASONAL_FOREST = new RTGBiomeBOPSeasonalForest());
        addBiome(SHIELD = new RTGBiomeBOPShield());
        addBiome(SHRUBLAND = new RTGBiomeBOPShrubland());
        addBiome(SNOWY_CONIFEROUS_FOREST = new RTGBiomeBOPSnowyConiferousForest());
        addBiome(SNOWY_FOREST = new RTGBiomeBOPSnowyForest());
        addBiome(STEPPE = new RTGBiomeBOPSteppe());
        addBiome(TEMPERATE_RAINFOREST = new RTGBiomeBOPTemperateRainforest());
        addBiome(TROPICAL_RAINFOREST = new RTGBiomeBOPTropicalRainforest());
        addBiome(TUNDRA = new RTGBiomeBOPTundra());
        addBiome(WASTELAND = new RTGBiomeBOPWasteland());
        addBiome(WETLAND = new RTGBiomeBOPWetland());
        addBiome(WOODLAND = new RTGBiomeBOPWoodland());
        addBiome(XERIC_SHRUBLAND = new RTGBiomeBOPXericShrubland());

        // edge-biomes, sub-biomes and mutated-biomes
        addBiome(MOUNTAIN_FOOTHILLS = new RTGBiomeBOPMountainFoothills());
        addBiome(GLACIER = new RTGBiomeBOPGlacier());
        addBiome(OASIS = new RTGBiomeBOPOasis());
        addBiome(CORAL_REEF = new RTGBiomeBOPCoralReef());
        addBiome(KELP_FOREST = new RTGBiomeBOPKelpForest());
        addBiome(MANGROVE = new RTGBiomeBOPMangrove());
        addBiome(ORIGIN_ISLAND = new RTGBiomeBOPOriginIsland());
        addBiome(TROPICAL_ISLAND = new RTGBiomeBOPTropicalIsland());
        addBiome(VOLCANIC_ISLAND = new RTGBiomeBOPVolcanicIsland());
        addBiome(FLOWER_ISLAND = new RTGBiomeBOPFlowerIsland());
        addBiome(GRAVEL_BEACH = new RTGBiomeBOPGravelBeach());
    }
}
