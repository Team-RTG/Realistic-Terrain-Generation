/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * <p/>
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * <p/>
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.world.BOPWorldSettings;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Map;

public interface IExtendedBiome {
    void applySettings(BOPWorldSettings settings);

    BiomeOwner getBiomeOwner();

    void addGenerator(String name, GeneratorStage stage, IGenerator generator);

    GenerationManager getGenerationManager();

    Map<BOPClimates, Integer> getWeightMap();

    void clearWeights();

    void addWeight(BOPClimates climate, int weight);

    int getBeachId();

    /**Get the base biome associated with this extension**/
    BiomeGenBase getBaseBiome();
}
