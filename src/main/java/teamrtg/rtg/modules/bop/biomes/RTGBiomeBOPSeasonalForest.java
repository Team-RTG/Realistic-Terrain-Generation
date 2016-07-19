package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPSeasonalForest extends RTGBiomeBOP {

    public RTGBiomeBOPSeasonalForest() {
        super(BOPBiomes.seasonal_forest.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase(68f) {

            private float start = 15f;
            private float height = 80f;
            private float width = 170f;

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, start, width, height, 0f);
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
