package rtg.world.biome.realistic.biomesoplenty;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.world.biome.Biome;
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
