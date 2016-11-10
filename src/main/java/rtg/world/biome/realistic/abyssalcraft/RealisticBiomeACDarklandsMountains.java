package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsMountains;
import rtg.world.gen.terrain.*;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands_mountains;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklandsMountains(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceACDarklandsMountains(config, biome.topBlock, biome.fillerBlock, 0.2f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACDarklandsMountains(120f, 100f);
    }

    public class TerrainACDarklandsMountains extends FunctionalTerrainBase {

        private float width;
        private float strength;
        private float spikeWidth = 40;
        private float spikeHeight = 60;

        public TerrainACDarklandsMountains(float mountainWidth, float mountainStrength) {

            this(mountainWidth, mountainStrength, 90f);
        }

        public TerrainACDarklandsMountains(float mountainWidth, float mountainStrength, float baseHeight) {

            width = mountainWidth;
            strength = mountainStrength;
            base = baseHeight;
            MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
            mountainEffect.mountainHeight = strength;
            mountainEffect.mountainWavelength = width;
            mountainEffect.spikeHeight = this.spikeHeight;
            mountainEffect.spikeWavelength = this.spikeWidth;

            this.height = new JitterEffect(6f, 10f, mountainEffect);
            height = new JitterEffect(2f, 6f, height);

            HeightVariation passHeight = new HeightVariation();
            passHeight.height = 15;
            passHeight.octave = 4;
            passHeight.wavelength = 70;

            height = height.plus(passHeight);

        }
    }
}
