package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.terrain.HeightVariation;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPMarsh extends RTGBiomeBOP {

    public RTGBiomeBOPMarsh() {
        super(BOPBiomes.marsh.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 62f;
            private HeightVariation variation;
            private HeightVariation smallVariation;

            {
                variation = new HeightVariation();
                variation.height = 1.5f;
                variation.wavelength = 20;
                variation.octave = 0;

                smallVariation = new HeightVariation();
                smallVariation.height = 1.5f;
                smallVariation.wavelength = 10;
                smallVariation.octave = 0;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return baseHeight + variation.added(rtgWorld.simplex, rtgWorld.cell,x, y)+ smallVariation.added(rtgWorld.simplex, rtgWorld.cell,x, y);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(PARTS.STONE_OR_COBBLE));
        surface.add(PARTS.surfaceSwamp());
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
