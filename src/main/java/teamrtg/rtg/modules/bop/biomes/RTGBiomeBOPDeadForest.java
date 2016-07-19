package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPDeadForest extends RTGBiomeBOP {

    public RTGBiomeBOPDeadForest() {
        super(BOPBiomes.dead_forest.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float minHeight = 58f;
            private float maxHeight = 80f;
            private float hillStrength = 30f;
            private float deadForestGroundAmplitude = 10f;

            {
                this.minHeight = minHeight;
                this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
                this.hillStrength = hillStrength;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainRollingHills(x, y, rtgWorld.simplex, river, hillStrength, maxHeight, groundNoise, deadForestGroundAmplitude, 0f);
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
