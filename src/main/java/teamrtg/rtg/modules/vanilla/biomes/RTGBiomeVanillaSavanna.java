package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesertRiver;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionSavanna;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaSavanna extends RTGBiomeVanilla {

    public RTGBiomeVanillaSavanna() {

        super(Biomes.SAVANNA, Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            private final GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return riverized(65f + groundEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y), river);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceSavanna(this);
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna());
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.GRASS.getDefaultState());
    }
}
