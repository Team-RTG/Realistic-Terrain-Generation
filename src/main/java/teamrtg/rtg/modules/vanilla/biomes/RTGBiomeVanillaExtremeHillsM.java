package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaExtremeHillsM extends RTGBiomeVanilla {

    public RTGBiomeVanillaExtremeHillsM() {

        super(Biomes.MUTATED_EXTREME_HILLS, Biomes.RIVER);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.GRASS.getDefaultState());
        config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.DIRT.getDefaultState());
        config.GENERATE_EMERALDS.setDefault(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 200f, 140f, 10f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceExtremeHills(this);
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
