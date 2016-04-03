package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaColdBeach() {
        super(
                Biomes.coldBeach,
                Biomes.river

        );

        initProperties();
        initDecos();
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaColdBeach(this);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainBeach(x, y, simplex, river, 180f, 35f, 63f);
            }
        };
    }

    @Override
    protected void initProperties()
    {

    }

    @Override
    protected void initDecos()
    {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);
    }
}
