package rtg.world.biome.realistic.biomesoplenty;

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

    public RealisticBiomeBOPBase(Biome baseBiome, Biome riverbiome) {

        super(baseBiome, riverbiome);
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

    private void cacheBiome() {
        CACHED_BOP_BIOMES.add(this);
    }

    @Override
// TODO: [1.12] Verify that this should be over-ridden for all BoP biomes, and not just for bayou.
    public int waterSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Mods.biomesoplenty.isLoaded()) {
            if (BOPBiomes.alps.isPresent())                     new RealisticBiomeBOPAlps();
            if (BOPBiomes.bamboo_forest.isPresent())            new RealisticBiomeBOPBambooForest();
            if (BOPBiomes.bayou.isPresent())                    new RealisticBiomeBOPBayou();
            if (BOPBiomes.bog.isPresent())                      new RealisticBiomeBOPBog();
            if (BOPBiomes.boreal_forest.isPresent())            new RealisticBiomeBOPBorealForest();
            if (BOPBiomes.brushland.isPresent())                new RealisticBiomeBOPBrushland();
            if (BOPBiomes.chaparral.isPresent())                new RealisticBiomeBOPChaparral();
            if (BOPBiomes.cherry_blossom_grove.isPresent())     new RealisticBiomeBOPCherryBlossomGrove();
            if (BOPBiomes.cold_desert.isPresent())              new RealisticBiomeBOPColdDesert();
            if (BOPBiomes.coniferous_forest.isPresent())        new RealisticBiomeBOPConiferousForest();
            if (BOPBiomes.coral_reef.isPresent())               new RealisticBiomeBOPCoralReef();
            if (BOPBiomes.crag.isPresent())                     new RealisticBiomeBOPCrag();
            if (BOPBiomes.dead_forest.isPresent())              new RealisticBiomeBOPDeadForest();
            if (BOPBiomes.dead_swamp.isPresent())               new RealisticBiomeBOPDeadSwamp();
            if (BOPBiomes.eucalyptus_forest.isPresent())        new RealisticBiomeBOPEucalyptusForest();
            if (BOPBiomes.fen.isPresent())                      new RealisticBiomeBOPFen();
            if (BOPBiomes.flower_field.isPresent())             new RealisticBiomeBOPFlowerField();
            if (BOPBiomes.flower_island.isPresent())            new RealisticBiomeBOPFlowerIsland();
            if (BOPBiomes.glacier.isPresent())                  new RealisticBiomeBOPGlacier();
            if (BOPBiomes.grassland.isPresent())                new RealisticBiomeBOPGrassland();
            if (BOPBiomes.gravel_beach.isPresent())             new RealisticBiomeBOPGravelBeach();
            if (BOPBiomes.grove.isPresent())                    new RealisticBiomeBOPGrove();
            if (BOPBiomes.highland.isPresent())                 new RealisticBiomeBOPHighland();
            if (BOPBiomes.kelp_forest.isPresent())              new RealisticBiomeBOPKelpForest();
            if (BOPBiomes.land_of_lakes.isPresent())            new RealisticBiomeBOPLandOfLakes();
            if (BOPBiomes.lavender_fields.isPresent())          new RealisticBiomeBOPLavenderFields();
            if (BOPBiomes.lush_desert.isPresent())              new RealisticBiomeBOPLushDesert();
            if (BOPBiomes.lush_swamp.isPresent())               new RealisticBiomeBOPLushSwamp();
            if (BOPBiomes.mangrove.isPresent())                 new RealisticBiomeBOPMangrove();
            if (BOPBiomes.maple_woods.isPresent())              new RealisticBiomeBOPMapleWoods();
            if (BOPBiomes.marsh.isPresent())                    new RealisticBiomeBOPMarsh();
            if (BOPBiomes.meadow.isPresent())                   new RealisticBiomeBOPMeadow();
            if (BOPBiomes.moor.isPresent())                     new RealisticBiomeBOPMoor();
            if (BOPBiomes.mountain.isPresent())                 new RealisticBiomeBOPMountainPeaks();
            if (BOPBiomes.mountain_foothills.isPresent())       new RealisticBiomeBOPMountainFoothills();
            if (BOPBiomes.mystic_grove.isPresent())             new RealisticBiomeBOPMysticGrove();
            if (BOPBiomes.oasis.isPresent())                    new RealisticBiomeBOPOasis();
            if (BOPBiomes.ominous_woods.isPresent())            new RealisticBiomeBOPOminousWoods();
            if (BOPBiomes.orchard.isPresent())                  new RealisticBiomeBOPOrchard();
            if (BOPBiomes.origin_island.isPresent())            new RealisticBiomeBOPOriginIsland();
            if (BOPBiomes.outback.isPresent())                  new RealisticBiomeBOPOutback();
            if (BOPBiomes.overgrown_cliffs.isPresent())         new RealisticBiomeBOPOvergrownCliffs();
            if (BOPBiomes.prairie.isPresent())                  new RealisticBiomeBOPPrairie();
            if (BOPBiomes.quagmire.isPresent())                 new RealisticBiomeBOPQuagmire();
            if (BOPBiomes.rainforest.isPresent())               new RealisticBiomeBOPRainforest();
            if (BOPBiomes.redwood_forest.isPresent())           new RealisticBiomeBOPRedwoodForest();
            if (BOPBiomes.sacred_springs.isPresent())           new RealisticBiomeBOPSacredSprings();
            if (BOPBiomes.seasonal_forest.isPresent())          new RealisticBiomeBOPSeasonalForest();
            if (BOPBiomes.shield.isPresent())                   new RealisticBiomeBOPShield();
            if (BOPBiomes.shrubland.isPresent())                new RealisticBiomeBOPShrubland();
            if (BOPBiomes.snowy_coniferous_forest.isPresent())  new RealisticBiomeBOPSnowyConiferousForest();
            if (BOPBiomes.snowy_forest.isPresent())             new RealisticBiomeBOPSnowyForest();
            if (BOPBiomes.steppe.isPresent())                   new RealisticBiomeBOPSteppe();
            if (BOPBiomes.temperate_rainforest.isPresent())     new RealisticBiomeBOPTemperateRainforest();
            if (BOPBiomes.tropical_island.isPresent())          new RealisticBiomeBOPTropicalIsland();
            if (BOPBiomes.tropical_rainforest.isPresent())      new RealisticBiomeBOPTropicalRainforest();
            if (BOPBiomes.tundra.isPresent())                   new RealisticBiomeBOPTundra();
            if (BOPBiomes.volcanic_island.isPresent())          new RealisticBiomeBOPVolcanicIsland();
            if (BOPBiomes.wasteland.isPresent())                new RealisticBiomeBOPWasteland();
            if (BOPBiomes.wetland.isPresent())                  new RealisticBiomeBOPWetland();
            if (BOPBiomes.woodland.isPresent())                 new RealisticBiomeBOPWoodland();
            if (BOPBiomes.xeric_shrubland.isPresent())          new RealisticBiomeBOPXericShrubland();
        }
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
