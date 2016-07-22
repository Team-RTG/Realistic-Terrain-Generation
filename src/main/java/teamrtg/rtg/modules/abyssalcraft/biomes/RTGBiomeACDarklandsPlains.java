package teamrtg.rtg.modules.abyssalcraft.biomes;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.abyssalcraft.RTGBiomeAC;

public class RTGBiomeACDarklandsPlains extends RTGBiomeAC {

    public RTGBiomeACDarklandsPlains() {
        super(ACBiomes.darklands_plains, Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
                return riverized(65f + groundEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y),river);
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

    }
}
