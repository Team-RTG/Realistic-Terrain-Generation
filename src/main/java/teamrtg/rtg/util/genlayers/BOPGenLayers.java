package teamrtg.rtg.util.genlayers;

import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.BiomeProviderBOP;
import biomesoplenty.common.world.layer.*;
import net.minecraft.world.gen.layer.*;

/**
 * All of this is BOP code, i just had to remove the need for a BOPWorldType and such
 * @author topisani
 */
public class BOPGenLayers {

    public static GenLayer[] setupBOPGenLayers(long worldSeed) {
        BOPWorldSettings settings = new BOPWorldSettings();

        int biomeSize = settings.biomeSize.getValue();
        int riverSize = 4;

        // first few layers just create areas of land and sea, continents and islands
        GenLayer mainBranch = BiomeProviderBOP.initialLandAndSeaLayer(settings.landScheme);

        // add mushroom islands and deep oceans
        mainBranch = new GenLayerAddMushroomIsland(5L, mainBranch);
        mainBranch = new GenLayerLargeIsland(5L, mainBranch);
        mainBranch = new GenLayerDeepOcean(4L, mainBranch);

        // fork off a new branch as a seed for rivers and sub biomes
        GenLayer riversAndSubBiomesInit = new GenLayerRiverInit(100L, mainBranch);

        // create climate layer
        GenLayer climateLayer = BiomeProviderBOP.climateLayer(settings, worldSeed);

        // allocate the biomes
        mainBranch = allocateBOPBiomes(settings, mainBranch, riversAndSubBiomesInit, climateLayer);

        // do a bit more zooming, depending on biomeSize
        //mainBranch = new GenLayerRareBiome(1001L, mainBranch); - sunflower plains I think
        for (int i = 0; i < biomeSize; ++i) {
            mainBranch = new GenLayerZoom((long) (1000 + i), mainBranch);
            if (i == 0) {mainBranch = new GenLayerRaggedEdges(3L, mainBranch);}
            if (i == 1 || biomeSize == 1) {mainBranch = new GenLayerShoreBOP(1000L, mainBranch);}
        }
        mainBranch = new GenLayerSmooth(1000L, mainBranch);

        // develop the rivers branch
        GenLayer riversBranch = GenLayerZoom.magnify(1000L, riversAndSubBiomesInit, 2);
        riversBranch = GenLayerZoom.magnify(1000L, riversBranch, riverSize);
        riversBranch = new GenLayerRiver(1L, riversBranch);
        riversBranch = new GenLayerSmooth(1000L, riversBranch);

        // mix rivers into main branch
        GenLayer riverMixFinal = new GenLayerRiverMixBOP(100L, mainBranch, riversBranch);

        // finish biomes with Voronoi zoom
        GenLayer biomesFinal = new GenLayerVoronoiZoom(10L, riverMixFinal);

        riverMixFinal.initWorldGenSeed(worldSeed);
        biomesFinal.initWorldGenSeed(worldSeed);
        return new GenLayer[] {riverMixFinal, biomesFinal, riverMixFinal};

    }

    private static GenLayer allocateBOPBiomes(BOPWorldSettings settings, GenLayer mainBranch, GenLayer subBiomesInit, GenLayer climateLayer) {
        // allocate the basic biomes
        GenLayer biomesLayer = new GenLayerBiomeBOP(200L, mainBranch, climateLayer, settings);

        // magnify everything (using the same seed)
        biomesLayer = new GenLayerZoom(1000L, biomesLayer);
        subBiomesInit = new GenLayerZoom(1000L, subBiomesInit);
        climateLayer = new GenLayerZoom(1000L, climateLayer);

        // magnify everything again (using the same seed)
        biomesLayer = new GenLayerZoom(1000L, biomesLayer);
        subBiomesInit = new GenLayerZoom(1000L, subBiomesInit);
        climateLayer = new GenLayerZoom(1000L, climateLayer);

        // add edge biomes
        biomesLayer = new GenLayerBiomeEdgeBOP(1000L, biomesLayer);

        // add sub-biomes (like hills or rare mutated variants) seeded with subBiomesInit
        biomesLayer = new GenLayerSubBiomesBOP(1000L, biomesLayer, subBiomesInit);

        // add tiny islands
        biomesLayer = new GenLayerBiomeIslands(15L, biomesLayer, climateLayer, 12);

        return biomesLayer;
    }
}
