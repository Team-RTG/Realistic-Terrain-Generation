package rtg.world.biome;

import net.minecraft.world.biome.BiomeProvider;
import rtg.api.world.RTGWorld;


public class BiomeProviderRTG extends BiomeProvider {
//  private final RTGWorld rtgWorld;
//  private final RTGChunkGenSettings generatorSettings;
//  private GenLayer genBiomes;
//  private GenLayer biomeIndexLayer;

    public BiomeProviderRTG(RTGWorld rtgWorld) {

        super(rtgWorld.world().getWorldInfo());

//      this.rtgWorld = rtgWorld; //new RTGWorld(world)
//      this.generatorSettings = rtgWorld.getGeneratorSettings();

//      GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, ChunkGeneratorSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build());
//      agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
//      this.genBiomes = agenlayer[0]; //maybe this will be needed
//      this.biomeIndexLayer = agenlayer[1];
    }
}
