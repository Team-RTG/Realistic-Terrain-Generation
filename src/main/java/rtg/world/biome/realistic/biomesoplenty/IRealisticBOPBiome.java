package rtg.world.biome.realistic.biomesoplenty;

import java.util.ArrayList;
import java.util.HashMap;

import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;
import com.google.common.collect.ImmutableCollection;

/**
 * Created by WhichOnesPink on 15/06/2017.
 */
public interface IRealisticBOPBiome {

    IExtendedBiome bopExtendedBiome();
    IGenerationManager bopGenerationManager();
    HashMap<GeneratorStage, ImmutableCollection<IGenerator>> bopStageGenerators();
    ArrayList<GeneratorStage> generatorStages();
    void addBOPGenerators();
    void removeBOPGenerators();
}
