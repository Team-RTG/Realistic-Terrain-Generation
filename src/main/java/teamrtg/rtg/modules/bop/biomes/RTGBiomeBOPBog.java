package teamrtg.rtg.modules.bop.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.terrain.HeightVariation;
import teamrtg.rtg.api.tools.terrain.HillockEffect;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeBOPBog extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.PLAINS;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeBOPBog() {
        super(
                mutationBiome,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private final float bottom = 58f;
            private final HeightVariation bottomVariation;
            private final HillockEffect smallHills;
            private final HillockEffect mediumHills;

            {
                bottomVariation = new HeightVariation();
                bottomVariation.height = 2;
                bottomVariation.octave = 0;
                bottomVariation.wavelength = 40;

                smallHills = new HillockEffect();
                smallHills.height = 6;
                smallHills.wavelength = 15;
                smallHills.minimumSimplex = 0.2f;
                smallHills.octave = 1;

                mediumHills = new HillockEffect();
                mediumHills.height = 12;
                mediumHills.wavelength = 25;
                mediumHills.minimumSimplex = 0.2f;
                mediumHills.octave = 2;

            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                float increment = bottomVariation.added(rtgWorld.simplex,rtgWorld.cell, x, y) + smallHills.added(rtgWorld.simplex, rtgWorld.cell,x, y) ;
                increment += mediumHills.added(rtgWorld.simplex, rtgWorld.cell,x, y);
                return riverized(bottom + increment,river);
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
