package teamrtg.rtg.modules.bop.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.terrain.BumpyHillsEffect;
import teamrtg.rtg.api.tools.terrain.JitterEffect;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeBOPHighland extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.PLAINS;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeBOPHighland() {
        super(
                mutationBiome,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 90f;
            private BumpyHillsEffect onTop = new BumpyHillsEffect();
            private JitterEffect withJitter;

            {
                onTop.hillHeight = 30;
                onTop.hillWavelength = 60;
                onTop.spikeHeight = 20;
                onTop.spikeWavelength = 10;

                withJitter = new JitterEffect();
                withJitter.amplitude=2;
                withJitter.wavelength=5;
                withJitter.jittered = onTop;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return riverized(baseHeight + withJitter.added(rtgWorld.simplex, rtgWorld.cell,x, y)+ this.groundNoise(x, y, 6, rtgWorld.simplex),river);
                //return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
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
