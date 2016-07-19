package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPKelpForest extends RTGBiomeBOP {

    public RTGBiomeBOPKelpForest() {
        super(BOPBiomes.alps.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private boolean booRiver;
            private float[] height;
            private int heightLength;
            private float strength;
            private float cWidth;
            private float cHeigth;
            private float cStrength;
            private float base;

            /*
             * Example parameters:
             *
             * allowed to generate rivers?
             * riverGen = true
             *
             * canyon jump heights
             * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
             *
             * strength of canyon jump heights
             * heightStrength = 35f
             *
             * canyon width (cliff to cliff)
             * canyonWidth = 160f
             *
             * canyon heigth (total heigth)
             * canyonHeight = 60f
             *
             * canyon strength
             * canyonStrength = 40f
             *
             */

            {
                booRiver = false;
                height = new float[]{5.0f, 0.5f, 12.5f, 0.5f};
                strength = -10f;
                heightLength = height.length;
                cWidth = 0f;
                cHeigth = 0f;
                cStrength = 0f;
                base = 30f;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainOceanCanyon(x, y, rtgWorld.simplex, river, height, border, strength, heightLength, booRiver);
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
