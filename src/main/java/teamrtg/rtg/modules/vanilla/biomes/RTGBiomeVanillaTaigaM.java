package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionTaiga;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaTaigaM extends RTGBiomeVanilla {

    public RTGBiomeVanillaTaigaM() {

        super(Biomes.MUTATED_TAIGA, Biomes.RIVER);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainGrasslandHills(x, y, rtgWorld.simplex, rtgWorld.cell, river, 100f, 7f, 180f, 70f, 68f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceTaiga(this);
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionTaiga(10f));
    }
}
