package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPOriginIsland extends RTGBiomeBOP {

    public RTGBiomeBOPOriginIsland() {
        super(BOPBiomes.origin_island.get(), Biomes.RIVER);
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
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.STONE_OR_COBBLE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
