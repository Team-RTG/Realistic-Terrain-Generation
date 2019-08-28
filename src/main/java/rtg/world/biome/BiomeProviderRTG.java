package rtg.world.biome;

import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayerRiverMix;

import rtg.api.util.Logger;
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
        maybeRemoveRivers();
    }

    private void maybeRemoveRivers() {
        /*
         * If the river layer is an instance of GenLayerRiverRTG (ie: no mods have altered the GenLayers from WorldTypeEvent$InitBiomeGens)
         * then leave the layers alone since it will handle rivers, otherwise the layers have been altered by another mod, such as
         * Geographicraft, so we need to remove the river layer.
         */
        //if (!(genBiomes instanceof GenLayerRiverRTG)) {
            if (genBiomes instanceof GenLayerRiverMix) {
                // Overwrite the river layer with the biome layer to kill vanilla rivers.
                Logger.debug("Removing vanilla river layer");
                ((GenLayerRiverMix)genBiomes).riverPatternGeneratorChain = ((GenLayerRiverMix)genBiomes).biomePatternGeneratorChain;
            } else {
                Logger.error("Failed to remove the vanilla river layer; Wrong GenLayer type: {}", genBiomes.getClass().getName());
            }
        //}
    }
}
