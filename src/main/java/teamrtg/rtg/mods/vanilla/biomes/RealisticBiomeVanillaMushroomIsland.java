package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaMushroomIsland;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMushroomIsland(ChunkProviderRTG chunkProvider) {
        super(
                Biomes.MUSHROOM_ISLAND,
                Biomes.RIVER,
                chunkProvider
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainGrasslandFlats(x, y, simplex, river, 40f, 25f, 68f);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaMushroomIsland(this, 67, 0f);
    }

    @Override
    protected void initDecos() {

    }

    @Override
    protected void initProperties() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.MYCELIUM.getDefaultState());
    }
}
