package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.util.noise.SimplexOctave;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPChaparral extends RTGBiomeBOP {

    public RTGBiomeBOPChaparral() {
        super(BOPBiomes.chaparral.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 76f;
            private float peakyHillWavelength = 40f;
            private float peakyHillStrength = 40f;
            private float smoothHillWavelength = 60f;
            private float smoothHillStrength = 30f;

            private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
            private float wavelength = 10f;// of jitter
            private float amplitude = 2f;// of jitter

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex);

                //float m = hills(x, y, peakyHillStrength, simplex, river);

                rtgWorld.simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
                int pX = (int)Math.round(x + jitter.deltax() * amplitude);
                int pY = (int)Math.round(y + jitter.deltay() * amplitude);
                float h = this.terrainGrasslandHills(pX, pY, rtgWorld.simplex, rtgWorld.cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

                return groundNoise+ h;
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
