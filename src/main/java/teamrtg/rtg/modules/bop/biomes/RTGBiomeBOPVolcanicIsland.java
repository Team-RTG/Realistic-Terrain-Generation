package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.deco.DecoGrassDoubleTallgrass;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPVolcanicIsland extends RTGBiomeBOP {

    public RTGBiomeBOPVolcanicIsland() {

        super(BOPBiomes.volcanic_island.get(), Biomes.RIVER);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        config.GENERATE_EMERALDS.setDefault(true);
        config.WATER_POND_CHANCE.set(0);
        config.LAVA_POND_CHANCE.set(1);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {

            private float hHeight;
            private float hWidth;
            private float vHeight;
            private float vWidth;
            private float lHeight;
            private float lWidth;
            private float bHeight;

            /*
             * hillHeight = 70f
             * hillWidth = 180f
             *
             * varHeight = 7f
             * varWidth = 100f
             *
             * lakeHeigth = 38f
             * lakeWidth = 260f
             *
             * baseHeight = 68f
             *
             * 70f, 180f, 7f, 100f, 38f, 260f, 68f
             */

            {

                hHeight = 90f;
                hWidth = 180f;

                vHeight = 13f;
                vWidth = 100f;

                lHeight = 1f;
                lWidth = 260f;

                bHeight = 59f;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainGrasslandHills(x, y, rtgWorld.simplex, rtgWorld.cell, river, vWidth, vHeight, hWidth, hHeight, bHeight);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
    }
}
