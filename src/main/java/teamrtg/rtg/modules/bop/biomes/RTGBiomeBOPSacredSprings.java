package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPSacredSprings extends RTGBiomeBOP {

    public RTGBiomeBOPSacredSprings() {
        super(BOPBiomes.sacred_springs.get(), Biomes.RIVER);
        this.noWaterFeatures = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float width;
            private float strength;
            private float lakeDepth;
            private float lakeWidth;
            private float terrainHeight;

            {
                width = 150f;
                strength = 30f;
                terrainHeight = 68f;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainLonelyMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, strength, width, terrainHeight);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {
        config.WATER_POND_CHANCE.set(2);
    }
}
