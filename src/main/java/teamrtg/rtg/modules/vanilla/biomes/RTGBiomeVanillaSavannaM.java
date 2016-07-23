package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesertRiver;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionSavanna;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaSavannaM extends RTGBiomeVanilla {

    public RTGBiomeVanillaSavannaM() {

        super(Biomes.MUTATED_SAVANNA, Biomes.RIVER);

        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 4f, 90f, 67f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceSavanna(this);
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna());
    }

    @Override
    public void initConfig() {

    }
}
