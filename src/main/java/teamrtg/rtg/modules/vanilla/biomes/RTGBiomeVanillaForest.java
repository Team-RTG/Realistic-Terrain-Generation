package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.block.BlockDirt;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionForest;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaForest extends RTGBiomeVanilla {

    public RTGBiomeVanillaForest() {

        super(Biomes.FOREST, Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                groundNoise = groundNoise(x, y, groundVariation, rtgWorld.simplex);
                float m = hills(x, y, 10f, rtgWorld.simplex, river);
                float floNoise = 65f + groundNoise + m;

                return riverized(floNoise, river);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceForest(this);
    }

    @Override
    public void initDecos() {
    	
    	this.addDecoCollection(new DecoCollectionForest());
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL));
        config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.DIRT.getDefaultState());
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
    }
}
