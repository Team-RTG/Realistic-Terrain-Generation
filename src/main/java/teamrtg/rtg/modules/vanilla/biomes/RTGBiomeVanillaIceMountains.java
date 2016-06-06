package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaIceMountains extends RTGBiomeVanilla {

    public RTGBiomeVanillaIceMountains() {

        super(
                Biomes.ICE_MOUNTAINS,
            Biomes.FROZEN_RIVER
        );
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.4f)
            .add(new DepthSelector(0, 1)
                .add(PARTS.rand(3)
                    .add(new BlockPart(config.CLIFF_BLOCK_2.get()))))
            .add(new BlockPart(config.CLIFF_BLOCK_1.get())));
        surface.add(PARTS.surfaceMix((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 60f, z / 60f) + rtgWorld.simplex.noise2(x / 14f, z / 14f) * 0.25f > -0.14f));
        surface.add(PARTS.surfaceGeneric());
        return surface;
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
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.SNOW.getDefaultState());
        config.addBlock(config.MIX_BLOCK_FILL).setDefault(Blocks.SNOW.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.PACKED_ICE.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.ICE.getDefaultState());
    }
}
