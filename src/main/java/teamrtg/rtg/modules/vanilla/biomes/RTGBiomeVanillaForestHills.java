package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionForest;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaForestHills extends RTGBiomeVanilla {

    public RTGBiomeVanillaForestHills() {

        super(Biomes.FOREST_HILLS, Biomes.RIVER);

        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 68f, 45f, 10f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceForest(this);
    }

    @Override
    public void initDecos() {
    	this.addDecoCollection(new DecoCollectionForest());
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.DIRT.getStateFromMeta(2));
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
    }
}
