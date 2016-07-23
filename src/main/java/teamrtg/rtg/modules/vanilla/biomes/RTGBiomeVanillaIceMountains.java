package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaIceMountains extends RTGBiomeVanilla {

    public RTGBiomeVanillaIceMountains() {

        super(Biomes.ICE_MOUNTAINS, Biomes.FROZEN_RIVER);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.SNOW.getDefaultState());
        config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.SNOW.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.PACKED_ICE.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.ICE.getDefaultState());
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainLonelyMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, 80f, 230f, 68f);
            }
        };
    }


    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceIceMix(this);
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
