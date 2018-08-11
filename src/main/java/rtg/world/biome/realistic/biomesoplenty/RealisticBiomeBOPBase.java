package rtg.world.biome.realistic.biomesoplenty;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.world.biome.Biome;
import rtg.api.RTGAPI;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

import static biomesoplenty.api.generation.GeneratorStage.BIG_SHROOM;
import static biomesoplenty.api.generation.GeneratorStage.CACTUS;
import static biomesoplenty.api.generation.GeneratorStage.CLAY;
import static biomesoplenty.api.generation.GeneratorStage.DEAD_BUSH;
import static biomesoplenty.api.generation.GeneratorStage.FLOWERS;
import static biomesoplenty.api.generation.GeneratorStage.GRASS;
import static biomesoplenty.api.generation.GeneratorStage.LAKE_LAVA;
import static biomesoplenty.api.generation.GeneratorStage.LAKE_WATER;
import static biomesoplenty.api.generation.GeneratorStage.LILYPAD;
import static biomesoplenty.api.generation.GeneratorStage.ORE_POST;
import static biomesoplenty.api.generation.GeneratorStage.ORE_PRE;
import static biomesoplenty.api.generation.GeneratorStage.PARENT;
import static biomesoplenty.api.generation.GeneratorStage.POST;
import static biomesoplenty.api.generation.GeneratorStage.PRE;
import static biomesoplenty.api.generation.GeneratorStage.PUMPKIN;
import static biomesoplenty.api.generation.GeneratorStage.REED;
import static biomesoplenty.api.generation.GeneratorStage.SAND;
import static biomesoplenty.api.generation.GeneratorStage.SAND_PASS2;
import static biomesoplenty.api.generation.GeneratorStage.SHROOM;
import static biomesoplenty.api.generation.GeneratorStage.TREE;


// TODO: [1.12] Clean this mess up. (And do it in a way that doesn't cause the classloader to try and load classes that aren't present!)
public abstract class RealisticBiomeBOPBase extends RealisticBiomeBase implements IRealisticBOPBiome {

    private static Set<RealisticBiomeBOPBase> CACHED_BOP_BIOMES = Sets.newHashSet();
    private IGenerationManager BOPGenManager;
    private Map<GeneratorStage, Collection<IGenerator>> bopStageGenerators = Maps.newHashMap();
    private Collection<GeneratorStage> generatorStages = Collections.unmodifiableCollection(Arrays.asList(
        BIG_SHROOM, CACTUS, CLAY, DEAD_BUSH, FLOWERS, GRASS, LAKE_LAVA, LAKE_WATER, LILYPAD,
        ORE_POST, ORE_PRE, PARENT, POST, PRE, PUMPKIN, REED, SAND, SAND_PASS2, SHROOM, TREE
    ));

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);

        this.cacheBiome();

        if (!(baseBiome instanceof IExtendedBiome)) {
            throw new ClassCastException(
                String.format("Tried creating an instance of a IRealisticBiome for a BOPBiome without a %s.\n IANAL but that would be illegal.",
                    baseBiome.getClass().getName())
            );
        }

        this.BOPGenManager = ((IExtendedBiome) baseBiome).getGenerationManager();// Get this biome's Generation Manager.
        // Add the BOP biome's generators to RTG's internal list.
        this.generatorStages.forEach(stage ->
            this.bopStageGenerators.put(stage, this.BOPGenManager.getGeneratorsForStage(stage)));
        this.removeGenerators();// Now remove them from the biome so we can manually call them when we want to.
        this.removeStages();// Remove any relevant stages from decoration, as designated in the individual biome's initDecos().
    }

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeBOPBase(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    public static void addBiomes() {

        if (Mods.biomesoplenty.isLoaded()) {

            if (BOPBiomes.alps.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlps());
            }
            if (BOPBiomes.alps_foothills.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlpsFoothills());
            }
            if (BOPBiomes.bamboo_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBambooForest());
            }
            if (BOPBiomes.bayou.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBayou());
            }
            if (BOPBiomes.bog.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBog());
            }
            if (BOPBiomes.boreal_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBorealForest());
            }
            if (BOPBiomes.brushland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBrushland());
            }
            if (BOPBiomes.chaparral.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPChaparral());
            }
            if (BOPBiomes.cherry_blossom_grove.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCherryBlossomGrove());
            }
            if (BOPBiomes.cold_desert.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPColdDesert());
            }
            if (BOPBiomes.coniferous_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPConiferousForest());
            }
            if (BOPBiomes.coral_reef.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCoralReef());
            }
            if (BOPBiomes.crag.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCrag());
            }
            if (BOPBiomes.dead_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadForest());
            }
            if (BOPBiomes.dead_swamp.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadSwamp());
            }
            if (BOPBiomes.eucalyptus_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPEucalyptusForest());
            }
            if (BOPBiomes.fen.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFen());
            }
            if (BOPBiomes.flower_field.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerField());
            }
            if (BOPBiomes.flower_island.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerIsland());
            }
            if (BOPBiomes.glacier.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGlacier());
            }
            if (BOPBiomes.grassland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrassland());
            }
            if (BOPBiomes.gravel_beach.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGravelBeach());
            }
            if (BOPBiomes.grove.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrove());
            }
            if (BOPBiomes.highland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPHighland());
            }
            if (BOPBiomes.kelp_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPKelpForest());
            }
            if (BOPBiomes.land_of_lakes.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLandOfLakes());
            }
            if (BOPBiomes.lavender_fields.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLavenderFields());
            }
            if (BOPBiomes.lush_desert.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushDesert());
            }
            if (BOPBiomes.lush_swamp.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushSwamp());
            }
            if (BOPBiomes.mangrove.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMangrove());
            }
            if (BOPBiomes.maple_woods.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMapleWoods());
            }
            if (BOPBiomes.marsh.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMarsh());
            }
            if (BOPBiomes.meadow.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMeadow());
            }
            if (BOPBiomes.moor.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMoor());
            }
            if (BOPBiomes.mountain.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainPeaks());
            }
            if (BOPBiomes.mountain_foothills.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainFoothills());
            }
            if (BOPBiomes.mystic_grove.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMysticGrove());
            }
            if (BOPBiomes.oasis.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOasis());
            }
            if (BOPBiomes.ominous_woods.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOminousWoods());
            }
            if (BOPBiomes.orchard.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOrchard());
            }
            if (BOPBiomes.origin_beach.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginBeach());
            }
            if (BOPBiomes.origin_island.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginIsland());
            }
            if (BOPBiomes.outback.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOutback());
            }
            if (BOPBiomes.overgrown_cliffs.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOvergrownCliffs());
            }
            if (BOPBiomes.pasture.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPasture());
            }
            if (BOPBiomes.prairie.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPrairie());
            }
            if (BOPBiomes.quagmire.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPQuagmire());
            }
            if (BOPBiomes.rainforest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRainforest());
            }
            if (BOPBiomes.redwood_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRedwoodForest());
            }
            if (BOPBiomes.sacred_springs.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSacredSprings());
            }
            if (BOPBiomes.seasonal_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSeasonalForest());
            }
            if (BOPBiomes.shield.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShield());
            }
            if (BOPBiomes.shrubland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShrubland());
            }
            if (BOPBiomes.snowy_coniferous_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyConiferousForest());
            }
            if (BOPBiomes.snowy_forest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyForest());
            }
            if (BOPBiomes.steppe.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSteppe());
            }
            if (BOPBiomes.temperate_rainforest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTemperateRainforest());
            }
            if (BOPBiomes.tropical_island.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalIsland());
            }
            if (BOPBiomes.tropical_rainforest.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalRainforest());
            }
            if (BOPBiomes.tundra.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTundra());
            }
            if (BOPBiomes.volcanic_island.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPVolcanicIsland());
            }
            if (BOPBiomes.wasteland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWasteland());
            }
            if (BOPBiomes.wetland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWetland());
            }
            if (BOPBiomes.white_beach.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWhiteBeach());
            }
            if (BOPBiomes.woodland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWoodland());
            }
            if (BOPBiomes.xeric_shrubland.isPresent()) {
                RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPXericShrubland());
            }
        }
    }

    private void cacheBiome() {
        CACHED_BOP_BIOMES.add(this);
    }

    @Override
// TODO: [1.12] Verify that this should be over-ridden for all BoP biomes, and not just for bayou.
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public void addGenerators() {
        this.bopStageGenerators.forEach((stage, generators) ->
            generators.forEach(generator -> this.BOPGenManager.addGenerator(generator.getName(), stage, generator)));
    }

    @Override
    public final void removeGenerators() {
        this.bopStageGenerators.forEach((stage, generators) ->
            generators.forEach(generator -> this.BOPGenManager.removeGenerator(generator.getName())));
    }

    private void removeStages() {
        CACHED_BOP_BIOMES.forEach(biome ->
            biome.getDecos().stream()
                .filter(deco -> deco instanceof DecoBOPBaseBiomeDecorations)
                .map(deco -> (DecoBOPBaseBiomeDecorations) deco)
                .filter(deco -> deco.stagesToRemove().size() > 0)
                .forEach(deco -> deco.stagesToRemove().stream()
                    .filter(stage -> biome.bopStageGenerators.containsKey(stage))
                    .forEach(stage -> biome.bopStageGenerators.remove(stage)))
        );
    }
}
