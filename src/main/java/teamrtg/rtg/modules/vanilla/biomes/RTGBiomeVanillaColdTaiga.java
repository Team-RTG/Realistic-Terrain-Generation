package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionTaiga;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaColdTaiga extends RTGBiomeVanilla {

    public RTGBiomeVanillaColdTaiga() {

        super(Biomes.COLD_TAIGA, Biomes.FROZEN_RIVER);
    }

    @Override
    public void initConfig() {

        this.config.SCATTERED_FEATURE.setDefault(BiomeConfig.FeatureType.IGLOO.name());
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainFlatLakes(x, y, rtgWorld.simplex, river, 13f, 66f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceTaiga(this);
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionTaiga(8f));
    }
}
