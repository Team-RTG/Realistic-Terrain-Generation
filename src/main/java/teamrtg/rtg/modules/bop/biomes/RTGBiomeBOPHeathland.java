package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.terrain.HillockEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPHeathland extends RTGBiomeBOP {

    public RTGBiomeBOPHeathland() {
        super(BOPBiomes.heathland.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 66f;
            private HillockEffect hills;

            {
                hills = new HillockEffect();
                hills.height = 25;
                hills.minimumSimplex = 0.3f;
                hills.octave = 0;
                hills.wavelength = 50f;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                float added = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex);
                added += hills.added(rtgWorld.simplex, rtgWorld.cell,x, y);
                return riverized(baseHeight + added,river);
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
