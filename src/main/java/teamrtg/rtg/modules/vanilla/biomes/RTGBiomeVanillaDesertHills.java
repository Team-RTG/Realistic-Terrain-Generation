package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesert;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesertRiver;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaDesertHills extends RTGBiomeVanilla {

    public RTGBiomeVanillaDesertHills() {

        super(Biomes.DESERT_HILLS, Biomes.RIVER);

        this.noLakes = true;
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceDesertHills(this);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 200f, 120f, 10f);
            }
        };
    }

    @Override
    public void initDecos() {
		this.addDecoCollection(new DecoCollectionDesertRiver());
		this.addDecoCollection(new DecoCollectionDesert());
    }

    @Override
    public void initConfig() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(BiomeConfig.FeatureType.DESERT_TEMPLE.name());
        this.config.WATER_POND_CHANCE.setDefault(0);
    }
}
