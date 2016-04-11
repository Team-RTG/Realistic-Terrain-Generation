package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaIceMountains;
import teamrtg.rtg.world.biome.terrain.TerrainBase;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaIceMountains() {

        super(
                Biomes.ICE_MOUNTAINS,
                Biomes.FROZEN_RIVER
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainLonelyMountain(x, y, simplex, cell, river, 80f, 230f, 68f);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaIceMountains(this, 60f, -0.14f, 14f, 0.25f);
    }

    @Override
    protected void initDecos() {

    }

    @Override
    protected void initProperties() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.SNOW.getDefaultState());
        config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.SNOW.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.PACKED_ICE.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.ICE.getDefaultState());
    }
}
