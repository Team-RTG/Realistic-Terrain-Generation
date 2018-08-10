package teamrtg.rtg.modules.abyssalcraft.biomes;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.HeightEffect;
import teamrtg.rtg.api.tools.terrain.HeightVariation;
import teamrtg.rtg.api.tools.terrain.JitterEffect;
import teamrtg.rtg.api.tools.terrain.MountainsWithPassesEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.abyssalcraft.RTGBiomeAC;

public class RTGBiomeACDarklandsMountains extends RTGBiomeAC {

    public RTGBiomeACDarklandsMountains() {

        super(ACBiomes.darklands_mountains, Biomes.RIVER);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {

            private HeightEffect height;
            private float width;
            private float strength;
            private float spikeWidth = 40;
            private float spikeHeight = 60;

            {
                width = 120f;
                strength = 100f;
                base = 90f;
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

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return riverized(65f + height.added(rtgWorld.simplex, rtgWorld.cell, x, y), river);
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
}
