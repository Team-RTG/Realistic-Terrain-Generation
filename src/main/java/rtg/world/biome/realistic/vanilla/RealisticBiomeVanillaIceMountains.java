package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaIceMountains() {

        super(
                Biomes.iceMountains,
                Biomes.frozenRiver
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
    protected void initDecos()
    {

    }
    
    @Override
    protected void initProperties()
    {
    	config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.snow.getDefaultState());
    	config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.snow.getDefaultState());
    	config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.packed_ice.getDefaultState());
    	config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.ice.getDefaultState());
    }
}
